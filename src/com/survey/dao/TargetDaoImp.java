package com.survey.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.survey.hibernate.model.Role;
import com.survey.hibernate.model.Target;

public class TargetDaoImp implements TargetDao {

	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void createTarget(Target target) {
		 
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(target);

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
	public Target getTragetById(Integer id) {
	

		Session session = null;
		List<Target> list = new ArrayList<Target>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("from Target where Id = " + id).list(); // here should be something else than list()
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
	public Target getTargetByName(String name) {

		Session session = null;
		List<Target> list = new ArrayList<Target>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("from Role where Name = '" + name + "'").list(); // here should be something else than list()
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
	public List<Target> getAllTargets() {

		Session session = null;
		List<Target> list = new ArrayList<Target>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("from Target").list(); // here should be something else than list()
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}

		finally {
			session.close();
		}
		return (List<Target>) (list.isEmpty() ? null : list.get(0));
	}

}
