package com.survey.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.survey.hibernate.model.SectionAttribute;

/**
 * A data access object (DAO) providing persistence and search support for
 * SectionAttribute entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.survey.hibernate.model.SectionAttribute
 * @author MyEclipse Persistence Tools
 */

public class SectionAttributeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SectionAttributeDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(SectionAttribute transientInstance) {
		log.debug("saving SectionAttribute instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SectionAttribute persistentInstance) {
		log.debug("deleting SectionAttribute instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SectionAttribute findById(java.lang.Integer id) {
		log.debug("getting SectionAttribute instance with id: " + id);
		try {
			SectionAttribute instance = (SectionAttribute) getHibernateTemplate()
					.get("com.survey.hibernate.model.SectionAttribute", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SectionAttribute instance) {
		log.debug("finding SectionAttribute instance by example");
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
		log.debug("finding SectionAttribute instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SectionAttribute as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all SectionAttribute instances");
		try {
			String queryString = "from SectionAttribute";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SectionAttribute merge(SectionAttribute detachedInstance) {
		log.debug("merging SectionAttribute instance");
		try {
			SectionAttribute result = (SectionAttribute) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SectionAttribute instance) {
		log.debug("attaching dirty SectionAttribute instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SectionAttribute instance) {
		log.debug("attaching clean SectionAttribute instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SectionAttributeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SectionAttributeDAO) ctx.getBean("SectionAttributeDAO");
	}
}