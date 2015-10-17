package com.survey.hibernate.model;

/**
 * QuestionChoice entity. @author MyEclipse Persistence Tools
 */

public class QuestionChoice implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private Integer order;
	private Integer question;

	// Constructors

	/** default constructor */
	public QuestionChoice() {
	}

	/** full constructor */
	public QuestionChoice(String name, String description, Integer order,
			Integer question) {
		this.name = name;
		this.description = description;
		this.order = order;
		this.question = question;
	}

	// Property accessors

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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getQuestion() {
		return this.question;
	}

	public void setQuestion(Integer question) {
		this.question = question;
	}

}