package com.survey.hibernate.model;

/**
 * TemplateSection entity. @author MyEclipse Persistence Tools
 */

public class TemplateSection implements java.io.Serializable {

	// Fields

	private Integer id;
	private Section section;
	private Template template;

	// Constructors

	/** default constructor */
	public TemplateSection() {
	}

	/** full constructor */
	public TemplateSection(Section section, Template template) {
		this.section = section;
		this.template = template;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Template getTemplate() {
		return this.template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

}