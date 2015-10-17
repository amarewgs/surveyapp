package com.survey.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * QuestionGroup entity. @author MyEclipse Persistence Tools
 */

public class QuestionGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String code;
	private String description;
	private boolean active;	
	private Set<Questionnaire> questionnaires = new HashSet<Questionnaire>();
	private Set<TargetQuestionnaire> targetQuestionnaires = new HashSet<TargetQuestionnaire>();

	// Constructors

	/** default constructor */
	public QuestionGroup() {
	}

	/** full constructor */
	public QuestionGroup(String name, Integer order,
			String description, boolean active, Set questionnaires, Set targetQuestionnaires) {
		this.name = name;
		this.code = code;
		this.active = active;
		this.description = description;
		this.questionnaires = questionnaires;
		this.targetQuestionnaires = targetQuestionnaires;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Questionnaire> getQuestionnaires() {
		return this.questionnaires;
	}

	public void setQuestionnaires(Set<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

	public Set<TargetQuestionnaire> getTargetQuestionnaires() {
		return this.targetQuestionnaires;
	}

	public void setTargetQuestionnaires(Set<TargetQuestionnaire> targetQuestionnaires) {
		this.targetQuestionnaires = targetQuestionnaires;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}