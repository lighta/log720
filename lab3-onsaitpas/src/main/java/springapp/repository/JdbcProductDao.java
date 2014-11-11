package springapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import springapp.domain.Product;

public class JdbcProductDao extends SimpleJdbcDaoSupport implements ProductDao {
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public List<Product> getProductList() {
    	// Begin unit of work
    	List<Product> products = HibernateUtil.getSessionFactory().getCurrentSession()
				.createCriteria(Product.class).list();
        return products;
    }

    public void saveProduct(Product prod) {
        logger.info("Saving product: " + prod.getDescription());
        HibernateUtil.getSessionFactory().getCurrentSession().saveOrUpdate(prod);
    }
}