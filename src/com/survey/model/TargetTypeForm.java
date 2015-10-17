package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.survey.hibernate.model.Status;
import com.survey.hibernate.model.Template;

public class TargetTypeForm {

	private Integer targetTypeId;
	@NotEmpty(message="name is required")
	private String name;
	private String description;
	private boolean active;
	private Integer templateId;
	@NotEmpty(message="select one or more status")
	private List<Integer> targetTypeStatusIds = new ArrayList<Integer>();
	private List<Status> statuses = new ArrayList<Status>();
	private Template template;
	
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	public List<Status> getStatuses() {
		return statuses;
	}
	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}
	public Integer getTargetTypeId() {
		return targetTypeId;
	}
	public void setTargetTypeId(Integer targetTypeId) {
		this.targetTypeId = targetTypeId;
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
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public List<Integer> getTargetTypeStatusIds() {
		return targetTypeStatusIds;
	}
	public void setTargetTypeStatusIds(List<Integer> targetTypeStatusIds) {
		this.targetTypeStatusIds = targetTypeStatusIds;
	}
	
}
