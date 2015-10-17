package com.survey.hibernate.model;

/**
 * TargetTypeStatus entity. @author MyEclipse Persistence Tools
 */

public class TargetTypeStatus implements java.io.Serializable {

	// Fields

	private Integer id;
	private TargetType targetType;
	private Status status;

	// Constructors

	/** default constructor */
	public TargetTypeStatus() {
	}

	/** full constructor */
	public TargetTypeStatus(TargetType targetType, Status status) {
		this.targetType = targetType;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TargetType getTargetType() {
		return this.targetType;
	}

	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}