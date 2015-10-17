package com.survey.hibernate.model;

/**
 * Answer entity. @author MyEclipse Persistence Tools
 */

public class Answer implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String answer;
	private Question question;
	private Float score;

	// Constructors

	/** default constructor */
	public Answer() {
	}

	/** full constructor */
	public Answer(String code, String answer, Question question, Float score) {
		this.code = code;
		this.answer = answer;
		this.question = question;
		this.score = score;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Float getScore() {
		return this.score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

}