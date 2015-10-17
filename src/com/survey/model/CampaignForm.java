package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.survey.hibernate.model.Asset;
import com.survey.hibernate.model.Question;
import com.survey.hibernate.model.Status;
import com.survey.hibernate.model.User;

public class CampaignForm {
	
	private Integer campaignId;
	private Asset asset;
	@NotEmpty(message="name is required")
	private String name;
	private String description;
	private boolean active;
	private Status status;
	@NotEmpty(message="one or more questionnaires are required!")
	private List<Integer> questionnaireIds = new ArrayList<Integer>();
	
	private List<User> users = new ArrayList<User>();
	
	private List<UserQuestionnaireForm> questionnaires = new ArrayList<UserQuestionnaireForm>();
	
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<Integer> getQuestionnaireIds() {
		return questionnaireIds;
	}
	public void setQuestionnaireIds(List<Integer> questionnaireIds) {
		this.questionnaireIds = questionnaireIds;
	}	

	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<UserQuestionnaireForm> getQuestionnaires() {
		return questionnaires;
	}
	public void setQuestionnaires(List<UserQuestionnaireForm> questionnaires) {
		this.questionnaires = questionnaires;
	}
}
