package com.survey.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.survey.hibernate.model.Template;

/**
 * A data access object (DAO) providing persistence and search support for
 * Template entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.survey.hibernate.model.Template
 * @author MyEclipse Persistence Tools
 */

public class TemplateDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TemplateDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(Template transientInstance) {
		log.debug("saving Template instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Template persistentInstance) {
		log.debug("deleting Template instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Template findById(java.lang.Integer id) {
		log.debug("getting Template instance with id: " + id);
		try {
			Template instance = (Template) getHibernateTemplate().get(
					"com.survey.hibernate.model.Template", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Template instance) {
		log.debug("finding Template instance by example");
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
		log.debug("finding Template instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Template as model where model."
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
		log.debug("finding all Template instances");
		try {
			String queryString = "from Template";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Template merge(Template detachedInstance) {
		log.debug("merging Template instance");
		try {
			Template result = (Template) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Template instance) {
		log.debug("attaching dirty Template instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Template instance) {
		log.debug("attaching clean Template instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TemplateDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TemplateDAO) ctx.getBean("TemplateDAO");
	}
}