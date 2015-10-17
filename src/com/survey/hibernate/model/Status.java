package com.survey.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Status entity. @author MyEclipse Persistence Tools
 */

public class Status implements java.io.Serializable {

	// Fields

	private Integer id;
	@NotEmpty(message="name is required")
	private String name;
	private String description;
	private Boolean active;
	private Set targetTypeStatuses = new HashSet(0);
	private Set assets = new HashSet(0);

	// Constructors

	/** default constructor */
	public Status() {
	}

	/** full constructor */
	public Status(String name, String description, Boolean active,
			Set targetTypes, Set assets) {
		this.name = name;
		this.description = description;
		this.active = active;
		this.targetTypeStatuses = targetTypes;
		this.assets = assets;
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

	public Set getAssets() {
		return this.assets;
	}

	public void setAssets(Set assets) {
		this.assets = assets;
	}
	public Set getTargetTypeStatuses() {
		return targetTypeStatuses;
	}

	public void setTargetTypeStatuses(Set targetTypeStatuses) {
		this.targetTypeStatuses = targetTypeStatuses;
	}
}