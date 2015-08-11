package persistence.dao;

import persistence.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addProduct(Product p) {
        Session session = sessionFactory.getCurrentSession();
        if (p != null) {
            session.persist(p);
        }
    }

    @Override
    public void updateProduct(Product p) {
        Session session = sessionFactory.getCurrentSession();
        if (p != null && session != null) {
            session.update(p);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        Session session = sessionFactory.getCurrentSession();
        Product p = (Product) session.load(Product.class, productId);
        if (p != null) {
            session.delete(p);
        }
    }

    @Override
    public Product viewProduct(int productId) {
        Session session = sessionFactory.getCurrentSession();
        return (Product) session.get(Product.class, productId);
    }

    @Override
    public List<Product> viewAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product").list();
    }

}
