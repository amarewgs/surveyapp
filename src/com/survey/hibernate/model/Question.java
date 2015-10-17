package com.survey.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */

public class Question implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6497654343911668753L;

	private Integer id;

	@NotEmpty(message="name is required!")
	private String name;

	private String question;

	@NotEmpty(message="question text is required")
	private String text;

	private Integer questionOrder;

	private Boolean mandatory;

	@NotEmpty(message ="question type must be selected")
	private String questionType;

	private String questionGroup;

	private Boolean commentEnabled;
	
	private Boolean active = true;
	
	private Question parent;

	private String filePath;
	
	private Boolean attachmentEnabled = false;

	Set<Answer> answersForQuestion = new HashSet<Answer>();
	Set<Questionnaire> questionnairesForQuestion = new HashSet<Questionnaire>();
	Set<UserAnswer> userAnswersForQuestion = new HashSet<UserAnswer>();
	
	// Constructors

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	/** default constructor */
	public Question() {

	}

	/** full constructor */
	public Question(String name, String question, String text, Integer questionOrder, Boolean mandatory, String questionType, String questionGroup, Boolean commentEnabled, Boolean active) {

		this.name = name;
		this.question = question;
		this.questionOrder = questionOrder;
		this.mandatory = mandatory;
		this.questionType = questionType;
		this.questionGroup = questionGroup;
		this.commentEnabled = commentEnabled;
		this.text = text;
		this.active = active;
	}

	// Property accessors

	public Boolean getCommentEnabled() {

		return commentEnabled;
	}

	public void setCommentEnabled(Boolean commentEnabled) {

		this.commentEnabled = commentEnabled;
	}

	public Integer getId() {

		return this.id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getQuestion() {

		return this.question;
	}

	public void setQuestion(String question) {

		this.question = question;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getQuestionOrder() {

		return this.questionOrder;
	}

	public void setQuestionOrder(Integer questionOrder) {

		this.questionOrder = questionOrder;
	}

	public Boolean getMandatory() {

		return this.mandatory;
	}

	public void setMandatory(Boolean mandatory) {

		this.mandatory = mandatory;
	}

	public String getQuestionType() {

		return this.questionType;
	}

	public void setQuestionType(String questionType) {

		this.questionType = questionType;
	}

	public String getQuestionGroup() {

		return this.questionGroup;
	}

	public void setQuestionGroup(String questionGroup) {

		this.questionGroup = questionGroup;
	}

	public Set<Answer> getAnswersForQuestion() {
		return answersForQuestion;
	}

	public void setAnswersForQuestion(Set<Answer> answersForQuestion) {
		this.answersForQuestion = answersForQuestion;
	}

	public Set<Questionnaire> getQuestionnairesForQuestion() {
		return questionnairesForQuestion;
	}

	public void setQuestionnairesForQuestion(
			Set<Questionnaire> questionnairesForQuestion) {
		this.questionnairesForQuestion = questionnairesForQuestion;
	}

	public Set<UserAnswer> getUserAnswersForQuestion() {
		return userAnswersForQuestion;
	}

	public void setUserAnswersForQuestion(Set<UserAnswer> userAnswersForQuestion) {
		this.userAnswersForQuestion = userAnswersForQuestion;
	}
	
	public Question getParent() {
		return parent;
	}

	public void setParent(Question parent) {
		this.parent = parent;
	}	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}	

	public Boolean getAttachmentEnabled() {
		return attachmentEnabled;
	}

	public void setAttachmentEnabled(Boolean attachmentEnabled) {
		this.attachmentEnabled = attachmentEnabled;
	}
	
}