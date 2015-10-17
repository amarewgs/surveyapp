package com.survey.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.survey.hibernate.model.AssetAttribute;

/**
 * A data access object (DAO) providing persistence and search support for
 * AssetAttribute entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.survey.hibernate.model.AssetAttribute
 * @author MyEclipse Persistence Tools
 */

public class AssetAttributeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AssetAttributeDAO.class);
	// property constants
//	public static final String ASSET = "asset";
//	public static final String SECTION = "section";
//	public static final String ATTRIBUTE = "attribute";
	public static final String VALUE = "value";

	protected void initDao() {
		// do nothing
	}

	public void save(AssetAttribute transientInstance) {
		log.debug("saving AssetAttribute instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssetAttribute persistentInstance) {
		log.debug("deleting AssetAttribute instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssetAttribute findById(java.lang.Integer id) {
		log.debug("getting AssetAttribute instance with id: " + id);
		try {
			AssetAttribute instance = (AssetAttribute) getHibernateTemplate()
					.get("com.survey.hibernate.model.AssetAttribute", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssetAttribute instance) {
		log.debug("finding AssetAttribute instance by example");
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
		log.debug("finding AssetAttribute instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AssetAttribute as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

//	public List findByAsset(Object asset) {
//		return findByProperty(ASSET, asset);
//	}
//
//	public List findBySection(Object section) {
//		return findByProperty(SECTION, section);
//	}
//
//	public List findByAttribute(Object attribute) {
//		return findByProperty(ATTRIBUTE, attribute);
//	}

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findAll() {
		log.debug("finding all AssetAttribute instances");
		try {
			String queryString = "from AssetAttribute";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssetAttribute merge(AssetAttribute detachedInstance) {
		log.debug("merging AssetAttribute instance");
		try {
			AssetAttribute result = (AssetAttribute) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssetAttribute instance) {
		log.debug("attaching dirty AssetAttribute instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssetAttribute instance) {
		log.debug("attaching clean AssetAttribute instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssetAttributeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AssetAttributeDAO) ctx.getBean("AssetAttributeDAO");
	}
}