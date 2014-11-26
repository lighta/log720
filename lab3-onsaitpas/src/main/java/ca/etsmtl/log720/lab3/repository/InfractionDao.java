// Generated Nov 10, 2014 2:00:28 PM by Hibernate Tools 3.4.0.CR1
package ca.etsmtl.log720.lab3.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.transaction.annotation.Transactional;

import ca.etsmtl.log720.lab3.domain.Infraction;

/**
 * Home object for domain model class Infraction.
 * @see .Infraction
 * @author Hibernate Tools
 */
public class InfractionDao {

	private static final Log log = LogFactory.getLog(InfractionDao.class);
	private SessionFactory sessionFactory;

	public InfractionDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Infraction> getInfractionList() {
		@SuppressWarnings("unchecked")
		List<Infraction> infractions = (List<Infraction>) sessionFactory.getCurrentSession()
				.createCriteria(Infraction.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return infractions;
	}
	
	@Transactional
	public void insert(Infraction infraction) {
		sessionFactory.getCurrentSession().save(infraction);
	}
	
	//partie autogenere

	public void persist(Infraction transientInstance) {
		log.debug("persisting Infraction instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Infraction instance) {
		log.debug("attaching dirty Infraction instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Infraction instance) {
		log.debug("attaching clean Infraction instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Infraction persistentInstance) {
		log.debug("deleting Infraction instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Infraction merge(Infraction detachedInstance) {
		log.debug("merging Infraction instance");
		try {
			Infraction result = (Infraction) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Infraction findById(int id) {
		log.debug("getting Infraction instance with id: " + id);
		try {
			Infraction instance = (Infraction) sessionFactory
					.getCurrentSession().get(Infraction.class, id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Infraction> findByExample(Infraction instance) {
		log.debug("finding Infraction instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Infraction> results = sessionFactory.getCurrentSession()
					.createCriteria(Infraction.class).add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
