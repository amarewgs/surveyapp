package com.survey.hibernate.model;

/**
 * UserAnswer entity. @author MyEclipse Persistence Tools
 */

public class UserAnswer implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Question question;
	private String comment;
	private Answer answer;
	private String detailAnswer;
	private Boolean booleanAnswer;
	private QuestionGroup questionGroup;
	private String attachmentPath;
	// Constructors
	/** default constructor */
	public UserAnswer() {
	}

	/** full constructor */
	public UserAnswer(User user, Question question, String comment, Answer answer, String detailAnswer, 
			Boolean booleanAnswer, QuestionGroup questionGroup) {
		this.user = user;
		this.question = question;
		this.comment = comment;
		this.answer = answer;
		this.detailAnswer = detailAnswer;
		this.booleanAnswer = booleanAnswer;
		this.questionGroup = questionGroup;
	}

	// Property accessors

	
	
	
	public Boolean getBooleanAnswer() {
	
		return booleanAnswer;
	}

	
	public void setBooleanAnswer(Boolean booleanAnswer) {
	
		this.booleanAnswer = booleanAnswer;
	}

	public String getDetailAnswer() {
	
		return detailAnswer;
	}

	
	public void setDetailAnswer(String detailAnswer) {
	
		this.detailAnswer = detailAnswer;
	}

	public Answer getAnswer() {
	
		return answer;
	}

	
	public void setAnswer(Answer answer) {
	
		this.answer = answer;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public QuestionGroup getQuestionGroup() {
		return questionGroup;
	}

	public void setQuestionGroup(QuestionGroup questionGroup) {
		this.questionGroup = questionGroup;
	}

}