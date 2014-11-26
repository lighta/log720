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

import ca.etsmtl.log720.lab3.domain.Dossier;

/**
 * Home object for domain model class Dossier.
 * @see .Dossier
 * @author Hibernate Tools
 */
public class DossierDao {

	private static final Log log = LogFactory.getLog(DossierDao.class);
	private SessionFactory sessionFactory;

	public DossierDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Dossier> getDossierList() {
		@SuppressWarnings("unchecked")
		List<Dossier> dossiers = (List<Dossier>) sessionFactory.getCurrentSession()
				.createCriteria(Dossier.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return dossiers;
	}
	
	@Transactional
	public void insert(Dossier dossier) {
		sessionFactory.getCurrentSession().save(dossier);
	}
	
	public void persist(Dossier transientInstance) {
		log.debug("persisting Dossier instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Dossier instance) {
		log.debug("attaching dirty Dossier instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Dossier instance) {
		log.debug("attaching clean Dossier instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Dossier persistentInstance) {
		log.debug("deleting Dossier instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dossier merge(Dossier detachedInstance) {
		log.debug("merging Dossier instance");
		try {
			Dossier result = (Dossier) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Dossier findById(int id) {
		log.debug("getting Dossier instance with id: " + id);
		try {
			Dossier instance = (Dossier) sessionFactory.getCurrentSession()
					.get(Dossier.class, id);
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

	public List<Dossier> findByExample(Dossier instance) {
		log.debug("finding Dossier instance by example");
		try {
			@SuppressWarnings("unchecked")
			List<Dossier> results = sessionFactory.getCurrentSession()
					.createCriteria(Dossier.class).add(Example.create(instance))
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
