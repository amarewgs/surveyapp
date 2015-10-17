package com.survey.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.survey.hibernate.model.AssetAttribute;
import com.survey.hibernate.model.Status;
import com.survey.hibernate.model.TargetType;

public class AssetForm {

	private Integer assetId;
	private TargetType targetType;
	private Status status;
	@NotEmpty(message="name is required")
	private String name;
	private String description;
	private Boolean active;
	private Timestamp campaignDate;	
	@NotEmpty(message="add one or more attributes")
	private List<AssetAttribute> assetAttributes = new ArrayList<AssetAttribute>();

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}
	
	public TargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getCampaignDate() {
		return campaignDate;
	}

	public void setCampaignDate(Timestamp campaignDate) {
		this.campaignDate = campaignDate;
	}
	
	public List<AssetAttribute> getAssetAttributes() {
		return assetAttributes;
	}

	public void setAssetAttributes(List<AssetAttribute> assetAttributes) {
		this.assetAttributes = assetAttributes;
	}

}
