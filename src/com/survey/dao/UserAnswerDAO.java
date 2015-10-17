package com.survey.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.survey.hibernate.model.Question;
import com.survey.hibernate.model.QuestionGroup;
import com.survey.hibernate.model.User;
import com.survey.hibernate.model.UserAnswer;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserAnswer entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.survey.hibernate.model.UserAnswer
 * @author MyEclipse Persistence Tools
 */

public class UserAnswerDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UserAnswerDAO.class);
	// property constants
	public static final String USER = "user";
	public static final String QUESTION = "question";
	public static final String COMMENT = "comment";
	public static final String ANSWER = "answer";
	public static final String DETAIL_ANSWER = "detailAnswer";
	public static final String BOOLEAN_ANSWER = "booleanAnswer";
	public static final String QUESTION_GROUP = "questionGroup";
	public static final String TITLE = "title";

	protected void initDao() {
		// do nothing
	}

	public void save(UserAnswer transientInstance) {
		log.debug("saving UserAnswer instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserAnswer persistentInstance) {
		log.debug("deleting UserAnswer instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserAnswer findById(java.lang.Integer id) {
		log.debug("getting UserAnswer instance with id: " + id);
		try {
			UserAnswer instance = (UserAnswer) getHibernateTemplate().get(
					"com.survey.hibernate.model.UserAnswer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserAnswer instance) {
		log.debug("finding UserAnswer instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserAnswer instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserAnswer as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUser(Object user) {
		return findByProperty(USER, user);
	}

	public List findByQuestion(Object question) {
		return findByProperty(QUESTION, question);
	}

	public List findByComment(Object comment) {
		return findByProperty(COMMENT, comment);
	}

	public List findByAnswer(Object answer) {
		return findByProperty(ANSWER, answer);
	}

	public List findByDetailAnswer(Object detailAnswer) {
		return findByProperty(DETAIL_ANSWER, detailAnswer);
	}

	public List findByBooleanAnswer(Object booleanAnswer) {
		return findByProperty(BOOLEAN_ANSWER, booleanAnswer);
	}

	public List findByQuestionGroup(Object questionGroup) {
		return findByProperty(QUESTION_GROUP, questionGroup);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findAll() {
		log.debug("finding all UserAnswer instances");
		try {
			String queryString = "from UserAnswer";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserAnswer merge(UserAnswer detachedInstance) {
		log.debug("merging UserAnswer instance");
		try {
			UserAnswer result = (UserAnswer) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserAnswer instance) {
		log.debug("attaching dirty UserAnswer instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserAnswer instance) {
		log.debug("attaching clean UserAnswer instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserAnswerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserAnswerDAO) ctx.getBean("UserAnswerDAO");
	}
	
	public List<Integer> getQuestionIdsCompleted(Integer categoryId, Integer userId) {

		List<Integer>  list = new ArrayList<Integer>();
		Session session = null;
		try {
			
			String sqlQuery = "select distinct question from user_answer where question_group=" + categoryId + " and user=" + userId;
			
			session = getHibernateTemplate().getSessionFactory().openSession();
			
			list = session.createSQLQuery(sqlQuery).list();//.list();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			session.flush();
			session.close();
		}
		return list;
		/*DetachedCriteria idsOnlyCriteria = DetachedCriteria.forClass(UserAnswer.class);
		//add other joins and query params here
		idsOnlyCriteria.setProjection(Projections.distinct(Projections.id()));

		Criteria criteria = session.createCriteria(UserAnswer.class);
		criteria.add(Subqueries.propertyIn("id", idsOnlyCriteria));
		criteria.setFirstResult(0).setMaxResults(50);
		return criteria.list();*/
	}
	public List<UserAnswer> getUserAnswersCompleted(QuestionGroup category,
			User user) {
			
		List<UserAnswer>  list = new ArrayList<UserAnswer>();
		Session session = null;
		
		try {
			
			String sqlQuery = "from UserAnswer where questionGroup=" + category.getId() + " and user=" + user.getId();
				
			session = getHibernateTemplate().getSessionFactory().openSession();
			
			list = session.createQuery(sqlQuery).list();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			session.flush();
			session.close();
		}
		return list;
		
	}
	
	public List<Integer> getQuestionnaireIdsCompletedByUser(User user) {
		
		List<Integer>  list = new ArrayList<Integer>();
		Session session = null;
		try {
			
			String sqlQuery = "select distinct question_group from user_answer where user=" + user.getId();			
			
			session = getHibernateTemplate().getSessionFactory().openSession();
			
			list = session.createSQLQuery(sqlQuery).list();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			session.flush();
			session.close();
		}
		return list;
		
	}

	public List<UserAnswer> getUserAnswersByQuestion(QuestionGroup category, Question question,
			User user) {
			
		List<UserAnswer>  list = new ArrayList<UserAnswer>();
		Session session = null;
		try {
			
			String sqlQuery = "from UserAnswer where questionGroup=" + category.getId() + " and user=" + user.getId() + " and question=" + question.getId();
						
			session = getHibernateTemplate().getSessionFactory().openSession();
			session.beginTransaction();
			list = session.createQuery(sqlQuery).list();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			session.flush();
			session.close();
		}
		return list;
		
	}
}