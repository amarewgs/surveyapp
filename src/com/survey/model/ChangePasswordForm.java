package com.survey.model;

import javax.validation.constraints.NotNull;

public class ChangePasswordForm {

	
	private String oldPassword;
	private String password;
	private String confirmPassword;
	private int userId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		
		//checkPassword();
	}
	
/*	private void checkPassword() {
	    if(this.password == null || this.confirmPassword == null ){
	    	this.confirmPassword = null;
	    }else if(!this.password.equals(this.confirmPassword)){
	        this.confirmPassword = null;
	    }
	}*/
}
