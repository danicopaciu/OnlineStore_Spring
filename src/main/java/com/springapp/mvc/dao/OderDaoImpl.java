package com.springapp.mvc.dao;

import com.springapp.mvc.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OderDaoImpl implements OrderDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addOrder(Order o) {
        if(o != null){
            Session session = sessionFactory.getCurrentSession();
            session.persist(o);
        }
    }

    @Override
    public void updateOrder(Order o) {
        if(o != null){
            Session session = sessionFactory.getCurrentSession();
            session.update(o);
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Order o = (Order) session.load(Order.class, orderId);
        if(o != null){
            session.delete(o);
        }
    }

    @Override
    public List<Order> viewOrders(int orderId) {
        return null;
    }
}
