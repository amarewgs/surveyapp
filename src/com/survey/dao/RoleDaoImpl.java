package com.survey.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.survey.hibernate.model.Role;
import com.survey.hibernate.model.User;


public class RoleDaoImpl implements RoleDao {

	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}
	@Override
	public Role getRoleById(Integer roleId) {

		Session session = null;
		List<Role> list = new ArrayList<Role>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("from Role where Id = " + roleId).list(); // here should be something else than list()
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
	public List<Role> getRoles() {

		Session session = null;
		List<Role> list = new ArrayList<Role>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("from Role").list(); // here should be something else than list()
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
	public Role getRoleByName(String role) {

		Session session = null;
		List<Role> list = new ArrayList<Role>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("from Role where name = '" + role + "'").list(); // here should be something else than list()
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
	public void createRole(Role role) {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(role);

			tx.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Actual contact insertion will happen at this step
			session.flush();
			session.close();

		}
		
	}

}
