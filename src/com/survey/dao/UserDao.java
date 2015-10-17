package com.survey.dao;

import java.util.Date;
import java.util.List;

import com.survey.hibernate.model.User;

public interface UserDao {
	// Return a user by login credentials:
	public User getUser(String username, String password);
	public User getUserByUsername(String username);
	
	public void createUser(String username, String password, String email, String gender, String fullName, 
			Date birthDate, String firstName, String lastName);
	public void createUser(User user);
	public void updateUser(User user);
	public void changePassword(User user);
	public List<User> getUsersList();
	public User getUserByUserId(Integer userId);
	public List<User> getUsersByActive(Boolean active);
}