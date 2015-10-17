package com.survey.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.survey.hibernate.model.Questionnaire;
import com.survey.hibernate.model.TargetQuestionnaire;

public class TargetQuestionnaireDaoImpl implements TargetQuestionnaireDao {


	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void createTargetQuestionnaire(
			TargetQuestionnaire targetQuestionnaire) {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(targetQuestionnaire);

			session.flush();
			tx.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Actual contact insertion will happen at this step
			// session.flush();
			session.close();

		}

	}

	@Override
	public List<Questionnaire> getQuestionnairesByTargetId(Integer targetId) {

		Session session = null;
		List<Questionnaire> list = new ArrayList<Questionnaire>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			list = session.createQuery(
					"from TargetQuestionnaire where target = " + targetId)
					.list(); // here should be something else than list()
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
	public List<TargetQuestionnaire> getAllTargetQuestionnaires() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TargetQuestionnaire getTargetQuestionnaireById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TargetQuestionnaire getTargetQuestionnaireByQuestionnaireAndTarget(
			Integer questionnaireId, Integer targetId) {
		// TODO Auto-generated method stub
		return null;
	}

}
