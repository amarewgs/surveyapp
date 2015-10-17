package com.survey.model;

import java.util.ArrayList;
import java.util.List;

public class SectionAttributeForm {

	private Integer sectionId;
	private String name;
	private String description;
	private boolean active;
	private List<AttributeForm> attributes = new ArrayList<AttributeForm>();
	
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<AttributeForm> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<AttributeForm> attributes) {
		this.attributes = attributes;
	}
}
