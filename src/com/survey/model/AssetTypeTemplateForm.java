package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import com.survey.hibernate.model.Section;

public class AssetTypeTemplateForm {

	private String name;
	private String description;
	private boolean active;
	List<SectionAttributeForm> sectionAttributes = new ArrayList<SectionAttributeForm>();

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
	
	public List<SectionAttributeForm> getSectionAttributes() {
		return sectionAttributes;
	}
	public void setSectionAttributes(List<SectionAttributeForm> sectionAttributes) {
		this.sectionAttributes = sectionAttributes;
	}
	
}
