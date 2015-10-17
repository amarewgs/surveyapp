package com.survey.hibernate.model;

/**
 * QuestionType entity. @author MyEclipse Persistence Tools
 */

public class QuestionType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private String code;
	private Integer maxSelection;
	private Integer maxChoice;

	// Constructors

	/** default constructor */
	public QuestionType() {
	}

	/** full constructor */
	public QuestionType(String name, String description, String code,
			Integer maxSelection, Integer maxChoice) {
		this.name = name;
		this.description = description;
		this.code = code;
		this.maxSelection = maxSelection;
		this.maxChoice = maxChoice;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getMaxSelection() {
		return this.maxSelection;
	}

	public void setMaxSelection(Integer maxSelection) {
		this.maxSelection = maxSelection;
	}

	public Integer getMaxChoice() {
		return this.maxChoice;
	}

	public void setMaxChoice(Integer maxChoice) {
		this.maxChoice = maxChoice;
	}

}