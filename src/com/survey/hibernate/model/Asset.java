package com.survey.hibernate.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Asset entity. @author MyEclipse Persistence Tools
 */

public class Asset implements java.io.Serializable {

	// Fields

	private Integer id;
	private TargetType targetType;
	private Status status;
	@NotEmpty(message="name is required")
	private String name;
	private String description;
	private Boolean active;
	private Timestamp campaignDate;
	private Set<AssetAttribute> assetAttributes = new HashSet<AssetAttribute>(0);
	private Set campaignsForAsset = new HashSet(0);

	// Constructors
	/** default constructor */
	public Asset() {
	}

	/** full constructor */
	public Asset(TargetType targetType, Status status, String name,
			String description, Boolean active, Timestamp campaignDate,
			Set<Campaign> campaignsForAsset,
			Set targetQuestionnairesForAsset, Set<AssetAttribute> assetAttributes) {
		this.targetType = targetType;
		this.status = status;
		this.name = name;
		this.description = description;
		this.active = active;
		this.campaignDate = campaignDate;
		this.campaignsForAsset = campaignsForAsset;
		this.assetAttributes = assetAttributes;
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

	public Timestamp getCampaignDate() {
		return this.campaignDate;
	}

	public void setCampaignDate(Timestamp campaignDate) {
		this.campaignDate = campaignDate;
	}
	
	public Set<AssetAttribute> getAssetAttributes() {
		return assetAttributes;
	}

	public void setAssetAttributes(Set<AssetAttribute> assetAttributes) {
		this.assetAttributes = assetAttributes;
	}

	public Set getCampaignsForAsset() {
		return campaignsForAsset;
	}

	public void setCampaignsForAsset(Set campaignsForAsset) {
		this.campaignsForAsset = campaignsForAsset;
	}

}