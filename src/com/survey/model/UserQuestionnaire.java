package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class UserQuestionnaire {

	@NotEmpty(message = "fill one or more questions")
	private List<UserAnswerForm> userAnswerForm = new ArrayList<UserAnswerForm>();

	private Integer campaignId;
	private Integer groupId;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	public List<UserAnswerForm> getUserAnswerForm() {
		return userAnswerForm;
	}

	public void setUserAnswerForm(List<UserAnswerForm> userAnswerForm) {
		this.userAnswerForm = userAnswerForm;
	}
	
}
