package com.survey.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.survey.hibernate.model.TargetQuestionnaire;

/**
 * A data access object (DAO) providing persistence and search support for
 * TargetQuestionnaire entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.survey.hibernate.model.TargetQuestionnaire
 * @author MyEclipse Persistence Tools
 */

public class TargetQuestionnaireWDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TargetQuestionnaireWDAO.class);
	// property constants
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(TargetQuestionnaire transientInstance) {
		log.debug("saving TargetQuestionnaire instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TargetQuestionnaire persistentInstance) {
		log.debug("deleting TargetQuestionnaire instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TargetQuestionnaire findById(java.lang.Integer id) {
		log.debug("getting TargetQuestionnaire instance with id: " + id);
		try {
			TargetQuestionnaire instance = (TargetQuestionnaire) getHibernateTemplate()
					.get("com.survey.hibernate.model.TargetQuestionnaire", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TargetQuestionnaire instance) {
		log.debug("finding TargetQuestionnaire instance by example");
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
		log.debug("finding TargetQuestionnaire instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TargetQuestionnaire as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByActive(Object isActive) {
		return findByProperty(ACTIVE, isActive);
	}

	public List findAll() {
		log.debug("finding all TargetQuestionnaire instances");
		try {
			String queryString = "from TargetQuestionnaire";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TargetQuestionnaire merge(TargetQuestionnaire detachedInstance) {
		log.debug("merging TargetQuestionnaire instance");
		try {
			TargetQuestionnaire result = (TargetQuestionnaire) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TargetQuestionnaire instance) {
		log.debug("attaching dirty TargetQuestionnaire instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TargetQuestionnaire instance) {
		log.debug("attaching clean TargetQuestionnaire instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TargetQuestionnaireWDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TargetQuestionnaireWDAO) ctx.getBean("TargetQuestionnaireDAO");
	}
}