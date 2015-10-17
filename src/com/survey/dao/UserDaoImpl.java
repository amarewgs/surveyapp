package com.survey.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.survey.hibernate.model.User;

public class UserDaoImpl implements UserDao {
	
	SessionFactory sessionFactory;

	@Autowired
	Md5PasswordEncoder passwordEncoder;

	public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {

		this.passwordEncoder = passwordEncoder;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUser(String username, String password) {

		return (User) sessionFactory.getCurrentSession().getNamedQuery("User.getUser").setParameter("username", username).setParameter("password", password).uniqueResult();
	}

	@Override
	public User getUserByUsername(String username) {

		Session session = null;
		List<User> list = new ArrayList<User>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("from User where userName = '" + username + "'").list(); // here should be something else than list()
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}

		finally {
			session.close();
		}
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void createUser(String username, String password, String email, String gender, String fullName, 
			Date birthDate, String firstName, String lastName) {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			//Create new instance of Contact and set values in it by reading them from form object

			User user = new User();
			user.setFullName(fullName);
			user.setUserName(username);
			user.setGender(gender);

			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(passwordEncoder.encodePassword(password, username));
			session.save(user);

			tx.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Actual contact insertion will happen at this step
			session.flush();
			session.close();

		}
	}

	@Override
	public void createUser(User user) {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			//Create new instance of Contact and set values in it by reading them from form object
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(), user.getUserName()));
			session.save(user);

			tx.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Actual contact insertion will happen at this step
			session.flush();
			session.close();

		}
	}

	@Transactional
	@Override
	public List<User> getUsersList() {
		Session session = null;
		List<User> list = new ArrayList<User>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			list = session.createQuery("from User").list(); // here should be something else than list()
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}

		finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public User getUserByUserId(Integer userId) {

		Session session = null;
		List<User> list = new ArrayList<User>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("from User where id = " + userId + "").list(); // here should be something else than list()
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}

		finally {
			session.close();
		}
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void updateUser(User user) {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.merge(user);

			tx.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Actual contact insertion will happen at this step
			session.flush();
			session.close();

		}
		
	}

	@Override
	public void changePassword(User user) {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			//Create new instance of Contact and set values in it by reading them from form object
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(), user.getUserName()));
			session.merge(user);

			tx.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Actual contact insertion will happen at this step
			session.flush();
			session.close();

		}
		
	}

	@Override
	public List<User> getUsersByActive(Boolean active) {
	
		Session session = null;
		List<User> list = new ArrayList<User>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("from User where active = " + active + "").list(); // here should be something else than list()
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}

		finally {
			session.close();
		}
		return list;
	}

}
