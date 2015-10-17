package com.survey.hibernate.model;


public class UserCampaign implements java.io.Serializable {


	private Integer id;
	private User user;
	private Campaign campaign;
	private boolean active;
	
	public UserCampaign(){
		
	}
	/** full constructor */
	public UserCampaign(User user, Campaign campaign, boolean active) {
		this.user = user;
		this.campaign = campaign;
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Campaign getCampaign() {
		return campaign;
	}
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
}
