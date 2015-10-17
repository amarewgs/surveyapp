package com.survey.hibernate.model;

/**
 * SectionAttribute entity. @author MyEclipse Persistence Tools
 */

public class SectionAttribute implements java.io.Serializable {

	// Fields

	private Integer id;
	private Section section;
	private Attribute attribute;

	// Constructors

	/** default constructor */
	public SectionAttribute() {
	}

	/** full constructor */
	public SectionAttribute(Section section, Attribute attribute) {
		this.section = section;
		this.attribute = attribute;
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

	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

}