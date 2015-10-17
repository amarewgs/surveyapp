package com.survey.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.survey.hibernate.model.User;

public class UserRegistrationForm {

	private String confirmPassword;
	private Integer id;
	private String userName;
	private String password;
	private Integer role;
	private String email;

	private String firstName;
	private String lastName;
	private boolean active;
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		//checkPassword();//check

	}
	
	/*private void checkPassword() {
	    if(this.getPassword() == null || this.confirmPassword == null){
	        return;
	    }else if(!this.getPassword().equals(confirmPassword)){
	        this.confirmPassword = null;
	    }
	}*/
	
	
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

	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
