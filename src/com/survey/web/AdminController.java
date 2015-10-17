package com.survey.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Attr;

import au.com.bytecode.opencsv.CSVReader;

import com.survey.dao.AnswerDAO;
import com.survey.dao.AssetAttributeDAO;
import com.survey.dao.AssetDAO;
import com.survey.dao.AttributeDAO;
import com.survey.dao.CampaignDAO;
import com.survey.dao.QuestionDAO;
import com.survey.dao.QuestionGroupDAO;
import com.survey.dao.QuestionnaireDAO;
import com.survey.dao.RoleDao;
import com.survey.dao.SectionAttributeDAO;
import com.survey.dao.SectionDAO;
import com.survey.dao.StatusDAO;
import com.survey.dao.TargetQuestionnaireWDAO;
import com.survey.dao.TargetTypeDAO;
import com.survey.dao.TargetTypeStatusDAO;
import com.survey.dao.TemplateDAO;
import com.survey.dao.TemplateSectionDAO;
import com.survey.dao.UserAnswerDAO;
import com.survey.dao.UserCampaignDAO;
import com.survey.dao.UserDao;
import com.survey.hibernate.model.Answer;
import com.survey.hibernate.model.Asset;
import com.survey.hibernate.model.AssetAttribute;
import com.survey.hibernate.model.Attribute;
import com.survey.hibernate.model.Campaign;
import com.survey.hibernate.model.Question;
import com.survey.hibernate.model.QuestionGroup;
import com.survey.hibernate.model.Questionnaire;
import com.survey.hibernate.model.Role;
import com.survey.hibernate.model.Section;
import com.survey.hibernate.model.SectionAttribute;
import com.survey.hibernate.model.Status;
import com.survey.hibernate.model.TargetQuestionnaire;
import com.survey.hibernate.model.TargetType;
import com.survey.hibernate.model.TargetTypeStatus;
import com.survey.hibernate.model.Template;
import com.survey.hibernate.model.TemplateSection;
import com.survey.hibernate.model.User;
import com.survey.hibernate.model.UserAnswer;
import com.survey.hibernate.model.UserCampaign;
import com.survey.model.AssetForm;
import com.survey.model.AssetTypeTemplateForm;
import com.survey.model.AttributeForm;
import com.survey.model.CSVUploadForm;
import com.survey.model.CampaignForm;
import com.survey.model.ChangePasswordForm;
import com.survey.model.FieldTypeEnum;
import com.survey.model.QuestionAnswer;
import com.survey.model.QuestionIdsForm;
import com.survey.model.QuestionSheet;
import com.survey.model.QuestionTypeEnum;
import com.survey.model.QuestionnaireForm;
import com.survey.model.QuestionnaireIdsForm;
import com.survey.model.SectionAttributeForm;
import com.survey.model.SectionForm;
import com.survey.model.StatusForm;
import com.survey.model.TargetTypeForm;
import com.survey.model.TemplateForm;
import com.survey.model.UserAnswerSheetForm;
import com.survey.model.UserQuestionnaireCompletedForm;
import com.survey.model.UserQuestionnaireForm;
import com.survey.model.UserQuestionnaireGroupCompletedForm;
import com.survey.model.UserRegistrationForm;
import com.survey.utils.Constants;
import com.survey.utils.UserValidator;

@Controller
public class AdminController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired(required = false)
	private QuestionDAO questionDao;

	@Autowired(required = false)
	private QuestionnaireDAO questionnaireDao;

	@Autowired(required = false)
	private QuestionGroupDAO questionGroupDao;

	@Autowired(required = false)
	private UserDao userDao;

	@Autowired(required = false)
	private RoleDao roleDao;

	@Autowired(required = false)
	private UserAnswerDAO userAnswerDao;

	@Autowired
	Md5PasswordEncoder passwordEncoder;

	@Autowired
	private AttributeDAO attributeDAO;

	@Autowired
	private SectionDAO sectionDAO;

	@Autowired
	private TemplateDAO templateDAO;

	@Autowired
	private SectionAttributeDAO sectionAttributeDao;

	@Autowired
	private TemplateSectionDAO templateSectionDAO;

	@Autowired
	private StatusDAO statusDAO;

	@Autowired
	private TargetTypeDAO targetTypeDao;

	@Autowired
	private TargetTypeStatusDAO targetTypeStatusDAO;

	@Autowired
	private AssetDAO assetDAO;

	@Autowired
	private CampaignDAO campaignDAO;

	@Autowired
	private TargetQuestionnaireWDAO targetQuestionnaireWDAO;

	@Autowired(required = false)
	private AnswerDAO answerDao;

	@Autowired
	private AssetAttributeDAO assetAttributeDAO;

	@Autowired
	private UserCampaignDAO userCampaignDAO;

	@Autowired
	private ResourceLoader resourceLoader;

	private static final char SEPARATOR = ',';

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("userName", authentication.getName());

		this.loadQuestionMenues(model);
		this.loadTreeMenu(model);
		
		return new ModelAndView("/admin/index", "model", myModel);
	}
	
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "question/listByActive/{active}")
	public String questionListByActive(@PathVariable("active") Boolean active, ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		// List<Question> questions = questionDao.findAll();

		// model.addAttribute("questionnaireForm", new QuestionnaireForm());
		model.put(
				"questionnaireForm",
				session.getAttribute("questionnaireForm") != null ? session
						.getAttribute("questionnaireForm")
						: new QuestionnaireForm());
		model.put(
				"org.springframework.validation.BindingResult.questionnaireForm",
				session.getAttribute("binding"));

		model.addAttribute(
				"questionIdsForm",
				session.getAttribute("questionIdsForm") != null ? session
						.getAttribute("questionIdsForm")
						: new QuestionIdsForm());
		model.put(
				"org.springframework.validation.BindingResult.questionIdsForm",
				session.getAttribute("binding"));
		model.addAttribute("title", "question bank");

		model.put(
				"csvUploadForm",
				session.getAttribute("csvUploadForm") != null ? session
						.getAttribute("csvUploadForm") : new CSVUploadForm());
		model.put("org.springframework.validation.BindingResult.csvUploadForm",
				session.getAttribute("binding"));

		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			List<Question> questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questionList", this.questionDao.findByActive(active));
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("questionnaires", questionnaires);
		this.loadTreeMenu(model);
		session.removeAttribute("questionIdsForm");
		session.removeAttribute("questionnaireForm");
		session.removeAttribute("binding");

		return "/admin/question/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "question/list", method = RequestMethod.GET)
	public String questions(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		// List<Question> questions = questionDao.findAll();

		// model.addAttribute("questionnaireForm", new QuestionnaireForm());
		model.put(
				"questionnaireForm",
				session.getAttribute("questionnaireForm") != null ? session
						.getAttribute("questionnaireForm")
						: new QuestionnaireForm());
		model.put(
				"org.springframework.validation.BindingResult.questionnaireForm",
				session.getAttribute("binding"));

		model.addAttribute(
				"questionIdsForm",
				session.getAttribute("questionIdsForm") != null ? session
						.getAttribute("questionIdsForm")
						: new QuestionIdsForm());
		model.put(
				"org.springframework.validation.BindingResult.questionIdsForm",
				session.getAttribute("binding"));
		model.addAttribute("title", "question bank");

		model.put(
				"csvUploadForm",
				session.getAttribute("csvUploadForm") != null ? session
						.getAttribute("csvUploadForm") : new CSVUploadForm());
		model.put("org.springframework.validation.BindingResult.csvUploadForm",
				session.getAttribute("binding"));

		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			List<Question> questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questionList", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("questionnaires", questionnaires);
		this.loadTreeMenu(model);
		session.removeAttribute("questionIdsForm");
		session.removeAttribute("questionnaireForm");
		session.removeAttribute("binding");

		return "/admin/question/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "question/form", method = RequestMethod.GET)
	public String createQuestionForm(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<QuestionTypeEnum> types = new ArrayList<QuestionTypeEnum>(
				Arrays.asList(QuestionTypeEnum.values()));

		model.addAttribute("title", "create new question form");
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("types", types);

		model.addAttribute(
				"question",
				session.getAttribute("question") != null ? session
						.getAttribute("question") : new Question());
		model.put("org.springframework.validation.BindingResult.question",
				session.getAttribute("binding"));
		model.addAttribute("successMsg", session.getAttribute("successMsg"));

		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			List<Question> questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("questionnaires", questionnaires);
		this.loadTreeMenu(model);
		session.removeAttribute("question");
		session.removeAttribute("binding");

		return "/admin/question/form";// , "model", myModel);
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "question/form/{questionId}", method = RequestMethod.GET)
	public String editQuestionForm(ModelMap model, HttpSession session,
			@PathVariable("questionId") Integer questionId) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<QuestionTypeEnum> types = new ArrayList<QuestionTypeEnum>(
				Arrays.asList(QuestionTypeEnum.values()));

		if (questionId != null) {

			Question question = questionDao.findById(questionId);

			session.setAttribute("question", question);

			List<Answer> answers = new ArrayList<Answer>(
					question.getAnswersForQuestion());

			model.addAttribute("answers", answers);

			model.addAttribute("question", question);
		} else {
			model.addAttribute("question", new Question());
		}
		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			List<Question> questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("questionnaires", questionnaires);
		this.loadTreeMenu(model);
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("types", types);

		return "/admin/question/form";// , "model", myModel);
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "question/detail/{questionId}", method = RequestMethod.GET)
	public String detailQuestionForm(ModelMap model, HttpSession session,
			@PathVariable("questionId") Integer questionId) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		// List<QuestionTypeEnum> types = new
		// ArrayList<QuestionTypeEnum>(Arrays.asList(QuestionTypeEnum.values()));

		if (questionId != null) {

			Question question = questionDao.findById(questionId);

			session.setAttribute("question", question);

			List<Answer> answers = new ArrayList<Answer>(
					question.getAnswersForQuestion());

			model.addAttribute("answers", answers);

			model.addAttribute("question", question);
		} else {
			model.addAttribute("question", new Question());
		}
		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			List<Question> questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("questionnaires", questionnaires);
		this.loadTreeMenu(model);
		model.addAttribute("userName", authentication.getName());
		// model.addAttribute("types", types);

		return "/admin/question/detail";// , "model", myModel);
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "questions/delete", method = RequestMethod.POST)
	public String deleteQuestions(
			@ModelAttribute("questionIdsForm") @Valid QuestionIdsForm questionIdsForm,
			BindingResult result, RedirectAttributes attr, HttpSession session,
			SessionStatus status) {

		if (result.hasErrors()) {

			session.setAttribute("questionIdsForm", questionIdsForm);
			session.setAttribute("binding", result);
			return "redirect:/admin/question/list";
		} else {

			for (Integer questionId : questionIdsForm.getQuestionIds()) {

				Question question = questionDao.findById(questionId);

				question.setActive(false);
				questionDao.merge(question);

				/*
				 * List<Questionnaire> questionnaires =
				 * questionnaireDao.getQuestionnairesByQuestion(questionId);
				 * 
				 * for(Questionnaire questionnaire : questionnaires) {
				 * 
				 * questionnaireDao.deleteQuestionnaire(questionnaire); }
				 */
			}

			status.setComplete();

			return "redirect:/admin/question/list";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "questions/delete/{questionId}", method = RequestMethod.GET)
	public String deleteQuestion(ModelMap model,
			@PathVariable("questionId") Integer questionId,
			HttpSession session, SessionStatus status) {

		Question question = questionDao.findById(questionId);
		question.setActive(false);
		questionDao.merge(question);

		/*
		 * List<Questionnaire> questionnaires =
		 * questionnaireDao.getQuestionnairesByQuestion(questionId);
		 * 
		 * for(Questionnaire questionnaire : questionnaires) {
		 * 
		 * questionnaireDao.deleteQuestionnaire(questionnaire); }
		 */

		status.setComplete();

		return "redirect:/admin/question/list";

	}

	public void validateQuestion(Question question, Errors errors) {

		if (question.getName() == null || question.getName().isEmpty()) {
			errors.rejectValue("name", "required", "name can not be empty");
		}
		/*
		 * if(question.getQuestion().isEmpty() || question.getQuestion() ==
		 * null){ errors.rejectValue("question", "required",
		 * "question details can not be empty"); }
		 */
		if (question.getQuestionType() == null
				|| question.getQuestionType().isEmpty()) {
			errors.rejectValue("questionType", "required",
					"question type is required");
		}
		if (question.getText() == null || question.getText().isEmpty()) {
			errors.rejectValue("text", "required", "question text is required");
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "/question/save", method = RequestMethod.POST)
	public String saveQuestion(@ModelAttribute("questionAnswer") QuestionAnswer questionAnswer, @ModelAttribute("question") Question question,
			HttpServletRequest request, HttpSession session, BindingResult result, SessionStatus status)
			throws IllegalStateException, IOException {

		validateQuestion(question, result);
		if (result.hasErrors()) {

			session.setAttribute("question", question);
			session.setAttribute("binding", result);

			return "redirect:/admin/question/form";
		} else {

			/*if (file != null && !file.isEmpty()) {
				String filePath = session.getServletContext().getRealPath("/");

				// File dir = resourceLoader.getResource(filePath +
				// "attachments").getFile();
				File dir = new File(filePath + "data/attachments");
				if (!dir.exists())
					dir.mkdirs();

				// dir = resourceLoader.getResource(filePath +
				// "attachments").getFile();
				byte[] bytes = file.getBytes();
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());

				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				// file.transferTo(dir);

				// file.transferTo(dir);

				question.setFilePath(file.getOriginalFilename());
			}*/

			if (question.getId() != null) {
				this.questionDao.merge(question);

				List<Answer> answers = new ArrayList<Answer>(
						question.getAnswersForQuestion());
				for (Answer answer : answers) {
					this.answerDao.delete(answer);
				}
			} else
				this.questionDao.save(question);

			if (questionAnswer.getAnswers() != null
					&& !questionAnswer.getAnswers().isEmpty()
					&& (question.getQuestionType().equals(
							QuestionTypeEnum.MULTICHOICE.toString()) || question
							.getQuestionType().equals(
									QuestionTypeEnum.LIST_CHOICE.toString()))) {

				for (Answer answer : questionAnswer.getAnswers()) {

					if (answer.getAnswer() != null
							&& !answer.getAnswer().isEmpty()) {

						answer.setQuestion(question);
						answerDao.save(answer);
					}
				}

				// status.setComplete();

				// the choices user selects will be available in the answer
				// object for this question
			} else {
				// else the user will endup in the generated YES/NO Choice or an
				// open ended textbox
			}

			status.setComplete();
			session.setAttribute("successMsg",
					"Question successfully Saved!! <br/> Please Add another!");

			session.removeAttribute("question");
			session.removeAttribute("binding");

			return "redirect:/admin/question/form";
		}
	}

	@RequestMapping(value = "/admin/attachments/{questionId}", method = RequestMethod.GET)
	public void getAttachmentFile(
			@PathVariable("questionId") Integer questionId,
			HttpServletResponse response, HttpSession session) {
		try {
			// get your file as InputStream
			Question question = this.questionDao.findById(questionId);
			String filePath = session.getServletContext().getRealPath("/");
			File fileToDownload = new File(filePath + "data/attachments"
					+ File.separator + question.getFilePath());
			InputStream inputStream = new FileInputStream(fileToDownload);
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileToDownload.getName());
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			// throw new
			// RuntimeException("IOError writing file to output stream");
		}

	}
	
	@RequestMapping(value = "/admin/user/attachments/{userAnswerId}", method = RequestMethod.GET)
	public void getUserAttachmentFile(
			@PathVariable("userAnswerId") Integer userAnswerId,
			HttpServletResponse response, HttpSession session) {
		try {
			// get your file as InputStream
			UserAnswer userAnswer = this.userAnswerDao.findById(userAnswerId);
			String filePath = session.getServletContext().getRealPath("/");
			File fileToDownload = new File(filePath + "data/user/attachments"
					+ File.separator + userAnswer.getAttachmentPath());
			InputStream inputStream = new FileInputStream(fileToDownload);
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileToDownload.getName());
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			// throw new
			// RuntimeException("IOError writing file to output stream");
		}

	}


	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "questionnaire/form", method = RequestMethod.GET)
	public String createQuestionnaire(ModelMap model, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		List<Question> questions = questionDao.findAll();

		model.addAttribute("userName", authentication.getName());
		// model.addAttribute("questions", questions);
		// model.addAttribute("questionnaireForm", new QuestionnaireForm());
		model.addAttribute(
				"questionnaireForm",
				session.getAttribute("questionnaireForm") != null ? session
						.getAttribute("questionnaireForm")
						: new QuestionnaireForm());
		model.put(
				"org.springframework.validation.BindingResult.questionnaireForm",
				session.getAttribute("binding"));

		List<QuestionSheet> questionSheets = new ArrayList<QuestionSheet>();

		for (Question question : questions) {
			QuestionSheet sheet = new QuestionSheet();
			sheet.setQuestion(question);
			sheet.setSelected(false);

			questionSheets.add(sheet);
		}

		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("questionnaires", questionnaires);
		this.loadTreeMenu(model);
		session.removeAttribute("questionnaireForm");
		session.removeAttribute("binding");

		model.addAttribute("questionSheets", questionSheets);
		return "/admin/questionnaire/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "questionnaire/questions/{groupId}", method = RequestMethod.GET, produces = "application/json", consumes = "application/json", headers = { "content-type=application/json" })
	public List<Question> getQuestionnaireQuestions(
			@PathVariable("groupId") Integer groupId) {

		if (groupId != null) {

			return getQuestionsByGroupId(groupId);
		}

		return null;
	}

	private List<Question> getQuestionsByGroupId(Integer groupId) {

		QuestionGroup questionGroup = this.questionGroupDao.findById(groupId);

		List<Questionnaire> questionnaires = new ArrayList<Questionnaire>(
				questionGroup.getQuestionnaires());

		List<Question> questions = new ArrayList<Question>();

		for (Questionnaire questionnaire : questionnaires) {
			questions.add(questionnaire.getQuestion());
		}

		return questions;
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "questionnaire/listByActive/{active}")
	public String questionnaireListByActive(@PathVariable("active") Boolean active, ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findByActive(active);
		for (QuestionGroup group : groups) {

			List<Question> questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}

		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("title", "available questionnaires");
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questionnaireList", questionnaires);
		this.loadTreeMenu(model);
		return "/admin/questionnaire/list";
	}
	
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "questionnaire/list", method = RequestMethod.GET)
	public String questionnaires(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			List<Question> questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}

		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("title", "available questionnaires");
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questionnaireList", questionnaires);
		this.loadTreeMenu(model);
		return "/admin/questionnaire/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "questionnaire/form/{groupId}", method = RequestMethod.GET)
	public String editQuestionnaire(ModelMap model, HttpSession session,
			@PathVariable("groupId") Integer groupId) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		List<Question> questions = questionDao.findAll();

		model.addAttribute("userName", authentication.getName());
		// model.addAttribute("questions", questions);
		// model.addAttribute("questionnaireForm", new QuestionnaireForm());
		model.addAttribute(
				"questionnaireForm",
				session.getAttribute("questionnaireForm") != null ? session
						.getAttribute("questionnaireForm")
						: new QuestionnaireForm());
		model.put(
				"org.springframework.validation.BindingResult.questionnaireForm",
				session.getAttribute("binding"));

		List<QuestionSheet> questionSheets = new ArrayList<QuestionSheet>();

		if (groupId != null) {
			QuestionGroup group = questionGroupDao.findById(groupId);

			QuestionnaireForm questionnaireForm = new QuestionnaireForm();
			questionnaireForm.setCategoryName(group.getName());
			questionnaireForm.setDescription(group.getDescription());
			questionnaireForm.setActive(group.getActive());
			questionnaireForm.setGroupId(groupId);
			model.addAttribute(
					"questionnaireForm",
					session.getAttribute("questionnaireForm") != null ? session
							.getAttribute("questionnaireForm")
							: questionnaireForm);

			for (Question question : questions) {
				if (question.getActive()) {
					QuestionSheet sheet = new QuestionSheet();
					sheet.setQuestion(question);

					// test these
					List<Questionnaire> questionnaires = questionnaireDao
							.findByQuestionAndQuestionGroup(question, group);

					Questionnaire questionnaire = null;

					if (questionnaires != null && questionnaires.size() > 0)
						questionnaire = questionnaires.get(0);

					if (questionnaire == null) {
						sheet.setSelected(false);
					} else {
						sheet.setSelected(true);
					}

					questionSheets.add(sheet);
				}
			}

			model.addAttribute("title", "add questions for " + group.getName());
		}
		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}

		session.removeAttribute("questionnaireForm");
		session.removeAttribute("binding");
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("questionnaires", questionnaires);
		this.loadTreeMenu(model);
		model.addAttribute("questionSheets", questionSheets);
		return "/admin/questionnaire/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "questionnaire/detail/{groupId}", method = RequestMethod.GET)
	public String questionnaire(ModelMap model,
			@PathVariable("groupId") Integer groupId) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		QuestionnaireForm questionnaire = new QuestionnaireForm();
		List<Question> questions = new ArrayList<Question>();

		if (groupId != null) {

			QuestionGroup group = this.questionGroupDao.findById(groupId);
			questionnaire.setCategoryName(group.getName());
			questionnaire.setDescription(group.getDescription());
			questionnaire.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					questionnaire.getQuestions().add(question);
			}
			questionnaire.setQuestionSize(questionnaire.getQuestions().size());
			questionnaire.setGroupId(group.getId());
		}
		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("questionnaires", questionnaires);

		model.addAttribute("title",
				"questionnaire detail for " + questionnaire.getCategoryName());
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questionnaire", questionnaire);
		this.loadTreeMenu(model);
		return "/admin/questionnaire/detail";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "questionnaire/save", method = RequestMethod.POST)
	public String saveQuestionnaire(
			@ModelAttribute("questionnaireForm") @Valid QuestionnaireForm questionnaireForm,
			BindingResult result, HttpSession session, SessionStatus status) {

		if (result.hasErrors()) {

			session.setAttribute("questionnaireForm", questionnaireForm);
			session.setAttribute("binding", result);

			if (questionnaireForm.getGroupId() == null)
				return "redirect:/admin/questionnaire/form";
			else
				return "redirect:/admin/questionnaire/form/"
						+ questionnaireForm.getGroupId();
		} else {

			String description = questionnaireForm.getDescription();
			boolean isActive = questionnaireForm.isActive();
			String categoryName = questionnaireForm.getCategoryName();

			QuestionGroup group = null;// questionGroupDao.getQuestionGroupByName(categoryName);

			Integer groupId = questionnaireForm.getGroupId();

			if (groupId == null) {

				group = new QuestionGroup(categoryName, null, description,
						true, null, null);

				questionGroupDao.save(group);

				List groups = questionGroupDao.findByName(categoryName);

				if (groups != null && groups.size() > 0)
					groupId = ((QuestionGroup) groups.get(0)).getId();
			} else {
				group = this.questionGroupDao.findById(groupId);
				group.setActive(isActive);
				group.setDescription(description);
				group.setName(categoryName);

				this.questionGroupDao.merge(group);
			}

			for (Integer questionId : questionnaireForm.getQuestionIds()) {
				if (questionId != null) {

					Question question = this.questionDao.findById(questionId);

					List<Questionnaire> questionnaires = questionnaireDao
							.findByQuestionAndQuestionGroup(question, group);

					Questionnaire questionnaire = null;

					if (questionnaires != null && questionnaires.size() > 0)
						questionnaire = questionnaires.get(0);

					if (questionnaire != null)
						questionnaireDao.delete(questionnaire);
				}

			}

			// questionnaireDao.createSurveyQuestions(questionnaireForm.getQuestionIds(),
			// groupId, null);
			for (Integer questionId : questionnaireForm.getQuestionIds()) {

				if (questionId != null) {
					Question question = this.questionDao.findById(questionId);
					Questionnaire questionnaire = new Questionnaire();
					questionnaire.setQuestion(question);
					questionnaire.setQuestionGroup(group);
					questionnaire.setTitle(null);

					this.questionnaireDao.save(questionnaire);
				}

			}
			status.setComplete();

			return "redirect:/admin/questionnaire/list";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "questionnaires/delete", method = RequestMethod.POST)
	public String deleteQuestionnaires(
			@ModelAttribute("questionnaireIdsForm") @Valid QuestionnaireIdsForm questionnaireIdsForm,
			BindingResult result, HttpSession session, SessionStatus status) {

		if (result.hasErrors()) {

			session.setAttribute("questionnaireIdsForm", questionnaireIdsForm);
			session.setAttribute("binding", result);
			return "redirect:/admin/questionnaire/list";
		} else {

			for (Integer groupId : questionnaireIdsForm.getGroupIds()) {

				QuestionGroup quesGroup = questionGroupDao.findById(groupId);
				quesGroup.setActive(false);
				questionGroupDao.merge(quesGroup);

				/*
				 * for(Questionnaire questionnaire : questionnaires) {
				 * questionnaireDao.deleteQuestionnaire(questionnaire); }
				 */
			}

			status.setComplete();

			return "redirect:/admin/questionnaire/list";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "questionnaires/delete/{groupId}", method = RequestMethod.GET)
	public String deleteQuestionnaire(@PathVariable("groupId") Integer groupId,
			BindingResult result, HttpSession session, SessionStatus status) {

		if (groupId != null) {

			QuestionGroup quesGroup = questionGroupDao.findById(groupId);

			quesGroup.setActive(false);
			questionGroupDao.merge(quesGroup);
		}

		status.setComplete();
		return "redirect:/admin/questionnaire/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "register", method = RequestMethod.GET)
	public String registerAdmin(ModelMap model, HttpSession session) {

		model.addAttribute(
				"userForm",
				session.getAttribute("userForm") != null ? session
						.getAttribute("userForm") : new UserRegistrationForm());
		model.put("org.springframework.validation.BindingResult.userForm",
				session.getAttribute("binding"));

		// model.addAttribute("userForm", new UserRegistrationForm());
		return "/admin/register";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "new/save", method = RequestMethod.POST)
	public String saveAdmin(ModelMap model,
			@ModelAttribute("userForm") @Valid UserRegistrationForm userForm,
			BindingResult result, SessionStatus status, HttpSession session) {

		new UserValidator().validate(userForm, result);
		if (result.hasErrors()) {

			session.setAttribute("userForm", userForm);
			session.setAttribute("binding", result);
			return "redirect:/admin/register";
		} else {

			Role role = roleDao.getRoleByName("ROLE_ADMIN");

			User user = new User();
			user.setFirstName(userForm.getFirstName());
			user.setLastName(userForm.getLastName());
			user.setEmail(userForm.getEmail());
			user.setRole(role);
			user.setUserName(userForm.getUserName());
			user.setPassword(userForm.getPassword());
			user.setActive(userForm.isActive());

			this.userDao.createUser(user);
			status.setComplete();

			return "redirect:/admin/users";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "users/delete/{userId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("userId") Integer userId,
			BindingResult result, HttpSession session, SessionStatus status) {

		if (userId != null) {

			User user = userDao.getUserByUserId(userId);

			user.setActive(false);
			userDao.updateUser(user);
		}

		status.setComplete();
		return "redirect:/admin/users";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "editProfile")
	public String editProfile(ModelMap model) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		User user = userDao.getUserByUsername(authentication.getName());

		model.addAttribute("user", user);

		model.addAttribute("userName", authentication.getName());

		return Constants.PATH_ADMIN_AUTH + "editProfile";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "/updateProfile", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute("user") @Valid User user,
			BindingResult result, SessionStatus status) {

		if (result.hasErrors()) {
			return "/admin/editProfile";
		} else {

			User originalUser = userDao.getUserByUserId(user.getId());
			originalUser.setFirstName(user.getFirstName());
			originalUser.setLastName(user.getLastName());
			originalUser.setEmail(user.getEmail());
			originalUser.setActive(user.isActive());

			this.userDao.updateUser(originalUser);
			status.setComplete();

			return "/admin/index";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "user/listByActive/{active}")
	public String usersListByActive(@PathVariable("active") Boolean active, HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws ServletException, IOException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		model.addAttribute("userName", authentication.getName());
		
		model.addAttribute("userList", this.userDao.getUsersByActive(active));

		this.loadQuestionMenues(model);
		this.loadTreeMenu(model);
		
		return Constants.PATH_ADMIN_AUTH + "users";

	}
	
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "users")
	public String adminList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws ServletException, IOException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		model.addAttribute("userName", authentication.getName());
		
		model.addAttribute("userList", this.userDao.getUsersList());

		this.loadQuestionMenues(model);
		this.loadTreeMenu(model);
		
		return Constants.PATH_ADMIN_AUTH + "users";

	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "users/form/{userId}")
	public String editUser(@PathVariable("userId") Integer userId,
			ModelMap model) {

		if (userId != null) {
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();

			User user = userDao.getUserByUserId(userId);

			model.addAttribute("user", user);

			model.addAttribute("userName", authentication.getName());

			return Constants.PATH_ADMIN_AUTH + "editProfile";
		}
		return null;
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "users/questionnaireResults")
	public String userQuestionnaireResults(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws ServletException, IOException {

		model.addAttribute("users", userDao.getUsersList());

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		model.addAttribute("userName", authentication.getName());

		this.loadQuestionMenues(model);
		this.loadTreeMenu(model);
		
		return Constants.PATH_ADMIN_AUTH + "userQuestionnaireResults";

	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "changePassword")
	public String changePassword(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		model.addAttribute(
				"changePasswordForm",
				session.getAttribute("changePasswordForm") != null ? session
						.getAttribute("changePasswordForm")
						: new ChangePasswordForm());
		model.put(
				"org.springframework.validation.BindingResult.changePasswordForm",
				session.getAttribute("binding"));

		model.addAttribute("userName", authentication.getName());

		return Constants.PATH_ADMIN_AUTH + "changePassword";
	}

	public void validateChangePassword(ChangePasswordForm changePasswordForm,
			Errors errors) {

		User user = this.userDao
				.getUserByUserId(changePasswordForm.getUserId());

		if (!this.passwordEncoder.encodePassword(
				changePasswordForm.getOldPassword(), user.getUserName())
				.equals(user.getPassword())) {
			errors.rejectValue("password", "valid",
					"old password are not valid");
		}
		if (changePasswordForm.getPassword() == null
				|| changePasswordForm.getConfirmPassword() == null) {
			errors.rejectValue("password", "required",
					"passwords can not be null");
		} else if (!changePasswordForm.getPassword().equals(
				changePasswordForm.getConfirmPassword())) {
			errors.rejectValue("password", "match", "passwords should match");
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "/updatePassword", method = RequestMethod.POST)
	public String updatePassword(
			@ModelAttribute("changePasswordForm") @Valid ChangePasswordForm changePasswordForm,
			BindingResult result, HttpSession session, SessionStatus status) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = userDao.getUserByUsername(authentication.getName());
		changePasswordForm.setUserId(user.getId());
		validateChangePassword(changePasswordForm, result);
		if (result.hasErrors()) {
			session.setAttribute("changePasswordForm", changePasswordForm);
			session.setAttribute("binding", result);
			return "redirect:" + Constants.PATH_ADMIN_AUTH + "changePassword";
		} else {

			user.setPassword(changePasswordForm.getPassword());

			this.userDao.changePassword(user);
			status.setComplete();

			return "redirect:/admin/index";
		}
	}

	@RequestMapping(value = "/admin/questionnairesCompleted/{userId}", method = RequestMethod.GET)
	public String completedUserQuestionnaire(ModelMap model,
			@PathVariable("userId") Integer userId) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		List<UserQuestionnaireForm> questionnaires = new ArrayList<UserQuestionnaireForm>();
		User user = userDao.getUserByUserId(userId);
		List<Integer> groupIds = userAnswerDao
				.getQuestionnaireIdsCompletedByUser(user);
		for (Integer groupId : groupIds) {

			QuestionGroup group = questionGroupDao.findById(groupId);

			List<Question> questions = getQuestionsByGroupId(group.getId());
			UserQuestionnaireForm qForm = new UserQuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setQuestions(questions);
			qForm.setQuestionSize(questions.size());
			qForm.setGroupId(group.getId());

			List<Integer> questionIdsCompleted = userAnswerDao
					.getQuestionIdsCompleted(group.getId(), userId);

			for (Integer questionId : questionIdsCompleted) {

				qForm.getQuestionsCompleted().add(
						questionDao.findById(questionId));
			}
			qForm.setQuestionCompletedSize(questionIdsCompleted.size());

			questionnaires.add(qForm);
		}

		model.addAttribute("userName", authentication.getName());
		model.addAttribute("user", user);
		model.addAttribute("questionnaires", questionnaires);
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		
		this.loadTreeMenu(model);
		
		return "/admin/question/userQuestionnarieList";
	}

	@RequestMapping(value = "/admin/question/questionsCompleted/{groupId}/{userId}")
	public String questionsCompleted(ModelMap model,
			@PathVariable("userId") Integer userId,
			@PathVariable("groupId") Integer groupId) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		model.put("userName", authentication.getName());

		QuestionGroup group = questionGroupDao.findById(groupId);

		UserQuestionnaireGroupCompletedForm questionGroupForm = new UserQuestionnaireGroupCompletedForm();
		questionGroupForm.setCategoryName(group.getName());
		questionGroupForm.setCategoryDescription(group.getDescription());

		User user = userDao.getUserByUserId(userId);

		List<UserQuestionnaireCompletedForm> userQuestionnaireCompletedForms = new ArrayList<UserQuestionnaireCompletedForm>();

		List<Integer> questionIdsCompleted = userAnswerDao
				.getQuestionIdsCompleted(groupId, userId);

		for (Integer questionId : questionIdsCompleted) {

			UserQuestionnaireCompletedForm userQuestionnaireCompletedForm = new UserQuestionnaireCompletedForm();

			Question question = questionDao.findById(questionId);

			userQuestionnaireCompletedForm.setQuestion(question);
			UserAnswerSheetForm userAnswerSheetForm = new UserAnswerSheetForm();

			List<UserAnswer> userAnswers = userAnswerDao
					.getUserAnswersByQuestion(group, question, user);

			if (question.getQuestionType().equals(
					QuestionTypeEnum.MULTICHOICE.name())
					|| question.getQuestionType().equals(
							QuestionTypeEnum.LIST_CHOICE.name())) {

				List<Answer> answers = new ArrayList<Answer>(
						question.getAnswersForQuestion());
				// userAnswerSheetForm.setAnswers(answers);
				// List<UserAnswer> userAnswers =
				// userAnswerDao.getUserAnswersByQuestion(groupId, questionId,
				// user.getId());

				if (question.getQuestionType().equals(
						QuestionTypeEnum.MULTICHOICE.name())) {
					for (Answer answer : answers) {
						boolean found = false;
						for (UserAnswer userAnswer : userAnswers) {
							if (answer.getId().equals(userAnswer.getAnswer()))
								found = true;
							// userAnswerSheetForm.getChosenAnswers().add(answerDao.getAnswerById(userAnswer.getAnswer()));
						}
						if (!found)
							userAnswerSheetForm.getAnswers().add(answer);
					}
				} else {
					userAnswerSheetForm.getAnswers().addAll(answers);
				}
				for (UserAnswer userAnswer : userAnswers) {
					userAnswerSheetForm.getChosenAnswers().add(
							userAnswer.getAnswer());
				}
			} else {

			}

			if (userAnswers != null && userAnswers.size() > 0) {
				userAnswerSheetForm.setComment(userAnswers.get(0).getComment());
				userAnswerSheetForm.setDetailAnswer(userAnswers.get(0)
						.getDetailAnswer());
				userAnswerSheetForm.setBooleanAnswer(userAnswers.get(0)
						.getBooleanAnswer());
				userAnswerSheetForm.setUserAnswerId(userAnswers.get(0).getId());
				
				if(userAnswers.get(0).getAttachmentPath() != null && !userAnswers.get(0).getAttachmentPath().isEmpty()) {
					
					userAnswerSheetForm.setAttachmentPath(userAnswers.get(0).getAttachmentPath());
				}
				
			}

			// questionForm.setCategory(group.getName());
			userQuestionnaireCompletedForm
					.setAnswerSheetForm(userAnswerSheetForm);

			userQuestionnaireCompletedForms.add(userQuestionnaireCompletedForm);
		}

		questionGroupForm
				.setUserQuestionnaireCompletedForms(userQuestionnaireCompletedForms);
		model.addAttribute("user", user);
		model.addAttribute("questionGroupForm", questionGroupForm);

		this.loadQuestionMenues(model);
		this.loadTreeMenu(model);
		
		return "/admin/question/questionnaireCompletedForm";
	}

	
	//attribute
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "attribute/form")
	public String attributeForm(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<FieldTypeEnum> types = new ArrayList<FieldTypeEnum>(
				Arrays.asList(FieldTypeEnum.values()));

		model.addAttribute(
				"attribute",
				session.getAttribute("attribute") != null ? session
						.getAttribute("attribute") : new Attribute());
		model.put("org.springframework.validation.BindingResult.attribute",
				session.getAttribute("binding"));

		model.addAttribute("types", types);
		model.addAttribute("userName", authentication.getName());

		session.removeAttribute("attribute");
		session.removeAttribute("binding");
		
		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "attribute/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "attribute/form/{attributeId}")
	public String attributeEditForm(
			@PathVariable("attributeId") Integer attributeId, ModelMap model,
			HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<FieldTypeEnum> types = new ArrayList<FieldTypeEnum>(
				Arrays.asList(FieldTypeEnum.values()));

		if (attributeId != null) {

			Attribute attribute = this.attributeDAO.findById(attributeId);
			model.addAttribute("attribute", attribute);
		}

		model.put("org.springframework.validation.BindingResult.attribute",
				session.getAttribute("binding"));

		model.addAttribute("types", types);
		model.addAttribute("userName", authentication.getName());
		
		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		return Constants.PATH_ADMIN_AUTH + "attribute/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "attribute/detail/{attributeId}")
	public String attributeDetail(
			@PathVariable("attributeId") Integer attributeId, ModelMap model,
			HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (attributeId != null) {

			Attribute attribute = this.attributeDAO.findById(attributeId);
			model.addAttribute("attribute", attribute);
		}

		model.addAttribute("userName", authentication.getName());
		
		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "attribute/detail";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "/attribute/save", method = RequestMethod.POST)
	public String saveAttribute(
			@ModelAttribute("attribute") @Valid Attribute attribute,
			BindingResult result, SessionStatus status, HttpSession session) {

		if((attribute.getFieldType().equals(FieldTypeEnum.MULTI_SELECT.name()) || attribute.getFieldType().equals(FieldTypeEnum.RADIO.name())
				|| attribute.getFieldType().equals(FieldTypeEnum.DROPDOWN.name())) && (attribute.getValue() == null || attribute.getValue().isEmpty())) {
			
			result.rejectValue("value", "required", "value can't be empty");
		}
		
		if (result.hasErrors()) {

			session.setAttribute("attribute", attribute);
			session.setAttribute("binding", result);
			return "redirect:/admin/attribute/form";
		} else {

			if (attribute.getId() != null)
				this.attributeDAO.merge(attribute);
			else
				this.attributeDAO.save(attribute);
			status.setComplete();

			return "redirect:/admin/attribute/form";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "/attribute/delete/{attributeId}", method = RequestMethod.GET)
	public String deleteAttribute(
			@PathVariable("attributeId") Integer attributeId,
			SessionStatus status) {

		Attribute attribute = this.attributeDAO.findById(attributeId);
		attribute.setActive(false);
		this.attributeDAO.merge(attribute);
		status.setComplete();

		return "redirect:/admin/attribute/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "attribute/list")
	public String attributeList(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Attribute> attributes = this.attributeDAO.findAll();

		model.addAttribute("attributeList", attributes);
		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "attribute/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "attribute/listByActive/{active}")
	public String attributeListByActive(@PathVariable("active") Boolean active, ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Attribute> attributes = this.attributeDAO.findByActive(active);

		model.addAttribute("attributeList", attributes);
		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "attribute/list";
	}
	
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "section/form")
	public String sectionForm(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Attribute> attributes = (List<Attribute>)this.attributeDAO.findByActive(true);

		model.addAttribute("attributeList", attributes);

		model.addAttribute(
				"sectionForm",
				session.getAttribute("sectionForm") != null ? session
						.getAttribute("sectionForm") : new SectionForm());
		model.put("org.springframework.validation.BindingResult.section",
				session.getAttribute("binding"));

		model.addAttribute("userName", authentication.getName());

		session.removeAttribute("sectionForm");
		session.removeAttribute("binding");

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "section/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "/section/delete/{sectionId}", method = RequestMethod.GET)
	public String deleteSection(@PathVariable("sectionId") Integer sectionId,
			SessionStatus status) {

		Section section = this.sectionDAO.findById(sectionId);
		section.setActive(false);
		this.sectionDAO.merge(section);
		status.setComplete();

		return "redirect:/admin/attribute/list";
	}

	@Transactional
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "section/form/{sectionId}")
	public String sectionEditForm(@PathVariable("sectionId") Integer sectionId,
			ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		SectionForm sectionForm = new SectionForm();

		if (sectionId != null) {

			Section section = this.sectionDAO.findById(sectionId);
			sectionForm.setActive(section.getActive());
			sectionForm.setSectionId(section.getId());
			sectionForm.setName(section.getName());
			sectionForm.setDescription(section.getDescription());

			List<SectionAttribute> sectionAttributes = new ArrayList<SectionAttribute>(
					section.getSectionAttributes());

			for (SectionAttribute sectionAttribute : sectionAttributes) {
				sectionForm.getAttributeIds().add(
						sectionAttribute.getAttribute().getId());
			}

		}
		
		List<Attribute> attributes = (List<Attribute>)this.attributeDAO.findByActive(true);

		model.addAttribute("attributeList", attributes);

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		model.addAttribute("sectionForm", sectionForm);
		model.put("org.springframework.validation.BindingResult.sectionForm",
				session.getAttribute("binding"));

		model.addAttribute("userName", authentication.getName());

		return Constants.PATH_ADMIN_AUTH + "section/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "section/detail/{sectionId}")
	public String sectionDetail(@PathVariable("sectionId") Integer sectionId,
			ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		SectionForm sectionForm = new SectionForm();

		if (sectionId != null) {

			Section section = this.sectionDAO.findById(sectionId);
			sectionForm.setActive(section.getActive());
			sectionForm.setSectionId(section.getId());
			sectionForm.setName(section.getName());
			sectionForm.setDescription(section.getDescription());

			List<SectionAttribute> sectionAttributes = new ArrayList<SectionAttribute>(
					section.getSectionAttributes());

			for (SectionAttribute sectionAttribute : sectionAttributes) {
				sectionForm.getAttributeIds().add(
						sectionAttribute.getAttribute().getId());
				sectionForm.getAttributes()
						.add(sectionAttribute.getAttribute());
			}

		}

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		model.addAttribute("section", sectionForm);

		model.addAttribute("userName", authentication.getName());

		return Constants.PATH_ADMIN_AUTH + "section/detail";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "/section/save", method = RequestMethod.POST)
	public String saveSection(
			@ModelAttribute("sectionForm") @Valid SectionForm sectionForm,
			BindingResult result, SessionStatus status, HttpSession session) {

		if (result.hasErrors()) {

			session.setAttribute("sectionForm", sectionForm);
			session.setAttribute("binding", result);
			return "redirect:/admin/section/form";
		} else {

			Section section = null;

			if (sectionForm.getSectionId() != null)
				section = this.sectionDAO.findById(sectionForm.getSectionId());
			else
				section = new Section();

			// section.setId(sectionForm.getSectionId());
			section.setActive(sectionForm.getActive());
			section.setName(sectionForm.getName());
			section.setDescription(sectionForm.getDescription());

			if (sectionForm.getSectionId() != null) {

				this.sectionDAO.merge(section);

				List<SectionAttribute> sectionAttributes = new ArrayList<SectionAttribute>(
						section.getSectionAttributes());

				for (SectionAttribute sectionAttribute : sectionAttributes) {
					this.sectionAttributeDao.delete(sectionAttribute);
				}
			} else {

				this.sectionDAO.save(section);
			}

			for (Integer attributeId : sectionForm.getAttributeIds()) {
				if (attributeId != null) {

					SectionAttribute sectionAttribute = new SectionAttribute();
					Attribute attribute = new Attribute();
					attribute.setId(attributeId);
					sectionAttribute.setAttribute(attribute);
					sectionAttribute.setSection(section);

					this.sectionAttributeDao.save(sectionAttribute);

					section.getSectionAttributes().add(sectionAttribute);
				}
			}

			status.setComplete();

			return "redirect:/admin/section/form";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "section/listByActive/{active}")
	public String sectionListByActive(@PathVariable("active") Boolean active, ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Section> sections = this.sectionDAO.findByActive(active);

		model.addAttribute("sectionList", sections);
		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "section/list";
	}
	
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "section/list")
	public String sectionList(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		List<Section> sections = this.sectionDAO.findAll();
		
		model.addAttribute("sectionList", sections);
		
		model.addAttribute("userName", authentication.getName());

		return Constants.PATH_ADMIN_AUTH + "section/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "template/listByActive/{active}")
	public String templateListByActive(@PathVariable("active") Boolean active, ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Template> templates = this.templateDAO.findByActive(active);

		model.addAttribute("templateList", templates);
		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "template/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "template/list")
	public String templateList(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		List<Template> templates = this.templateDAO.findAll();

		model.addAttribute("templateList", templates);
		
		model.addAttribute("userName", authentication.getName());

		return Constants.PATH_ADMIN_AUTH + "template/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "template/form")
	public String templateForm(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Section> sections = this.sectionDAO.findAll();

		model.addAttribute("sections", sections);

		model.addAttribute(
				"templateForm",
				session.getAttribute("templateForm") != null ? session
						.getAttribute("templateForm") : new TemplateForm());
		model.put("org.springframework.validation.BindingResult.templateForm",
				session.getAttribute("binding"));

		model.addAttribute("userName", authentication.getName());

		session.removeAttribute("templateForm");
		session.removeAttribute("binding");

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "template/form";
	}

	@Transactional
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "template/form/{templateId}")
	public String templateEditForm(
			@PathVariable("templateId") Integer templateId, ModelMap model,
			HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		TemplateForm templateForm = new TemplateForm();

		if (templateId != null) {

			Template template = this.templateDAO.findById(templateId);
			templateForm.setActive(template.getActive());
			templateForm.setTemplateId(template.getId());
			templateForm.setName(template.getName());
			templateForm.setDescription(template.getDescription());

			List<TemplateSection> templateSections = new ArrayList<TemplateSection>(
					template.getTemplateSections());

			for (TemplateSection templateSection : templateSections) {
				templateForm.getSectionIds().add(
						templateSection.getSection().getId());
			}

		}

		List<Section> sections = this.sectionDAO.findAll();

		model.addAttribute("sections", sections);

		model.addAttribute("templateForm", templateForm);
		model.put("org.springframework.validation.BindingResult.templateForm",
				session.getAttribute("binding"));

		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "template/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "template/detail/{templateId}")
	public String templateDetail(
			@PathVariable("templateId") Integer templateId, ModelMap model,
			HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		TemplateForm templateForm = new TemplateForm();

		if (templateForm != null) {

			Template template = this.templateDAO.findById(templateId);
			templateForm.setActive(template.getActive());
			templateForm.setTemplateId(template.getId());
			templateForm.setName(template.getName());
			templateForm.setDescription(template.getDescription());

			List<TemplateSection> templateSections = new ArrayList<TemplateSection>(
					template.getTemplateSections());

			for (TemplateSection templateSection : templateSections) {
				templateForm.getSectionIds().add(
						templateSection.getSection().getId());
				templateForm.getSections().add(templateSection.getSection());
			}

		}

		model.addAttribute("template", templateForm);

		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "template/detail";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "/template/save", method = RequestMethod.POST)
	public String saveTemplate(
			@ModelAttribute("templateForm") @Valid TemplateForm templateForm,
			BindingResult result, SessionStatus status, HttpSession session) {

		if (result.hasErrors()) {

			session.setAttribute("templateForm", templateForm);
			session.setAttribute("binding", result);
			return "redirect:/admin/template/form";
		} else {

			Template template = null;

			if (templateForm.getTemplateId() != null)
				template = templateDAO.findById(templateForm.getTemplateId());
			else
				template = new Template();

			// template.setId(templateForm.getTemplateId());
			template.setActive(templateForm.getActive());
			template.setName(templateForm.getName());
			template.setDescription(templateForm.getDescription());

			if (templateForm.getTemplateId() != null) {

				this.templateDAO.merge(template);

				List<TemplateSection> tempalteSections = new ArrayList<TemplateSection>(
						template.getTemplateSections());

				for (TemplateSection templateSection : tempalteSections) {
					templateSectionDAO.delete(templateSection);
				}
			} else {

				this.templateDAO.save(template);
			}

			for (Integer sectionId : templateForm.getSectionIds()) {
				if (sectionId != null) {

					TemplateSection templateSection = new TemplateSection();
					Section section = new Section();
					section.setId(sectionId);
					templateSection.setSection(section);
					templateSection.setTemplate(template);

					this.templateSectionDAO.save(templateSection);

					template.getTemplateSections().add(templateSection);
				}
			}

			status.setComplete();

			return "redirect:/admin/template/form";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "/template/delete/{templateId}", method = RequestMethod.GET)
	public String deleteTemplate(
			@PathVariable("templateId") Integer templateId, SessionStatus status) {

		Template template = this.templateDAO.findById(templateId);
		template.setActive(false);
		this.templateDAO.merge(template);
		status.setComplete();

		return "redirect:/admin/template/list";
	}

	// status


	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "status/listByActive/{active}")
	public String statusListByActive(@PathVariable("active") Boolean active, ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Status> statuses = this.statusDAO.findByActive(active);

		model.addAttribute("statusList", statuses);
		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "status/list";
	}
	
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "status/list")
	public String statusList(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		List<Status> statuses = this.statusDAO.findAll();

		model.addAttribute("statusList", statuses);
		
		model.addAttribute("userName", authentication.getName());

		return Constants.PATH_ADMIN_AUTH + "status/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "status/form")
	public String statusForm(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		model.addAttribute(
				"status",
				session.getAttribute("status") != null ? session
						.getAttribute("status") : new Status());
		model.put("org.springframework.validation.BindingResult.status",
				session.getAttribute("binding"));

		model.addAttribute("userName", authentication.getName());

		session.removeAttribute("status");
		session.removeAttribute("binding");

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "status/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "/status/save", method = RequestMethod.POST)
	public String saveStatus(@ModelAttribute("status") @Valid Status status,
			BindingResult result, SessionStatus sessionStatus,
			HttpSession session) {

		if (result.hasErrors()) {

			session.setAttribute("status", status);
			session.setAttribute("binding", result);
			return "redirect:/admin/status/form";
		} else {

			if (status.getId() != null)
				this.statusDAO.merge(status);
			else
				this.statusDAO.save(status);

			sessionStatus.setComplete();

			return "redirect:/admin/status/form";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "status/form/{statusId}")
	public String statusEditForm(@PathVariable("statusId") Integer statusId,
			ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (statusId != null) {

			Status status = this.statusDAO.findById(statusId);
			model.addAttribute("status", status);
		}

		model.put("org.springframework.validation.BindingResult.status",
				session.getAttribute("binding"));
		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "status/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "status/detail/{statusId}")
	public String statusDetail(@PathVariable("statusId") Integer statusId,
			ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (statusId != null) {

			Status status = this.statusDAO.findById(statusId);
			model.addAttribute("status", status);
		}

		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "status/detail";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "/status/delete/{statusId}", method = RequestMethod.GET)
	public String deleteStatus(@PathVariable("statusId") Integer statusId,
			SessionStatus Sstatus) {

		Status status = this.statusDAO.findById(statusId);
		status.setActive(false);
		this.statusDAO.merge(status);
		Sstatus.setComplete();

		return "redirect:/admin/status/list";
	}

	// asset type



	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "assetType/listByActive/{active}")
	public String assetTypeListByActive(@PathVariable("active") Boolean active, ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<TargetType> targetTypes = this.targetTypeDao.findByActive(active);

		model.addAttribute("targetTypeList", targetTypes);
		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "assetType/list";
	}
	
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "assetType/list")
	public String assetTypeList(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<TargetType> targetTypes = this.targetTypeDao.findAll();

		model.addAttribute("targetTypeList", targetTypes);
		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "assetType/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "assetType/form")
	public String assetTypeForm(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		model.addAttribute(
				"targetTypeForm",
				session.getAttribute("targetTypeForm") != null ? session
						.getAttribute("targetTypeForm") : new TargetTypeForm());
		model.put(
				"org.springframework.validation.BindingResult.targetTypeForm",
				session.getAttribute("binding"));

		model.addAttribute("userName", authentication.getName());

		model.addAttribute("activeTemplates", this.templateDAO.findByActive(true));
		
		session.removeAttribute("targetTypeForm");
		session.removeAttribute("binding");

		return Constants.PATH_ADMIN_AUTH + "assetType/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "assetType/form/{assetTypeId}")
	public String assetTypeEditForm(
			@PathVariable("assetTypeId") Integer assetTypeId, ModelMap model,
			HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (assetTypeId != null) {

			TargetType assetType = this.targetTypeDao.findById(assetTypeId);
			TargetTypeForm targetTypeForm = new TargetTypeForm();
			targetTypeForm.setTargetTypeId(assetTypeId);
			targetTypeForm.setName(assetType.getName());
			targetTypeForm.setDescription(assetType.getDescription());
			targetTypeForm.setTemplateId(assetType.getTemplate().getId());
			targetTypeForm.setActive(assetType.getActive());

			for (TargetTypeStatus status : assetType.getTargetTypeStatuses()) {

				targetTypeForm.getTargetTypeStatusIds().add(
						status.getStatus().getId());
			}

			model.addAttribute("targetTypeForm", targetTypeForm);
		}

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		model.put(
				"org.springframework.validation.BindingResult.targetTypeForm",
				session.getAttribute("binding"));
		model.addAttribute("userName", authentication.getName());

		model.addAttribute("activeTemplates", this.templateDAO.findByActive(true));
		
		return Constants.PATH_ADMIN_AUTH + "assetType/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "assetType/detail/{assetTypeId}")
	public String assetTypeDetail(
			@PathVariable("assetTypeId") Integer assetTypeId, ModelMap model,
			HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (assetTypeId != null) {

			TargetType assetType = this.targetTypeDao.findById(assetTypeId);
			TargetTypeForm targetTypeForm = new TargetTypeForm();
			targetTypeForm.setTargetTypeId(assetTypeId);
			targetTypeForm.setName(assetType.getName());
			targetTypeForm.setDescription(assetType.getDescription());
			targetTypeForm.setTemplateId(assetType.getTemplate().getId());
			targetTypeForm.setTemplate(assetType.getTemplate());
			targetTypeForm.setActive(assetType.getActive());

			for (TargetTypeStatus status : assetType.getTargetTypeStatuses()) {

				targetTypeForm.getTargetTypeStatusIds().add(
						status.getStatus().getId());
				targetTypeForm.getStatuses().add(status.getStatus());
			}

			model.addAttribute("targetType", targetTypeForm);
		}

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "assetType/detail";
	}

	/*
	 * public void validateAssetType(TargetTypeForm targetType, Errors errors) {
	 * 
	 * if(targetType.getTemplateId() == null) { errors.rejectValue("template",
	 * "required", "template is required"); }
	 * if(targetType.getTargetTypeStatusIds() == null ||
	 * targetType.getTargetTypeStatusIds().size() <= 0) {
	 * errors.rejectValue("status", "required", "select one or more status!"); }
	 * }
	 */

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "/assetType/save", method = RequestMethod.POST)
	public String saveAssetType(
			@ModelAttribute("targetTypeForm") @Valid TargetTypeForm targetTypeForm,
			BindingResult result, SessionStatus sessionStatus,
			HttpSession session) {

		// validateAssetType(targetTypeForm, result);
		if (result.hasErrors()) {

			session.setAttribute("targetTypeForm", targetTypeForm);
			session.setAttribute("binding", result);
			return "redirect:/admin/assetType/form";
		} else {

			TargetType targetType = null;

			if (targetTypeForm.getTargetTypeId() != null) {
				targetType = this.targetTypeDao.findById(targetTypeForm
						.getTargetTypeId());
			} else {
				targetType = new TargetType();
			}

			if (targetTypeForm.getTemplateId() != null)
				targetType.setTemplate(templateDAO.findById(targetTypeForm
						.getTemplateId()));

			targetType.setName(targetTypeForm.getName());
			targetType.setDescription(targetTypeForm.getDescription());
			targetType.setActive(targetTypeForm.isActive());

			if (targetTypeForm.getTargetTypeId() != null) {

				this.targetTypeDao.merge(targetType);

				List<TargetTypeStatus> targetStatus = new ArrayList<TargetTypeStatus>(
						targetType.getTargetTypeStatuses());

				for (TargetTypeStatus targetTypeStatus : targetStatus) {

					this.targetTypeStatusDAO.delete(targetTypeStatus);
				}
			} else {

				this.targetTypeDao.save(targetType);
			}

			for (Integer statusId : targetTypeForm.getTargetTypeStatusIds()) {

				if (statusId != null) {

					Status status = this.statusDAO.findById(statusId);
					if (status != null) {
						TargetTypeStatus targetTypeStatus = new TargetTypeStatus();
						targetTypeStatus.setTargetType(targetType);
						targetTypeStatus.setStatus(status);

						this.targetTypeStatusDAO.save(targetTypeStatus);
					}
				}

			}
			/*
			 * if(assetType.getStatus().getId() != null)
			 * assetType.setStatus(statusDAO
			 * .findById(assetType.getStatus().getId()));
			 */

			sessionStatus.setComplete();

			return "redirect:/admin/assetType/form";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "/assetType/delete/{assetTypeId}", method = RequestMethod.GET)
	public String deleteAssetType(
			@PathVariable("assetTypeId") Integer assetTypeId,
			SessionStatus Sstatus) {

		TargetType targetType = this.targetTypeDao.findById(assetTypeId);
		targetType.setActive(false);
		this.targetTypeDao.merge(targetType);
		Sstatus.setComplete();

		return "redirect:/admin/assetType/list";
	}

	// asset
	

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "asset/listByActive/{active}")
	public String assetListByActive(@PathVariable("active") Boolean active, ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Asset> assets = this.assetDAO.findByActive(active);

		model.addAttribute("assetList", assets);
		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "asset/list";
	}
	
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "asset/list")
	public String assetList(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Asset> assets = this.assetDAO.findAll();
		List<TargetType> assetTypes = this.targetTypeDao.findByActive(true);

		model.addAttribute("assetTypes", assetTypes);
		model.addAttribute("assetList", assets);
		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "asset/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "asset/template/{assetTypeId}/{assetId}", method = RequestMethod.GET, produces = "application/json", consumes = "application/json", headers = { "content-type=application/json" })
	public @ResponseBody AssetTypeTemplateForm getAssetTypeTemplate(
			@PathVariable("assetTypeId") Integer assetTypeId,
			@PathVariable("assetId") Integer assetId, HttpServletRequest request) {
		// assetTypeId = Integer.valueOf(
		// request.getAttribute("assetTypeId").toString() );
		if (assetTypeId != null) {

			TargetType targetType = this.targetTypeDao.findById(assetTypeId);

			Template template = targetType.getTemplate();

			if (template != null) {
				AssetTypeTemplateForm assetTemplate = new AssetTypeTemplateForm();
				assetTemplate.setActive(template.getActive());
				assetTemplate.setName(template.getName());
				assetTemplate.setDescription(template.getDescription());

				for (TemplateSection templateSection : template
						.getTemplateSections()) {

					Section section = templateSection.getSection();

					if (section.getSectionAttributes() != null
							&& section.getSectionAttributes().size() > 0) {

						SectionAttributeForm sectionAttributeForm = new SectionAttributeForm();
						sectionAttributeForm.setName(section.getName());
						sectionAttributeForm.setDescription(section
								.getDescription());
						sectionAttributeForm.setActive(section.getActive());
						sectionAttributeForm.setSectionId(section.getId());

						for (SectionAttribute sectionAttribute : section
								.getSectionAttributes()) {

							Attribute attribute = sectionAttribute
									.getAttribute();
							AttributeForm attributeForm = new AttributeForm();
							attributeForm.setActive(attribute.getActive());
							attributeForm.setLabel(attribute.getLabel());
							attributeForm.setDescription(attribute
									.getDescription());
							attributeForm
									.setFieldType(attribute.getFieldType());
							attributeForm.setAttributeId(attribute.getId());
							
							attributeForm.setValue(attribute.getValue());

							if (assetId != null && assetId != -1) {
								Asset asset = this.assetDAO.findById(assetId);
								for (AssetAttribute assetAttribute : asset
										.getAssetAttributes()) {
									if (assetAttribute != null
											&& assetAttribute.getAttribute()
													.getId()
													.equals(attribute.getId())) {
										attributeForm.setValue(assetAttribute
												.getValue());
									}
								}
								// AssetAttribute assetAttribute =
								// (AssetAttribute)
								// this.assetAttributeDAO.findByExample(new
								// AssetAttribute(asset, section, attribute,
								// null)).get(0);
								// if(assetAttribute != null &&
								// assetAttribute.getAttribute().getId().equals(attribute.getId()))
								// {
								// attributeForm.setValue(assetAttribute.getValue());
								// }
							}

							sectionAttributeForm.getAttributes().add(
									attributeForm);

						}

						assetTemplate.getSectionAttributes().add(
								sectionAttributeForm);
					}

				}

				return assetTemplate;
			}
		}

		return null;
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "asset/status/{assetTypeId}", method = RequestMethod.GET, produces = "application/json", consumes = "application/json", headers = { "content-type=application/json" })
	public @ResponseBody List<StatusForm> getAssetTypeStatus(
			@PathVariable("assetTypeId") Integer assetTypeId,
			HttpServletRequest request) {
		// assetTypeId = Integer.valueOf(
		// request.getAttribute("assetTypeId").toString() );
		if (assetTypeId != null) {
			TargetType targetType = this.targetTypeDao.findById(assetTypeId);
			List<TargetTypeStatus> targetTypeStatus = new ArrayList<TargetTypeStatus>(
					targetType.getTargetTypeStatuses());
			List<StatusForm> statuses = new ArrayList<StatusForm>();
			for (TargetTypeStatus status : targetTypeStatus) {
				StatusForm statusForm = new StatusForm();
				statusForm.setName(status.getStatus().getName());
				statusForm.setDescription(status.getStatus().getDescription());
				statusForm.setStatusId(status.getStatus().getId());
				statuses.add(statusForm);
			}

			return statuses;
		}

		return null;

	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "asset/form")
	public String assetForm(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		model.addAttribute(
				"assetForm",
				session.getAttribute("assetForm") != null ? session
						.getAttribute("assetForm") : new AssetForm());
		model.put("org.springframework.validation.BindingResult.assetForm",
				session.getAttribute("binding"));

		//List<Status> statuses = this.statusDAO.findAll();
		List<TargetType> assetTypes = this.targetTypeDao.findByActive(true);

		//model.addAttribute("statuses", statuses);
		model.addAttribute("assetTypes", assetTypes);

		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		session.removeAttribute("asset");
		session.removeAttribute("binding");

		return Constants.PATH_ADMIN_AUTH + "asset/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "assetForm/{assetTypeId}")
	public String assetFormByType(@PathVariable("assetTypeId") Integer assetTypeId, ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		AssetForm assetForm = session.getAttribute("assetForm") != null ? (AssetForm)session
				.getAttribute("assetForm") : new AssetForm();
		
		model.addAttribute(
				"assetForm", assetForm);
		model.put("org.springframework.validation.BindingResult.assetForm",
				session.getAttribute("binding"));
		
		TargetType targetType = this.targetTypeDao.findById(assetTypeId);

		assetForm.setTargetType(targetType);
		
		//List<Status> statuses = this.statusDAO.findAll();
		List<TargetType> assetTypes = this.targetTypeDao.findByActive(true);

		//model.addAttribute("statuses", statuses);
		model.addAttribute("assetTypes", assetTypes);

		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		session.removeAttribute("asset");
		session.removeAttribute("binding");

		return Constants.PATH_ADMIN_AUTH + "asset/form";
	}
	public void validateAsset(AssetForm asset, Errors errors) {

		if (asset.getTargetType().getId() == null) {
			errors.rejectValue("targetType", "required",
					"asset type is required");
		}
		if (asset.getStatus().getId() == null) {
			errors.rejectValue("status", "required", "status is required");
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "/asset/save", method = RequestMethod.POST)
	public String saveAsset(
			@ModelAttribute("assetForm") @Valid AssetForm assetForm,
			BindingResult result, SessionStatus sessionStatus,
			HttpSession session) {

		validateAsset(assetForm, result);

		if (result.hasErrors()) {

			session.setAttribute("assetForm", assetForm);
			session.setAttribute("binding", result);
			return "redirect:/admin/asset/form";
		} else {

			Asset asset = new Asset();

			asset.setName(assetForm.getName());
			asset.setDescription(assetForm.getDescription());
			asset.setActive(assetForm.getActive());

			if (assetForm.getAssetId() != null) {
				asset = this.assetDAO.findById(assetForm.getAssetId());
			}

			if (assetForm.getTargetType().getId() != null)
				asset.setTargetType(targetTypeDao.findById(assetForm
						.getTargetType().getId()));
			if (assetForm.getStatus().getId() != null)
				asset.setStatus(statusDAO.findById(assetForm.getStatus()
						.getId()));

			if (asset.getId() != null) {
				this.assetDAO.merge(asset);

				for (AssetAttribute assetAttribute : asset.getAssetAttributes()) {
					this.assetAttributeDAO.delete(assetAttribute);
				}
			} else
				this.assetDAO.save(asset);

			for (AssetAttribute attribute : assetForm.getAssetAttributes()) {

				if (attribute.getValue() != null
						&& !attribute.getValue().isEmpty()) {
					AssetAttribute assetAttribute = new AssetAttribute();

					assetAttribute.setAsset(asset);
					assetAttribute.setAttribute(this.attributeDAO
							.findById(attribute.getAttribute().getId()));
					assetAttribute.setSection(this.sectionDAO
							.findById(attribute.getSection().getId()));
					assetAttribute.setValue(attribute.getValue());

					this.assetAttributeDAO.save(assetAttribute);
				}
			}

			sessionStatus.setComplete();

			return "redirect:/admin/asset/form";
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "asset/form/{assetId}")
	public String assetEditForm(@PathVariable("assetId") Integer assetId,
			ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (assetId != null) {

			Asset asset = this.assetDAO.findById(assetId);
			AssetForm assetForm = new AssetForm();
			assetForm.setAssetId(assetId);
			assetForm.setName(asset.getName());
			assetForm.setDescription(asset.getDescription());
			assetForm.setActive(asset.getActive());
			assetForm.setTargetType(asset.getTargetType());
			assetForm.setStatus(asset.getStatus());

			assetForm.setAssetAttributes(new ArrayList<AssetAttribute>(asset
					.getAssetAttributes()));
			model.addAttribute("assetForm", assetForm);
		}

		List<Status> statuses = this.statusDAO.findAll();
		List<TargetType> assetTypes = this.targetTypeDao.findByActive(true);	

		model.addAttribute("statuses", statuses);
		model.addAttribute("assetTypes", assetTypes);

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
			
		model.put("org.springframework.validation.BindingResult.assetForm",
				session.getAttribute("binding"));
		model.addAttribute("userName", authentication.getName());

		return Constants.PATH_ADMIN_AUTH + "asset/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "asset/detail/{assetId}")
	public String assetDetail(@PathVariable("assetId") Integer assetId,
			ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (assetId != null) {

			Asset asset = this.assetDAO.findById(assetId);
			AssetForm assetForm = new AssetForm();
			assetForm.setAssetId(assetId);
			assetForm.setName(asset.getName());
			assetForm.setDescription(asset.getDescription());
			assetForm.setActive(asset.getActive());
			assetForm.setTargetType(asset.getTargetType());
			assetForm.setStatus(asset.getStatus());

			assetForm.setAssetAttributes(new ArrayList<AssetAttribute>(asset
					.getAssetAttributes()));

			Template template = asset.getTargetType().getTemplate();

			if (template != null) {
				AssetTypeTemplateForm assetTemplate = new AssetTypeTemplateForm();
				assetTemplate.setActive(template.getActive());
				assetTemplate.setName(template.getName());
				assetTemplate.setDescription(template.getDescription());

				for (TemplateSection templateSection : template
						.getTemplateSections()) {

					Section section = templateSection.getSection();

					if (section.getSectionAttributes() != null
							&& section.getSectionAttributes().size() > 0) {

						SectionAttributeForm sectionAttributeForm = new SectionAttributeForm();
						sectionAttributeForm.setName(section.getName());
						sectionAttributeForm.setDescription(section
								.getDescription());
						sectionAttributeForm.setActive(section.getActive());
						sectionAttributeForm.setSectionId(section.getId());

						for (SectionAttribute sectionAttribute : section
								.getSectionAttributes()) {

							Attribute attribute = sectionAttribute
									.getAttribute();
							AttributeForm attributeForm = new AttributeForm();
							attributeForm.setActive(attribute.getActive());
							attributeForm.setLabel(attribute.getLabel());
							attributeForm.setDescription(attribute
									.getDescription());
							attributeForm
									.setFieldType(attribute.getFieldType());
							attributeForm.setAttributeId(attribute.getId());

							if (assetId != null && assetId != -1) {

								for (AssetAttribute assetAttribute : asset
										.getAssetAttributes()) {
									if (assetAttribute != null
											&& assetAttribute.getAttribute()
													.getId()
													.equals(attribute.getId())) {
										attributeForm.setValue(assetAttribute
												.getValue());
									}
								}
								// AssetAttribute assetAttribute =
								// (AssetAttribute)
								// this.assetAttributeDAO.findByExample(new
								// AssetAttribute(asset, section, attribute,
								// null)).get(0);
								// if(assetAttribute != null &&
								// assetAttribute.getAttribute().getId().equals(attribute.getId()))
								// {
								// attributeForm.setValue(assetAttribute.getValue());
								// }
							}

							sectionAttributeForm.getAttributes().add(
									attributeForm);

						}

						assetTemplate.getSectionAttributes().add(
								sectionAttributeForm);
					}

				}

				model.addAttribute("assetTemplate", assetTemplate);

			}

			model.addAttribute("asset", assetForm);
		}

		model.addAttribute("userName", authentication.getName());

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return Constants.PATH_ADMIN_AUTH + "asset/detail";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "/asset/delete/{assetId}", method = RequestMethod.GET)
	public String deleteAsset(@PathVariable("assetId") Integer assetId,
			SessionStatus Sstatus) {

		Asset asset = this.assetDAO.findById(assetId);
		asset.setActive(false);
		this.assetDAO.merge(asset);
		Sstatus.setComplete();

		return "redirect:/admin/asset/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "/asset/questionnaire/{assetId}", method = RequestMethod.GET)
	public String assetQuestionnaire(@PathVariable("assetId") Integer assetId,
			ModelMap model, SessionStatus Sstatus) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		Asset asset = this.assetDAO.findById(assetId);
		model.addAttribute("asset", asset);

		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			List<Question> questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}

		model.addAttribute("title", "available questionnaires");
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questionnaires", questionnaires);

		this.loadTreeMenu(model);
		this.loadQuestionMenues(model);
		
		return "/admin/asset/questionnaire";
	}

	// campaign
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "campaign/listByActive/{active}")
	public String campaignListByActive(@PathVariable("active") Boolean active, ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Campaign> campaigns = this.campaignDAO.findAll();

		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findByActive(active);
		for (QuestionGroup group : groups) {
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}

		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("questionnaires", questionnaires);
		model.addAttribute("campaignList", campaigns);
		model.addAttribute("userName", authentication.getName());
		this.loadTreeMenu(model);
		return Constants.PATH_ADMIN_AUTH + "campaign/list";
	}
	
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "campaign/list")
	public String campaignList(ModelMap model, HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Campaign> campaigns = this.campaignDAO.findAll();

		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}

		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("questionnaires", questionnaires);
		model.addAttribute("campaignList", campaigns);
		model.addAttribute("userName", authentication.getName());
		this.loadTreeMenu(model);
		return Constants.PATH_ADMIN_AUTH + "campaign/list";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "campaign/form")
	public String campaignQuestionnaireForm(ModelMap model, HttpSession session) {

		List<Asset> assets = this.assetDAO.findAll();
		List<Status> statuses = this.statusDAO.findAll();
		List<User> users = this.userDao.getUsersList();

		model.addAttribute("statuses", statuses);
		model.addAttribute("assets", assets);
		model.addAttribute("users", users);

		model.addAttribute(
				"campaignForm",
				session.getAttribute("campaignForm") != null ? session
						.getAttribute("campaignForm") : new CampaignForm());
		model.put("org.springframework.validation.BindingResult.campaignForm",
				session.getAttribute("binding"));

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();

		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			List<Question> questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}

		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("questionnaires", questionnaires);
		model.addAttribute("title", "prepare campaign form");
		model.addAttribute("userName", authentication.getName());
		this.loadTreeMenu(model);
		return Constants.PATH_ADMIN_AUTH + "campaign/form";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "campaign/detail/{campaignId}")
	public String campaignDetail(
			@PathVariable("campaignId") Integer campaignId, ModelMap model,
			HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();

		if (campaignId != null) {

			Campaign campaign = this.campaignDAO.findById(campaignId);

			CampaignForm campaignForm = new CampaignForm();
			campaignForm.setName(campaign.getName());
			campaignForm.setDescription(campaign.getDescription());
			campaignForm.setActive(campaign.getActive());
			campaignForm.setAsset(campaign.getAsset());
			campaignForm.setStatus(campaign.getStatus());

			List<TargetQuestionnaire> targetQuestionnaires = new ArrayList<TargetQuestionnaire>(
					campaign.getTargetQuestionnaires());

			for (TargetQuestionnaire targetQuestionnaire : targetQuestionnaires) {
				campaignForm.getQuestionnaireIds().add(
						targetQuestionnaire.getQuestionGroup().getId());
			}

			List<UserCampaign> userCampaigns = new ArrayList<UserCampaign>(
					campaign.getCampaignsForUser());

			for (UserCampaign userCampaign : userCampaigns) {
				campaignForm.getUsers().add(userCampaign.getUser());
			}

			List<QuestionGroup> groups = questionGroupDao.findAll();
			for (QuestionGroup group : groups) {

				List<Question> questions = getQuestionsByGroupId(group.getId());
				QuestionnaireForm qForm = new QuestionnaireForm();
				qForm.setCategoryName(group.getName());
				qForm.setDescription(group.getDescription());
				qForm.setActive(group.getActive());
				for (Question question : questions) {
					if (question.getActive())
						qForm.getQuestions().add(question);
				}
				qForm.setQuestionSize(qForm.getQuestions().size());
				qForm.setGroupId(group.getId());
				questionnaires.add(qForm);
			}

			model.addAttribute("campaign", campaignForm);
		}

		model.addAttribute("title", "prepare campaign form");
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questionnaires", questionnaires);
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		this.loadTreeMenu(model);
		return Constants.PATH_ADMIN_AUTH + "campaign/detail";
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "campaign/form/{campaignId}")
	public String editCampaignQuestionnaireForm(
			@PathVariable("campaignId") Integer campaignId, ModelMap model,
			HttpSession session) {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Asset> assets = this.assetDAO.findAll();
		List<Status> statuses = this.statusDAO.findAll();
		List<User> users = this.userDao.getUsersList();

		model.addAttribute("statuses", statuses);
		model.addAttribute("assets", assets);
		model.addAttribute("users", users);

		model.put("org.springframework.validation.BindingResult.campaignForm",
				session.getAttribute("binding"));

		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();

		if (campaignId != null) {

			Campaign campaign = this.campaignDAO.findById(campaignId);

			CampaignForm campaignForm = new CampaignForm();
			campaignForm.setName(campaign.getName());
			campaignForm.setDescription(campaign.getDescription());
			campaignForm.setActive(campaign.getActive());
			campaignForm.setAsset(campaign.getAsset());
			campaignForm.setStatus(campaign.getStatus());

			List<TargetQuestionnaire> targetQuestionnaires = new ArrayList<TargetQuestionnaire>(
					campaign.getTargetQuestionnaires());

			for (TargetQuestionnaire targetQuestionnaire : targetQuestionnaires) {
				campaignForm.getQuestionnaireIds().add(
						targetQuestionnaire.getQuestionGroup().getId());
			}

			List<UserCampaign> userCampaigns = new ArrayList<UserCampaign>(
					campaign.getCampaignsForUser());

			for (UserCampaign userCampaign : userCampaigns) {
				campaignForm.getUsers().add(userCampaign.getUser());
			}

			List<QuestionGroup> groups = questionGroupDao.findAll();
			for (QuestionGroup group : groups) {

				List<Question> questions = getQuestionsByGroupId(group.getId());
				QuestionnaireForm qForm = new QuestionnaireForm();
				qForm.setCategoryName(group.getName());
				qForm.setDescription(group.getDescription());
				qForm.setActive(group.getActive());
				for (Question question : questions) {
					if (question.getActive())
						qForm.getQuestions().add(question);
				}
				qForm.setQuestionSize(qForm.getQuestions().size());
				qForm.setGroupId(group.getId());
				questionnaires.add(qForm);
			}

			model.addAttribute(
					"campaignForm",
					session.getAttribute("campaignForm") != null ? session
							.getAttribute("campaignForm") : campaignForm);
		}

		model.addAttribute("title", "prepare campaign form");
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("questionnaires", questionnaires);
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		this.loadTreeMenu(model);
		
		return Constants.PATH_ADMIN_AUTH + "campaign/form";
	}

	public void validateCampaign(CampaignForm campaignForm, Errors errors) {

		/*
		 * if(campaignForm.getStatus().getId() == null) {
		 * errors.rejectValue("status", "required", "status is required"); }
		 */
		if (campaignForm.getAsset().getId() == null) {
			errors.rejectValue("asset", "required", "asset is required");
		}
	}

	@RequestMapping(value = Constants.PATH_ADMIN_AUTH + "/campaign/save", method = RequestMethod.POST)
	public String saveCampaign(
			@ModelAttribute("campaignForm") @Valid CampaignForm campaignForm,
			BindingResult result, SessionStatus sessionStatus,
			HttpSession session) {

		validateCampaign(campaignForm, result);

		if (result.hasErrors()) {

			session.setAttribute("campaignForm", campaignForm);
			session.setAttribute("binding", result);
			return "redirect:/admin/campaign/form";
		} else {

			Campaign campaign = null;
			if (campaignForm.getCampaignId() != null) {

				campaign = this.campaignDAO.findById(campaignForm
						.getCampaignId());
			} else {

				campaign = new Campaign();
			}

			if (campaignForm.getAsset().getId() != null) {

				campaign.setAsset(this.assetDAO.findById(campaignForm
						.getAsset().getId()));
			}
			if (campaignForm.getStatus().getId() != null) {

				campaign.setStatus(this.statusDAO.findById(campaignForm
						.getStatus().getId()));
			}
			campaign.setActive(campaignForm.getActive());
			campaign.setName(campaignForm.getName());
			campaign.setDescription(campaignForm.getDescription());
			campaign.setCampaignDate(Calendar.getInstance().getTime()); 

			if (campaignForm.getCampaignId() != null)
				this.campaignDAO.merge(campaign);
			else
				this.campaignDAO.save(campaign);

			List<TargetQuestionnaire> targetQuestionnaires = new ArrayList<TargetQuestionnaire>(
					campaign.getTargetQuestionnaires());

			for (TargetQuestionnaire targetQuestionnaire : targetQuestionnaires) {

				this.targetQuestionnaireWDAO.delete(targetQuestionnaire);
			}

			List<UserCampaign> userCampaigns = new ArrayList<UserCampaign>(
					campaign.getCampaignsForUser());

			for (UserCampaign userCampaign : userCampaigns) {

				this.userCampaignDAO.delete(userCampaign);
			}

			for (Integer questionnaireId : campaignForm.getQuestionnaireIds()) {

				if (questionnaireId != null) {

					TargetQuestionnaire targetQuestionnaire = new TargetQuestionnaire();

					QuestionGroup group = this.questionGroupDao
							.findById(questionnaireId);
					targetQuestionnaire.setQuestionGroup(group);
					targetQuestionnaire.setCampaign(campaign);
					targetQuestionnaire.setActive(true);

					this.targetQuestionnaireWDAO.save(targetQuestionnaire);
				}
			}

			for (User user : campaignForm.getUsers()) {

				if (user.getId() != null) {

					user = this.userDao.getUserByUserId(user.getId());
					UserCampaign userCampaign = new UserCampaign(user,
							campaign, true);

					this.userCampaignDAO.save(userCampaign);
				}
			}

			sessionStatus.setComplete();

			return "redirect:/admin/campaign/list";
		}
	}

	// IMPORT CSV DATA

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = Constants.PATH_ADMIN_AUTH
			+ "question/uploadCSVFile", method = RequestMethod.POST)
	public String uploadCSVFile(
			@ModelAttribute("csvUploadForm") @Valid CSVUploadForm csvUploadForm,
			BindingResult result, ModelMap model, HttpSession session) {

		if (result.hasErrors()) {

			session.setAttribute("csvUploadForm", csvUploadForm);
			session.setAttribute("binding", result);
			return "redirect:/admin/question/list";
		} else {

			if (!csvUploadForm.getFile().isEmpty()) {
				try {
					byte[] bytes = csvUploadForm.getFile().getBytes();

					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					File dir = new File(rootPath + File.separator + "tmpFiles");
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + csvUploadForm.getFile().getName());
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					this.readCSVFile(serverFile.getPath(),
							csvUploadForm.getQuestionType());

					logger.info("Server File Location="
							+ serverFile.getAbsolutePath());

					model.addAttribute(
							"success",
							"You successfully uploaded file="
									+ csvUploadForm.getFile().getName());

					return Constants.PATH_ADMIN_AUTH + "question/list";
				} catch (Exception e) {
					return "You failed to upload " + csvUploadForm.getFile().getName()
							+ " => " + e.getMessage();
				}
			} else {

				result.rejectValue("file", "You failed to upload "
						+ csvUploadForm.getName()
						+ " because the file was empty.");

				return Constants.PATH_ADMIN_AUTH + "question/list";
			}
		}
	}

	public String importCSV() {

		return "";
	}

	public void readCSVFile(String csvFile, String questionType)
			throws Exception {

		CSVReader csvReader = null;
		try {

			csvReader = new CSVReader(new FileReader(csvFile), this.SEPARATOR);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error occured while executing file. "
					+ e.getMessage());
		}

		String[] headerRow = csvReader.readNext();

		if (null == headerRow) {
			csvReader.close();
			throw new FileNotFoundException(
					"No columns defined in given CSV file."
							+ "Please check the CSV file format.");
		}
		
		if(headerRow.length != 7) {
			csvReader.close();
			throw new Exception("there must be seven columns containing question name, description, type(yes/no), isactive(1/0), question text, enable user comment(1/0), and enable file attachment(1/0) in the order");
		}

		String[] nextLine;
		while ((nextLine = csvReader.readNext()) != null) {

			if (null != nextLine && nextLine.length == 7) {
				int index = 0;
				Question question = new Question();
				for (String string : nextLine) {
					if (index == 0 && string != null)
						question.setName(string);
					else if (index == 1 && string != null) {
						question.setQuestion(string);
					} else if (index == 2 && string != null) {

						question.setQuestionType(string);
					} else if(index == 3 && string != null) {
						question.setActive(Integer.valueOf(string) == 1 ? true: false);
					} else if(index == 4 && string != null){

						question.setText(string);
					} else if(index == 5 && string != null) {
						question.setCommentEnabled(Integer.valueOf(string) == 1 ? true: false);
					} else if(index == 6 && string != null) {
						question.setAttachmentEnabled(Integer.valueOf(string) == 1 ? true: false);
					} else
						break;
					index++;
				} 

				this.questionDao.save(question);
			} else {
				csvReader.close();
				throw new Exception("there must be seven columns containing question name, description, type(yes/no), isactive(1/0), question text, enable user comment(1/0), and enable file attachment(1/0) in the order");
			}
		}
		String questionmarks = StringUtils.repeat("?,", headerRow.length);
		questionmarks = (String) questionmarks.subSequence(0,
				questionmarks.length() - 1);
		
		csvReader.close();
		

	}

	private void loadTreeMenu(ModelMap model) {

		model.addAttribute("attributes", this.attributeDAO.findAll());
		model.addAttribute("sections", this.sectionDAO.findAll());
		model.addAttribute("statuses", this.statusDAO.findAll());
		model.addAttribute("templates", this.templateDAO.findAll());
		model.addAttribute("targetTypes", this.targetTypeDao.findAll());
		model.addAttribute("targets", this.assetDAO.findAll());
		model.addAttribute("users", this.userDao.getUsersList());
		
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("questionGroups", this.questionGroupDao.findAll());
		model.addAttribute("campaignMenues", this.campaignDAO.findAll());
	}
	
	private void loadQuestionMenues(ModelMap model) {
		
		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		List<QuestionGroup> groups = questionGroupDao.findAll();
		for (QuestionGroup group : groups) {

			List<Question> questions = getQuestionsByGroupId(group.getId());
			QuestionnaireForm qForm = new QuestionnaireForm();
			qForm.setCategoryName(group.getName());
			qForm.setDescription(group.getDescription());
			qForm.setActive(group.getActive());
			for (Question question : questions) {
				if (question.getActive())
					qForm.getQuestions().add(question);
			}
			qForm.setQuestionSize(qForm.getQuestions().size());
			qForm.setGroupId(group.getId());
			questionnaires.add(qForm);
		}
		model.addAttribute("questions", this.questionDao.findAll());
		model.addAttribute("campaigns", this.campaignDAO.findAll());
		model.addAttribute("questionnaires", questionnaires);
	}
}
