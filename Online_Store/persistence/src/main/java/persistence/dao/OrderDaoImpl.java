package persistence.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import persistence.model.Order;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    public static final String USER_FK = "username";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addOrder(Order o) {
        if (o != null) {
            Session session = sessionFactory.getCurrentSession();
            session.save(o);
        }
    }

    @Override
    public void updateOrder(Order o) {
        if (o != null) {
            Session session = sessionFactory.getCurrentSession();
            session.update(o);
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Order o = (Order) session.load(Order.class, orderId);
        if (o != null) {
            session.delete(o);
        }
    }

    @Override
    public List<Order> viewOrders() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Order").list();
    }

    @Override
    public List<Order> viewOrdersForUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order where username = :username ");
        query.setParameter(USER_FK, username);
        return query.list();
    }

    @Override
    public Order viewOrder(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        return (Order) session.load(Order.class, orderId);
    }
}
