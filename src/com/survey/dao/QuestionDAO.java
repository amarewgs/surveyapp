package com.survey.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.survey.hibernate.model.Question;

/**
 * A data access object (DAO) providing persistence and search support for
 * Question entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.survey.hibernate.model.Question
 * @author MyEclipse Persistence Tools
 */

public class QuestionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(QuestionDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String QUESTION = "question";
	public static final String TEXT = "text";
	public static final String ORDER = "order";
	public static final String MANDATORY = "mandatory";
	public static final String QUESTION_TYPE = "questionType";
	public static final String QUESTION_GROUP = "questionGroup";
	public static final String QUESTION_ORDER = "questionOrder";
	public static final String COMMENT_ENABLED = "commentEnabled";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(Question transientInstance) {
		log.debug("saving Question instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Question persistentInstance) {
		log.debug("deleting Question instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Question findById(java.lang.Integer id) {
		log.debug("getting Question instance with id: " + id);
		try {
			Question instance = (Question) getHibernateTemplate().get(
					"com.survey.hibernate.model.Question", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Question instance) {
		log.debug("finding Question instance by example");
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
		log.debug("finding Question instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Question as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByQuestion(Object question) {
		return findByProperty(QUESTION, question);
	}

	public List findByText(Object text) {
		return findByProperty(TEXT, text);
	}

	public List findByOrder(Object order) {
		return findByProperty(ORDER, order);
	}

	public List findByMandatory(Object mandatory) {
		return findByProperty(MANDATORY, mandatory);
	}

	public List findByQuestionType(Object questionType) {
		return findByProperty(QUESTION_TYPE, questionType);
	}

	public List findByQuestionGroup(Object questionGroup) {
		return findByProperty(QUESTION_GROUP, questionGroup);
	}

	public List findByQuestionOrder(Object questionOrder) {
		return findByProperty(QUESTION_ORDER, questionOrder);
	}

	public List findByCommentEnabled(Object commentEnabled) {
		return findByProperty(COMMENT_ENABLED, commentEnabled);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all Question instances");
		try {
			String queryString = "from Question";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Question merge(Question detachedInstance) {
		log.debug("merging Question instance");
		try {
			Question result = (Question) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Question instance) {
		log.debug("attaching dirty Question instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Question instance) {
		log.debug("attaching clean Question instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static QuestionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (QuestionDAO) ctx.getBean("QuestionDAO");
	}
}