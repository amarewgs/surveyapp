package com.survey.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.survey.hibernate.model.TargetType;

/**
 * A data access object (DAO) providing persistence and search support for
 * TargetType entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.survey.hibernate.model.TargetType
 * @author MyEclipse Persistence Tools
 */

public class TargetTypeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TargetTypeDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(TargetType transientInstance) {
		log.debug("saving TargetType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TargetType persistentInstance) {
		log.debug("deleting TargetType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TargetType findById(java.lang.Integer id) {
		log.debug("getting TargetType instance with id: " + id);
		try {
			TargetType instance = (TargetType) getHibernateTemplate().get(
					"com.survey.hibernate.model.TargetType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TargetType instance) {
		log.debug("finding TargetType instance by example");
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
		log.debug("finding TargetType instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TargetType as model where model."
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

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all TargetType instances");
		try {
			String queryString = "from TargetType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TargetType merge(TargetType detachedInstance) {
		log.debug("merging TargetType instance");
		try {
			TargetType result = (TargetType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TargetType instance) {
		log.debug("attaching dirty TargetType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TargetType instance) {
		log.debug("attaching clean TargetType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TargetTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TargetTypeDAO) ctx.getBean("TargetTypeDAO");
	}
}