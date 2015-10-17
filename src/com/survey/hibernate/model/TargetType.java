package com.survey.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TargetType entity. @author MyEclipse Persistence Tools
 */

public class TargetType implements java.io.Serializable {

	// Fields

	private Integer id;
	private Template template;
	private String name;
	private String description;
	private Boolean active;
	private Set<Asset> assets = new HashSet<Asset>();
	private Set<TargetTypeStatus> targetTypeStatuses = new HashSet<TargetTypeStatus>();

	// Constructors

	/** default constructor */
	public TargetType() {
	}

	/** full constructor */
	public TargetType(Template template, String name, String description,
			Boolean active, Set assets, Set targetTypeStatuses) {
		this.template = template;
		this.name = name;
		this.description = description;
		this.active = active;
		this.assets = assets;
		this.targetTypeStatuses = targetTypeStatuses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Template getTemplate() {
		return this.template;
	}

	public void setTemplate(Template template) {
		this.template = template;
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

	public Set getAssets() {
		return this.assets;
	}

	public void setAssets(Set assets) {
		this.assets = assets;
	}

	public Set<TargetTypeStatus> getTargetTypeStatuses() {
		return this.targetTypeStatuses;
	}

	public void setTargetTypeStatuses(Set<TargetTypeStatus> targetTypeStatuses) {
		this.targetTypeStatuses = targetTypeStatuses;
	}

}