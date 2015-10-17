package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.survey.hibernate.model.Attribute;

public class SectionForm {
	
	private Integer sectionId;
	@NotNull(message="name is required")
	private String name;
	private String description;
	private boolean active;
	private List<Integer> attributeIds = new ArrayList<Integer>();
	private List<Attribute> attributes = new ArrayList<Attribute>();
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean isActive) {
		this.active = isActive;
	}
	
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Integer> getAttributeIds() {
		return attributeIds;
	}
	public void setAttributeIds(List<Integer> attributeIds) {
		this.attributeIds = attributeIds;
	}

}
