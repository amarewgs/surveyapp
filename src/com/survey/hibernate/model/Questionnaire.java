package com.survey.hibernate.model;

/**
 * UserAnswer entity. @author MyEclipse Persistence Tools
 */

public class Questionnaire implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7377841032482497940L;
	private Integer id;
	private Question question;
	private String title;
	private QuestionGroup questionGroup;
	// Constructors

	/** default constructor */
	public Questionnaire() {
	}

	/** full constructor */
	public Questionnaire(Question question, String title, QuestionGroup questionGroup) {
		
		this.question = question;
		this.title = title;
		this.questionGroup = questionGroup;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	
	public String getTitle() {
	
		return title;
	}

	
	public void setTitle(String title) {
	
		this.title = title;
	}

	
	public QuestionGroup getQuestionGroup() {
	
		return questionGroup;
	}

	
	public void setQuestionGroup(QuestionGroup questionGroup) {
	
		this.questionGroup = questionGroup;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}