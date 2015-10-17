package com.survey.service;

import java.util.Date;
import java.util.List;

import com.survey.hibernate.model.User;


/*TODO ESTO PORQUE TENIA SERIALIZABLE*/
public interface IUserManager {

	/* Most changes, user and password */
	public void changeUser(User user, User newUser);

	public List<User> getUsers();
	
	public void createUser(String user, String password, boolean enable, String email, String gender, 
			String fullName, Date birthDate, String firstName, String lastName);

}
