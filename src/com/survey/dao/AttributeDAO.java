package com.survey.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.survey.hibernate.model.Attribute;

/**
 * A data access object (DAO) providing persistence and search support for
 * Attribute entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.survey.hibernate.model.Attribute
 * @author MyEclipse Persistence Tools
 */

public class AttributeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AttributeDAO.class);
	// property constants
	public static final String LABEL = "label";
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";
	public static final String FIELD_TYPE = "fieldType";
	public static final String VALUE = "value";
	public static final String REQUIRED = "required";

	protected void initDao() {
		// do nothing
	}

	public void save(Attribute transientInstance) {
		log.debug("saving Attribute instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Attribute persistentInstance) {
		log.debug("deleting Attribute instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Attribute findById(java.lang.Integer id) {
		log.debug("getting Attribute instance with id: " + id);
		try {
			Attribute instance = (Attribute) getHibernateTemplate().get(
					"com.survey.hibernate.model.Attribute", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Attribute instance) {
		log.debug("finding Attribute instance by example");
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
		log.debug("finding Attribute instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Attribute as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLabel(Object label) {
		return findByProperty(LABEL, label);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findByFieldType(Object fieldType) {
		return findByProperty(FIELD_TYPE, fieldType);
	}

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findByRequired(Object required) {
		return findByProperty(REQUIRED, required);
	}

	public List findAll() {
		log.debug("finding all Attribute instances");
		try {
			String queryString = "from Attribute";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Attribute merge(Attribute detachedInstance) {
		log.debug("merging Attribute instance");
		try {
			Attribute result = (Attribute) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Attribute instance) {
		log.debug("attaching dirty Attribute instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Attribute instance) {
		log.debug("attaching clean Attribute instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AttributeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AttributeDAO) ctx.getBean("AttributeDAO");
	}
}