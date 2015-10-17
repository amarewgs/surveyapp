package com.survey.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Section entity. @author MyEclipse Persistence Tools
 */

public class Section implements java.io.Serializable {

	// Fields

	private Integer id;
	@NotEmpty(message = "names is required")
	private String name;
	private String description;
	private Boolean active;
	private Set<SectionAttribute> sectionAttributes = new HashSet<SectionAttribute>(0);
	private Set<TemplateSection> templateSections = new HashSet<TemplateSection>(0);

	// Constructors

	/** default constructor */
	public Section() {
	}

	/** full constructor */
	public Section(String name, String description, Boolean active,
			Set sectionAttributes, Set templateSections) {
		this.name = name;
		this.description = description;
		this.active = active;
		this.sectionAttributes = sectionAttributes;
		this.templateSections = templateSections;
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

	public Set<SectionAttribute> getSectionAttributes() {
		return this.sectionAttributes;
	}

	public void setSectionAttributes(Set sectionAttributes) {
		this.sectionAttributes = sectionAttributes;
	}

	public Set<TemplateSection> getTemplateSections() {
		return this.templateSections;
	}

	public void setTemplateSections(Set templateSections) {
		this.templateSections = templateSections;
	}

}