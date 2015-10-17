package com.survey.utils;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import com.survey.hibernate.model.User;
import com.survey.model.UserRegistrationForm;

public class UserValidator {

    public void validate(UserRegistrationForm userForm, Errors errors) {
    	if(userForm.getPassword() == null || userForm.getConfirmPassword() == null){
    		 errors.rejectValue("password", "required", "passwords can not be null");
 	    } else if(!userForm.getPassword().equals(userForm.getConfirmPassword())){
 	    	errors.rejectValue("password", "match", "passwords should match");
 	    } else if(userForm.getUserName() == null || userForm.getUserName().isEmpty()) {
 	    	errors.rejectValue("userName", "null", "user name is required");
 	    } else if(userForm.getUserName().length() < 4) {
 	    	errors.rejectValue("userName", "size", "user name must contain atleast 4 characters!");
 	    }
    }

}

