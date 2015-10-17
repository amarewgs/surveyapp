package com.survey.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.survey.hibernate.model.Answer;

/**
 * A data access object (DAO) providing persistence and search support for
 * Answer entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.survey.hibernate.model.Answer
 * @author MyEclipse Persistence Tools
 */

public class AnswerDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(AnswerDAO.class);
	// property constants
	public static final String CODE = "code";
	public static final String ANSWER = "answer";
	public static final String QUESTION = "question";
	public static final String SCORE = "score";

	protected void initDao() {
		// do nothing
	}

	public void save(Answer transientInstance) {
		log.debug("saving Answer instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Answer persistentInstance) {
		log.debug("deleting Answer instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Answer findById(java.lang.Integer id) {
		log.debug("getting Answer instance with id: " + id);
		try {
			Answer instance = (Answer) getHibernateTemplate().get(
					"com.survey.hibernate.model.Answer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Answer instance) {
		log.debug("finding Answer instance by example");
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
		log.debug("finding Answer instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Answer as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByAnswer(Object answer) {
		return findByProperty(ANSWER, answer);
	}

	public List findByQuestion(Object question) {
		return findByProperty(QUESTION, question);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findAll() {
		log.debug("finding all Answer instances");
		try {
			String queryString = "from Answer";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Answer merge(Answer detachedInstance) {
		log.debug("merging Answer instance");
		try {
			Answer result = (Answer) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Answer instance) {
		log.debug("attaching dirty Answer instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Answer instance) {
		log.debug("attaching clean Answer instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AnswerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AnswerDAO) ctx.getBean("AnswerDAO");
	}
}