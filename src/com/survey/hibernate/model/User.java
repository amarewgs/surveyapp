package com.survey.hibernate.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {
	
	private static final long serialVersionUID = 8774578498503860663L;

	/**Fields
	 * 
	 */
	private Integer id;
	private String userName;
	private String password;
	private String fullName;
	private String gender;
	private Date birthDate;
	private Role role;
	@Email(message="invalid email format")
	private String email;
	private boolean active;

	@NotEmpty(message="first name is required")
	private String firstName;
	@NotEmpty(message="last name is required")
	private String lastName;
	
	private Set<UserCampaign> userCampaigns = new HashSet<UserCampaign>();
	private Set<UserAnswer> userQuestionAnswers = new HashSet<UserAnswer>();
	// Constructors	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String userName, String password, String fullName,
			String gender, Date birthDate, Role role, String email, String firstName, String lastName, Set<UserCampaign> userCampaigns) {
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.role = role;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userCampaigns = userCampaigns;
	}

	// Property accessors

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Set<UserCampaign> getUserCampaigns() {
		return userCampaigns;
	}

	public void setUserCampaigns(Set<UserCampaign> userCampaigns) {
		this.userCampaigns = userCampaigns;
	}
	
	public Set<UserAnswer> getUserQuestionAnswers() {
		return userQuestionAnswers;
	}

	public void setUserQuestionAnswers(Set<UserAnswer> userQuestionAnswers) {
		this.userQuestionAnswers = userQuestionAnswers;
	}
}