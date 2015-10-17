package com.survey.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Target entity. @author MyEclipse Persistence Tools
 */

public class Target implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private Integer category;
	private Set targetQuestionnaires = new HashSet(0);

	// Constructors

	/** default constructor */
	public Target() {
	}

	/** full constructor */
	public Target(String name, String description, Integer category,
			Set targetQuestionnaires) {
		this.name = name;
		this.description = description;
		this.category = category;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Set getTargetQuestionnaires() {
		return this.targetQuestionnaires;
	}

	public void setTargetQuestionnaires(Set targetQuestionnaires) {
		this.targetQuestionnaires = targetQuestionnaires;
	}

}