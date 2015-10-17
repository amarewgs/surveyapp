package com.survey.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Attribute entity. @author MyEclipse Persistence Tools
 */

public class Attribute implements java.io.Serializable {

	// Fields

	private Integer id;
	@NotEmpty(message="label is required")
	private String label;
	private String description;
	private Boolean active;
	@NotEmpty(message="field type is required")
	private String fieldType;
	private String value;
	private Boolean required;
	private Set<SectionAttribute> sectionAttributes = new HashSet<SectionAttribute>();
	private Set<AssetAttribute> assetAttributes = new HashSet<AssetAttribute>();
	
	// Constructors

	/** default constructor */
	public Attribute() {
	}

	/** full constructor */
	public Attribute(String label, String description, Boolean active,
			String fieldType, String value, Boolean required,
			Set sectionAttributes) {
		this.label = label;
		this.description = description;
		this.active = active;
		this.fieldType = fieldType;
		this.value = value;
		this.required = required;
		this.sectionAttributes = sectionAttributes;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getRequired() {
		return this.required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Set<SectionAttribute> getSectionAttributes() {
		return this.sectionAttributes;
	}

	public void setSectionAttributes(Set<SectionAttribute> sectionAttributes) {
		this.sectionAttributes = sectionAttributes;
	}

	public Set<AssetAttribute> getAssetAttributes() {
		return assetAttributes;
	}

	public void setAssetAttributes(Set<AssetAttribute> assetAttributes) {
		this.assetAttributes = assetAttributes;
	}

}