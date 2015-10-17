package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.survey.hibernate.model.Section;

public class TemplateForm {
	
	private Integer templateId;
	@NotNull(message="name is required")
	private String name;
	private String description;
	private boolean active;
	private List<Integer> sectionIds = new ArrayList<Integer>();
	private List<Section> sections = new ArrayList<Section>();
	
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean isActive) {
		this.active = isActive;
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
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public List<Integer> getSectionIds() {
		return sectionIds;
	}
	public void setSectionIds(List<Integer> sectionIds) {
		this.sectionIds = sectionIds;
	}


}
