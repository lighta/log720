// Generated Nov 10, 2014 2:00:28 PM by Hibernate Tools 3.4.0.CR1

package springapp.repository;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import springapp.domain.Dosinfraction;
import springapp.domain.DosinfractionId;

/**
 * Home object for domain model class Dosinfraction.
 * @see .Dosinfraction
 * @author Hibernate Tools
 */
public class DosinfractionHome {

	private static final Log log = LogFactory.getLog(DosinfractionHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Dosinfraction transientInstance) {
		log.debug("persisting Dosinfraction instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Dosinfraction instance) {
		log.debug("attaching dirty Dosinfraction instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dosinfraction instance) {
		log.debug("attaching clean Dosinfraction instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Dosinfraction persistentInstance) {
		log.debug("deleting Dosinfraction instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dosinfraction merge(Dosinfraction detachedInstance) {
		log.debug("merging Dosinfraction instance");
		try {
			Dosinfraction result = (Dosinfraction) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Dosinfraction findById(DosinfractionId id) {
		log.debug("getting Dosinfraction instance with id: " + id);
		try {
			Dosinfraction instance = (Dosinfraction) sessionFactory
					.getCurrentSession().get("Dosinfraction", id);
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

	public List findByExample(Dosinfraction instance) {
		log.debug("finding Dosinfraction instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("Dosinfraction")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
