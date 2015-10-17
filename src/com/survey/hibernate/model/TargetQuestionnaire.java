package com.survey.hibernate.model;

/**
 * TargetQuestionnaire entity. @author MyEclipse Persistence Tools
 */

public class TargetQuestionnaire implements java.io.Serializable {

	// Fields

	private Integer id;
	private QuestionGroup questionGroup;
	private Boolean active;
	private Campaign campaign;

	// Constructors

	/** default constructor */
	public TargetQuestionnaire() {
	}

	/** full constructor */
	public TargetQuestionnaire(QuestionGroup questionGroup,
			Boolean isActive, Campaign campaign) {
		this.questionGroup = questionGroup;
		this.active = isActive;
		this.campaign = campaign;
	}

	// Property accessors

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuestionGroup getQuestionGroup() {
		return this.questionGroup;
	}

	public void setQuestionGroup(QuestionGroup questionGroup) {
		this.questionGroup = questionGroup;
	}
}