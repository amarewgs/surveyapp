package com.survey.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Template entity. @author MyEclipse Persistence Tools
 */

public class Template implements java.io.Serializable {

	// Fields

	private Integer id;
	@NotEmpty(message="name is required")
	private String name;
	private String description;
	private Boolean active;
	private Set<TemplateSection> templateSections = new HashSet<TemplateSection>();
	private Set targetTypes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Template() {
	}

	/** full constructor */
	public Template(String name, String description, Boolean active,
			Set templateSections, Set targetTypes) {
		this.name = name;
		this.description = description;
		this.active = active;
		this.templateSections = templateSections;
		this.targetTypes = targetTypes;
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

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<TemplateSection> getTemplateSections() {
		return this.templateSections;
	}

	public void setTemplateSections(Set<TemplateSection> templateSections) {
		this.templateSections = templateSections;
	}

	public Set getTargetTypes() {
		return this.targetTypes;
	}

	public void setTargetTypes(Set targetTypes) {
		this.targetTypes = targetTypes;
	}

}