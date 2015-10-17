package com.survey.hibernate.model;

import java.util.Date;

/**
 * Survey entity. @author MyEclipse Persistence Tools
 */

public class Survey implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private String createdBy;

	// Constructors

	/** default constructor */
	public Survey() {
	}

	/** full constructor */
	public Survey(String title, String description, Date startDate,
			Date endDate, String createdBy) {
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}