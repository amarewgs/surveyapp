package com.survey.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.survey.dao.AnswerDAO;
import com.survey.dao.CampaignDAO;
import com.survey.dao.QuestionDAO;
import com.survey.dao.QuestionGroupDAO;
import com.survey.dao.QuestionnaireDAO;
import com.survey.dao.RoleDao;
import com.survey.dao.UserAnswerDAO;
import com.survey.dao.UserDao;
import com.survey.hibernate.model.Answer;
import com.survey.hibernate.model.Campaign;
import com.survey.hibernate.model.Question;
import com.survey.hibernate.model.QuestionGroup;
import com.survey.hibernate.model.Questionnaire;
import com.survey.hibernate.model.Role;
import com.survey.hibernate.model.TargetQuestionnaire;
import com.survey.hibernate.model.User;
import com.survey.hibernate.model.UserAnswer;
import com.survey.hibernate.model.UserCampaign;
import com.survey.model.CampaignForm;
import com.survey.model.ChangePasswordForm;
import com.survey.model.QuestionForm;
import com.survey.model.QuestionGroupForm;
import com.survey.model.QuestionSheet;
import com.survey.model.QuestionTypeEnum;
import com.survey.model.QuestionnaireForm;
import com.survey.model.UserAnswerForm;
import com.survey.model.UserAnswerSheetForm;
import com.survey.model.UserQuestionnaire;
import com.survey.model.UserQuestionnaireCompletedForm;
import com.survey.model.UserQuestionnaireForm;
import com.survey.model.UserQuestionnaireGroupCompletedForm;
import com.survey.model.UserRegistrationForm;
import com.survey.utils.Constants;


@Controller
public class UserController {
	
	@Autowired(required = false)
	private QuestionDAO questionDao;

	@Autowired(required = false)
	private QuestionnaireDAO questionnaireDao;
	
	@Autowired(required = false)
	private QuestionGroupDAO questionGroupDao;
	
	@Autowired(required = false)
	private AnswerDAO answerDao;

	@Autowired(required = false)
	private UserAnswerDAO userAnswerDao;
	
	@Autowired(required = false)
	private UserDao	userDao;
	
	@Autowired(required = false)
	private RoleDao	roleDao;
	
	@Autowired
	private CampaignDAO campaignDAO;
	
	@Autowired
	Md5PasswordEncoder passwordEncoder;

	
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = Constants.PATH_PAGES_AUTH + "index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.put("userName", authentication.getName());

		return "/public/auth/index";
	}
	
	private void loadUserTreeMenu(ModelMap model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userDao.getUserByUsername(authentication.getName());
		
		List<CampaignForm> campaignForms = new ArrayList<CampaignForm>();
		List<QuestionnaireForm> questionnaires = new ArrayList<QuestionnaireForm>();
		
		List<UserCampaign> userCampaigns = new ArrayList<UserCampaign>(user.getUserCampaigns());
		for (UserCampaign userCampaign : userCampaigns) {
			
			Campaign campaign = userCampaign.getCampaign();
			CampaignForm campaignForm = new CampaignForm();
			campaignForm.setName(campaign.getName());
			campaignForm.setDescription(campaign.getDescription());
			campaignForm.setActive(campaign.getActive());
			campaignForm.setCampaignId(campaign.getId());
			
			List<TargetQuestionnaire> targetQuestionnaires = new ArrayList<TargetQuestionnaire>(campaign.getTargetQuestionnaires());
			
			for (TargetQuestionnaire targetQuestionnaire : targetQuestionnaires) {
				
				QuestionGroup group = targetQuestionnaire.getQuestionGroup();
				
				List<Question> questions = getQuestionsByGroupId(group.getId());
				UserQuestionnaireForm qForm = new UserQuestionnaireForm();
				qForm.setCategoryName(group.getName());
				qForm.setDescription(group.getDescription());
				for (Question question : questions) {
					if(question.getActive()) qForm.getQuestions().add(question);
				}
				qForm.setQuestionSize(qForm.getQuestions().size());
				
				List<Integer> questionIdsCompleted = userAnswerDao.getQuestionIdsCompleted(group.getId(), user.getId());
				
				for (Integer questionId : questionIdsCompleted) {
					
					qForm.getQuestionsCompleted().add(questionDao.findById(questionId));
				}
				qForm.setQuestionCompletedSize(questionIdsCompleted.size());
				//qForm.setQuestionSize(questions.size());
				qForm.setRemainingQuestions(qForm.getQuestions().size() - questionIdsCompleted.size());
				
				qForm.setGroupId(group.getId());
				
				campaignForm.getQuestionnaires().add(qForm);
			}
			
			campaignForms.add(campaignForm);
		}
		model.addAttribute("campaigns", campaignForms);
	}
	
	@RequestMapping(value = Constants.PATH_PAGES_AUTH + "userCampaigns", method = RequestMethod.GET)
	public String campaigns(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		this.loadUserTreeMenu(model);
		
		model.addAttribute("title", "available questionnaires"); 
		model.addAttribute("userName", authentication.getName());
		
		return "/public/auth/campaign/list";
	}
	
	@RequestMapping(value = Constants.PATH_PAGES_AUTH + "campaigns", method = RequestMethod.GET)
	public String userCampaigns(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.put("userName", authentication.getName());
		
		User user = userDao.getUserByUsername(authentication.getName());
		
		List<UserCampaign> userCampaigns = new ArrayList<UserCampaign>(user.getUserCampaigns());
		
		model.addAttribute("userCampaigns", userCampaigns);
		
		return "/public/auth/campaigns";
	}
	
	private List<Question> getQuestionsByGroupId(Integer groupId) {
    	
    	QuestionGroup questionGroup = this.questionGroupDao.findById(groupId);
		
		List<Questionnaire> questionnaires = new ArrayList<Questionnaire>( questionGroup.getQuestionnaires());
		
		List<Question> questions = new ArrayList<Question>();
		
		for (Questionnaire questionnaire : questionnaires) {
			questions.add(questionnaire.getQuestion());
		}
		
		return questions;
    }
	@RequestMapping(value = Constants.PATH_PAGES_AUTH + "/questionnaires/{campaignId}", method = RequestMethod.GET)
	public String questionnaires(ModelMap model, @PathVariable("campaignId") Integer campaignId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<UserQuestionnaireForm> questionnaires = new ArrayList<UserQuestionnaireForm>();
		User user = userDao.getUserByUsername(authentication.getName());
		
		if(campaignId != null) {
			
			Campaign campaign = this.campaignDAO.findById(campaignId);
			
			List<TargetQuestionnaire> targetQuestionnaires = new ArrayList<TargetQuestionnaire>(campaign.getTargetQuestionnaires());
			
			for(TargetQuestionnaire targetQuestionnaire : targetQuestionnaires) {
				
				QuestionGroup group = targetQuestionnaire.getQuestionGroup();
				
				List<Question> questions = getQuestionsByGroupId(group.getId());
				UserQuestionnaireForm qForm = new UserQuestionnaireForm();
				qForm.setCategoryName(group.getName());
				qForm.setDescription(group.getDescription());
				for (Question question : questions) {
					if(question.getActive()) qForm.getQuestions().add(question);
				}
				qForm.setQuestionSize(qForm.getQuestions().size());
				
				qForm.setGroupId(group.getId());
				
				List<Integer> questionIdsCompleted = userAnswerDao.getQuestionIdsCompleted(group.getId(), user.getId());
				
				for (Integer questionId : questionIdsCompleted) {
					
					qForm.getQuestionsCompleted().add(questionDao.findById(questionId));
				}
				qForm.setQuestionCompletedSize(questionIdsCompleted.size());
				//qForm.setQuestionSize(questions.size());
				qForm.setRemainingQuestions(qForm.getQuestions().size() - questionIdsCompleted.size());
				questionnaires.add(qForm);
			}
	
			model.addAttribute("campaign", campaign);
			model.addAttribute("userName", authentication.getName());
			model.addAttribute("questionnaires", questionnaires);
			
			this.loadUserTreeMenu(model);
			
			return Constants.PATH_PAGES_AUTH +  "/questionnarieList";
		} else 
			return "/public/auth/campaigns";
	}

	@RequestMapping(value = Constants.PATH_PAGES_AUTH + "questionnaire", method = RequestMethod.GET)
	public String questionnaire(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.put("userName", authentication.getName());

		List<QuestionGroup> groups = questionGroupDao.findAll();
		
		List<QuestionGroupForm> questionGroupForms = new ArrayList<QuestionGroupForm>();
		
		User user = userDao.getUserByUsername(authentication.getName());
		
		for (QuestionGroup group : groups) {			
			
			List<QuestionForm> questionForms = new ArrayList<QuestionForm>();
			
			QuestionGroupForm questionGroupForm = new QuestionGroupForm();
			questionGroupForm.setGroupName(group.getName());
			
			List<Question> questions = getQuestionsByGroupId(group.getId());
			
			List<Integer> questionIdsCompleted = userAnswerDao.getQuestionIdsCompleted(group.getId(), user.getId());
			
			for (Question question : questions) {
				
				QuestionForm questionForm = new QuestionForm();
				
				List<Answer> answers = new ArrayList<Answer>(question.getAnswersForQuestion());
				
				questionForm.setCategory(group.getName());
				questionForm.setAnswers(answers);
				questionForm.setQuestion(question);
				
				questionForms.add(questionForm);
			}
			
			questionGroupForm.setQuestionForms(questionForms);
			
			questionGroupForms.add(questionGroupForm);			
			
		}
		
		model.addAttribute("questionGroupForms", questionGroupForms);
		
		return "/public/auth/questionnaire";
	}
	
	@RequestMapping(value = Constants.PATH_PAGES_AUTH + "questionnaire/{groupId}/{campaignId}", method = RequestMethod.GET)
	public String fillQuestionnaire(ModelMap model, @PathVariable("groupId") Integer groupId, @PathVariable("campaignId") Integer campaignId, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.put("userName", authentication.getName());

		QuestionGroup group = questionGroupDao.findById(groupId);
		
		List<QuestionForm> questionForms = new ArrayList<QuestionForm>();
		
		QuestionGroupForm questionGroupForm = new QuestionGroupForm();
		questionGroupForm.setGroupName(group.getName());
		questionGroupForm.setDescription(group.getDescription());
		questionGroupForm.setGroupId(group.getId());
		List<Question> questions = getQuestionsByGroupId(group.getId());
		
		User user = userDao.getUserByUsername(authentication.getName());
		
		List<Integer> questionIdsCompleted = userAnswerDao.getQuestionIdsCompleted(groupId, user.getId());
		
		if(campaignId != null) {
			
			model.addAttribute("campaign", this.campaignDAO.findById(campaignId));
		}
		
		for (Question question : questions) {
			
			boolean isQuestionCompleted = false;
			
			for (Integer questionId : questionIdsCompleted) {
				if(questionId.equals(question.getId())) {
					isQuestionCompleted = true;
					break;
				} else continue;
			}
			
			if(!isQuestionCompleted && question.getActive()) {
				QuestionForm questionForm = new QuestionForm();
				
				List<Answer> answers = new ArrayList<Answer>(question.getAnswersForQuestion());
				
				questionForm.setCategory(group.getName());
				questionForm.setAnswers(answers);
				questionForm.setQuestion(question);
				
				questionForms.add(questionForm);
			}
		}
		
		questionGroupForm.setQuestionForms(questionForms);
		
		session.setAttribute("campaignId", campaignId);
		
		model.addAttribute("questionGroupForm", questionGroupForm);
		
		this.loadUserTreeMenu(model);
		
		return "/public/auth/questionnaireForm";
	}
	
	@RequestMapping(value = Constants.PATH_PAGES_AUTH + "questionnaireCompleted/{groupId}/{campaignId}", method = RequestMethod.GET)
	public String completedQuestionnaire(ModelMap model, @PathVariable("groupId") Integer groupId, @PathVariable("campaignId") Integer campaignId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.put("userName", authentication.getName());

		QuestionGroup group = questionGroupDao.findById(groupId);
		
		UserQuestionnaireGroupCompletedForm questionGroupForm = new UserQuestionnaireGroupCompletedForm();
		questionGroupForm.setCategoryName(group.getName());
		questionGroupForm.setCategoryDescription(group.getDescription());
		
		User user = userDao.getUserByUsername(authentication.getName());
		
		List<UserQuestionnaireCompletedForm> userQuestionnaireCompletedForms = new ArrayList<UserQuestionnaireCompletedForm>();
		
		List<Integer> questionIdsCompleted = userAnswerDao.getQuestionIdsCompleted(groupId, user.getId());
		
		for (Integer questionId : questionIdsCompleted) {
			
			UserQuestionnaireCompletedForm userQuestionnaireCompletedForm = new UserQuestionnaireCompletedForm();
			
			Question question = questionDao.findById(questionId);
			
			userQuestionnaireCompletedForm.setQuestion(question);
			UserAnswerSheetForm userAnswerSheetForm = new UserAnswerSheetForm();		
			
			List<UserAnswer> userAnswers = userAnswerDao.getUserAnswersByQuestion(group, question, user);
			
			if(question.getQuestionType().equals(QuestionTypeEnum.MULTICHOICE.name()) || question.getQuestionType().equals(QuestionTypeEnum.LIST_CHOICE.name())){
				
				List<Answer> answers = new ArrayList<Answer>(question.getAnswersForQuestion());
				//userAnswerSheetForm.setAnswers(answers);
				//List<UserAnswer> userAnswers = userAnswerDao.getUserAnswersByQuestion(groupId, questionId, user.getId());
				
				if(question.getQuestionType().equals(QuestionTypeEnum.MULTICHOICE.name())){
					for (Answer answer : answers) {
						boolean found = false;
						for (UserAnswer userAnswer : userAnswers) {
							if(answer.getId().equals(userAnswer.getAnswer())) found = true;
							//userAnswerSheetForm.getChosenAnswers().add(answerDao.getAnswerById(userAnswer.getAnswer()));
						}
						if(!found) userAnswerSheetForm.getAnswers().add(answer);
					}
				} else {
					userAnswerSheetForm.getAnswers().addAll(answers);
				}
				for (UserAnswer userAnswer : userAnswers) {
					userAnswerSheetForm.getChosenAnswers().add(userAnswer.getAnswer());
				}
			} else {			
				
				
			}
						
			if(userAnswers != null && userAnswers.size() > 0) {
				userAnswerSheetForm.setComment(userAnswers.get(0).getComment());
				userAnswerSheetForm.setDetailAnswer(userAnswers.get(0).getDetailAnswer());
				userAnswerSheetForm.setBooleanAnswer(userAnswers.get(0).getBooleanAnswer());
			}
			
			//questionForm.setCategory(group.getName());
			userQuestionnaireCompletedForm.setAnswerSheetForm(userAnswerSheetForm);
			
			userQuestionnaireCompletedForms.add(userQuestionnaireCompletedForm);
		}	
		
		if(campaignId != null) {
			
			model.addAttribute("campaign", this.campaignDAO.findById(campaignId));
		}
		questionGroupForm.setUserQuestionnaireCompletedForms(userQuestionnaireCompletedForms);
		
		model.addAttribute("questionGroupForm", questionGroupForm);
		
		this.loadUserTreeMenu(model);
				
		return "/public/auth/questionnaireCompletedForm";
	}	

	 @RequestMapping(value = Constants.PATH_PAGES_AUTH + "questionnaire/save", method = RequestMethod.POST)
	    public String saveUserQuestionnaire(@ModelAttribute("userQuestionnaire") @Valid UserQuestionnaire userQuestionnaire, BindingResult result, HttpSession session, SessionStatus status) throws IOException {
	    	 
	    	if (result.hasErrors()) {
	    		return "redirect:/public/auth/questionnaire/" + userQuestionnaire.getGroupId() + "/" + userQuestionnaire.getCampaignId();
	         } else {
	        	 
	        	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        	 
	        	 User user = userDao.getUserByUsername(authentication.getName());
	        	 
	        	 for (UserAnswerForm userAnswerForm : userQuestionnaire.getUserAnswerForm()) {
				
	        		 Question question = questionDao.findById(userAnswerForm.getQuestionId());
	        		 
	        		 if(question != null) {
	        			 
	        			 if(question.getQuestionType().equals(QuestionTypeEnum.YES_NO.name())) {
	        				 
	        				 if(userAnswerForm.getBooleanAnswer() == null) 
	        					 continue;
	        				 
	        				 UserAnswer userAnswer = new UserAnswer();
	        				 userAnswer.setBooleanAnswer(userAnswerForm.getBooleanAnswer());
	        				 userAnswer.setComment(userAnswerForm.getComment());
	        				 userAnswer.setQuestion(question);
	        				 userAnswer.setUser(user);
	        				 userAnswer.setQuestionGroup(this.questionGroupDao.findById(userQuestionnaire.getGroupId()));
	        				 
	        				 if(question.getAttachmentEnabled() != null && question.getAttachmentEnabled()) this.uploadUserFileAttachment(userAnswer, userAnswerForm, session);
	        				 userAnswerDao.save(userAnswer);
	        				 
	        			 } else if(question.getQuestionType().equals(QuestionTypeEnum.MULTICHOICE.name())) {
	        				 
	        				 if(userAnswerForm.getAnswerIds() == null) 
	        					 continue;
	        				 
	        				 if(userAnswerForm.getAnswerIds().size() <= 0)
	        					 continue;
	        				 
	        				 for (Integer answerId : userAnswerForm.getAnswerIds()) {
								
	        					 if(answerId == null) continue;
	        					 
	        					 UserAnswer userAnswer = new UserAnswer();
		        				 userAnswer.setComment(userAnswerForm.getComment());		        				 //userAnswer.setComment(userAnswerForm.getComment());
		        				 userAnswer.setQuestion(question);
		        				 userAnswer.setUser(user);
		        				 userAnswer.setQuestionGroup(this.questionGroupDao.findById(userQuestionnaire.getGroupId()));
		        				 if(question.getAttachmentEnabled() != null && question.getAttachmentEnabled()) this.uploadUserFileAttachment(userAnswer, userAnswerForm, session);
		        				 userAnswerDao.save(userAnswer);
							}
	        				
	        				 
	        				 
	        			 } else if(question.getQuestionType().equals(QuestionTypeEnum.LIST_CHOICE.name())) {
	        				 
	        				 if(userAnswerForm.getAnswerIds() == null && userAnswerForm.getAnswerIds().size() <= 0) 
	        					 continue;
	        				 if(userAnswerForm.getAnswerIds().get(0) == null)
	        					 continue;
	        				 
	        				 UserAnswer userAnswer = new UserAnswer();
	        				 userAnswer.setComment(userAnswerForm.getComment());
	        				 userAnswer.setQuestion(question);
	        				 userAnswer.setUser(user);
	        				 userAnswer.setQuestionGroup(this.questionGroupDao.findById(userQuestionnaire.getGroupId()));
	        				 if(question.getAttachmentEnabled() != null && question.getAttachmentEnabled()) this.uploadUserFileAttachment(userAnswer, userAnswerForm, session);
	        				 userAnswerDao.save(userAnswer);
	        				 
	        			 } else if(question.getQuestionType().equals(QuestionTypeEnum.OPEN_ENDED.name())) {
	        				 
	        				 if(userAnswerForm.getDetailAnswer() == null || userAnswerForm.getDetailAnswer().isEmpty()) 
	        					 continue;
	        				 
	        				 UserAnswer userAnswer = new UserAnswer();
	        				 userAnswer.setDetailAnswer(userAnswerForm.getDetailAnswer());
	        				 userAnswer.setComment(userAnswerForm.getComment());
	        				 userAnswer.setQuestion(question);
	        				 userAnswer.setUser(user);
	        				 userAnswer.setQuestionGroup(this.questionGroupDao.findById(userQuestionnaire.getGroupId()));
	        				 if(question.getAttachmentEnabled() != null && question.getAttachmentEnabled()) this.uploadUserFileAttachment(userAnswer, userAnswerForm, session);
	        				 userAnswerDao.save(userAnswer);
	        			 }
	        		 }
				}	        	 
	        	 Integer campaignId = Integer.valueOf(session.getAttribute("campaignId").toString());
	        	 session.removeAttribute("campaignId");
	        	 status.setComplete();
	        	 //userAnswerDao.getQuestionsCompleted(userQuestionnaire.getGroupId(), user.getId());
	        	 
	        	 return "redirect:" + Constants.PATH_PAGES_AUTH +  "questionnaires/" + campaignId;
	         }
	 }
	 
	 private void uploadUserFileAttachment(UserAnswer userAnswer, UserAnswerForm userAnswerForm, HttpSession session) throws IOException {
		 
		 MultipartFile file = userAnswerForm.getFile();
		 
		 if (file != null && !file.isEmpty()) {
				String filePath = session.getServletContext().getRealPath("/");

				// File dir = resourceLoader.getResource(filePath +
				// "attachments").getFile();
				File dir = new File(filePath + "data/user/attachments");
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

				userAnswer.setAttachmentPath(file.getOriginalFilename());
			}
	 }
	 
	 @RequestMapping(value=Constants.PATH_PAGES_AUTH + "editProfile")
	 public String editProfile(ModelMap model) {
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	 
    	 User user = userDao.getUserByUsername(authentication.getName());
    	 
    	 model.addAttribute("user", user);
    	 
    	 model.addAttribute("userName", authentication.getName());
    	 
		 return Constants.PATH_PAGES_AUTH + "editProfile";
	 }
	 
	    
    @RequestMapping(value = Constants.PATH_PAGES_AUTH + "/updateProfile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute("user") @Valid User user, BindingResult result, SessionStatus status) {
    	 
    	if (result.hasErrors()) {
             return "/public/auth/editProfile";
         } else {
        	         	 
        	User originalUser = userDao.getUserByUserId(user.getId());
         	originalUser.setFirstName(user.getFirstName());
         	originalUser.setLastName(user.getLastName());
         	originalUser.setEmail(user.getEmail());
            this.userDao.createUser(originalUser);
            
            status.setComplete();
             
        	 return "redirect:/public/auth/questionnaireList";
         }
    }
    
    public void validateChangePassword(ChangePasswordForm changePasswordForm, Errors errors) {
		 
		 User user = this.userDao.getUserByUserId(changePasswordForm.getUserId());
		 
		 if(!this.passwordEncoder.encodePassword(changePasswordForm.getOldPassword(), user.getUserName()).equals(user.getPassword())) {
			 errors.rejectValue("password", "valid", "old password are not valid");
		 }
		 if(changePasswordForm.getPassword() == null || changePasswordForm.getConfirmPassword() == null){
			 errors.rejectValue("password", "required", "passwords can not be null");
		 } else if(!changePasswordForm.getPassword().equals(changePasswordForm.getConfirmPassword())){
	    	errors.rejectValue("password", "match", "passwords should match");
	    } 
	}
    
	 @RequestMapping(value=Constants.PATH_PAGES_AUTH + "changePassword")
	 public String changePassword(ModelMap model, HttpSession session) {
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	   	 
		 model.addAttribute("changePasswordForm",  session.getAttribute("changePasswordForm") != null ? session.getAttribute("changePasswordForm") : new ChangePasswordForm());
		 model.put("org.springframework.validation.BindingResult.changePasswordForm", session.getAttribute("binding"));
			   	 
	   	 model.addAttribute("userName", authentication.getName());
   	 
		 return Constants.PATH_PAGES_AUTH + "changePassword";
	 }
	 
	 @RequestMapping(value = Constants.PATH_PAGES_AUTH + "/updatePassword", method = RequestMethod.POST)
	    public String updatePassword(@ModelAttribute("changePasswordForm") @Valid ChangePasswordForm changePasswordForm, BindingResult result, SessionStatus status, HttpSession session) {
	    	 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 User user = userDao.getUserByUsername(authentication.getName());
		 changePasswordForm.setUserId(user.getId());
		 validateChangePassword(changePasswordForm, result);
		 	
	    	if (result.hasErrors()) {
	    		
	    		 session.setAttribute("changePasswordForm", changePasswordForm);
	    		 session.setAttribute("binding",result);
	             return "redirect:/public/auth/changePassword";
	         } else {        	         	 
	    	   	 
	    	   	 user.setPassword(changePasswordForm.getPassword());
	    	   	 
	             this.userDao.createUser(user);
	             status.setComplete();
	             
	        	 return "redirect:/public/auth/campaigns";
	         }
	    }
	 
	 @RequestMapping(value = Constants.PATH_PAGES_AUTH +"attachments/{questionId}", method = RequestMethod.GET)
	    public void getAttachmentFile(@PathVariable("questionId") Integer questionId, HttpServletResponse response) {
	        try {
	          // get your file as InputStream
	        	Question question = this.questionDao.findById(questionId);
	        	File fileToDownload = new File(question.getFilePath());
	            InputStream inputStream = new FileInputStream(fileToDownload);
	            response.setContentType("application/force-download");
	            response.setHeader("Content-Disposition", "attachment; filename="+ fileToDownload.getName()); 
	            IOUtils.copy(inputStream, response.getOutputStream());
	              response.flushBuffer();
	              
	           inputStream.close();
	        } catch (IOException ex) {
	          //throw new RuntimeException("IOError writing file to output stream");
	        }

	    }
}
