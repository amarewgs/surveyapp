package com.survey.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.survey.hibernate.model.TargetTypeStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * TargetTypeStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.survey.hibernate.model.TargetTypeStatus
 * @author MyEclipse Persistence Tools
 */

public class TargetTypeStatusDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TargetTypeStatusDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(TargetTypeStatus transientInstance) {
		log.debug("saving TargetTypeStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TargetTypeStatus persistentInstance) {
		log.debug("deleting TargetTypeStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TargetTypeStatus findById(java.lang.Integer id) {
		log.debug("getting TargetTypeStatus instance with id: " + id);
		try {
			TargetTypeStatus instance = (TargetTypeStatus) getHibernateTemplate()
					.get("com.survey.hibernate.model.TargetTypeStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TargetTypeStatus instance) {
		log.debug("finding TargetTypeStatus instance by example");
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
		log.debug("finding TargetTypeStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TargetTypeStatus as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TargetTypeStatus instances");
		try {
			String queryString = "from TargetTypeStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TargetTypeStatus merge(TargetTypeStatus detachedInstance) {
		log.debug("merging TargetTypeStatus instance");
		try {
			TargetTypeStatus result = (TargetTypeStatus) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TargetTypeStatus instance) {
		log.debug("attaching dirty TargetTypeStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TargetTypeStatus instance) {
		log.debug("attaching clean TargetTypeStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TargetTypeStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TargetTypeStatusDAO) ctx.getBean("TargetTypeStatusDAO");
	}
}