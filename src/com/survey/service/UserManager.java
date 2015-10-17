package com.survey.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.dao.UserDao;
import com.survey.hibernate.model.User;

/*
 * Estamos indicando que esta es una clase relacionada con la capa de servicio (clases de negocio),
 * y que debe ser un Singleton.
 * */

@Service
public class UserManager implements IUserManager {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Autowired
	private UserDao				userDao;
	private List<User>			users;
	
	public void setUserDao(UserDao userdao) {
		this.userDao = userdao;
	}

	public void changeUser(User user, User newUser) {
		users.remove(user);
		users.add(newUser);
	}

	public List<User> getUsers() {
		// return users;
		return userDao.getUsersList();
	}

	/* Not implemented */
	public void setUsers(List<User> list) {
		this.users = list;
	}

	public void createUser(String user, String password, boolean enable, String email, String gender, 
			String fullName, Date birthDate, String firstName, String lastName) {
		// TODO Auto-generated method stub
		
		this.userDao.createUser(user, password, email, gender, fullName, birthDate, firstName, lastName);
		
	}

	public void createUser(User user) {
		
		this.userDao.createUser(user);
	}
}
