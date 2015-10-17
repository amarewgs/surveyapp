package com.survey.hibernate.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Asset entity. @author MyEclipse Persistence Tools
 */

public class Campaign implements java.io.Serializable {

	// Fields

	private Integer id;
	private Status status;
	@NotEmpty(message="name is required")
	private String name;
	private String description;
	private Boolean active;
	private Date campaignDate;
	private Asset asset;
	
	private Set<TargetQuestionnaire> targetQuestionnaires = new HashSet<TargetQuestionnaire>();
	private Set<UserCampaign> campaignsForUser = new HashSet<UserCampaign>();
	// Constructors
	/** default constructor */
	public Campaign() {
	}

	/** full constructor */
	public Campaign(Status status, String name,
			String description, Boolean active, Date campaignDate, Asset asset) {
		this.status = status;
		this.name = name;
		this.description = description;
		this.active = active;
		this.campaignDate = campaignDate;
		this.asset = asset;
	}

	// Property accessors

	public Set<UserCampaign> getCampaignsForUser() {
		return campaignsForUser;
	}

	public void setCampaignsForUser(Set<UserCampaign> campaignsForUser) {
		this.campaignsForUser = campaignsForUser;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
	
	public Date getCampaignDate() {
		return campaignDate;
	}

	public void setCampaignDate(Date campaignDate) {
		this.campaignDate = campaignDate;
	}
	
	public Set<TargetQuestionnaire> getTargetQuestionnaires() {
		return targetQuestionnaires;
	}

	public void setTargetQuestionnaires(
			Set<TargetQuestionnaire> targetQuestionnaires) {
		this.targetQuestionnaires = targetQuestionnaires;
	}

}