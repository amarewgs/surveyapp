package com.survey.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.validation.Errors;

import com.survey.dao.RoleDao;
import com.survey.dao.UserDao;
import com.survey.hibernate.model.User;
import com.survey.model.ChangePasswordForm;
import com.survey.model.UserRegistrationForm;

public class ChangePasswordValidator {
	
	@Autowired
	Md5PasswordEncoder passwordEncoder;
	@Autowired(required = false)
	private UserDao	userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {

		this.passwordEncoder = passwordEncoder;
	}
	
	 public void validate(ChangePasswordForm changePasswordForm, Errors errors) {
		 
		 User user = this.userDao.getUserByUserId(changePasswordForm.getUserId());
		 
		 if(!this.passwordEncoder.encodePassword(changePasswordForm.getOldPassword(), user.getUserName()).equals(user.getPassword())) {
			 errors.rejectValue("password", "valid", "old password can not be null");
		 }
		 if(changePasswordForm.getPassword() == null || changePasswordForm.getConfirmPassword() == null){
			 errors.rejectValue("password", "required", "passwords can not be null");
		 } else if(!changePasswordForm.getPassword().equals(changePasswordForm.getConfirmPassword())){
	    	errors.rejectValue("password", "match", "passwords should match");
	    } 
	}

}
