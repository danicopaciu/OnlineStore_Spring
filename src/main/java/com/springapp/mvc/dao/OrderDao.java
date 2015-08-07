package com.springapp.mvc.dao;

import com.springapp.mvc.model.Order;

import java.util.List;

/**
 * Created by daniel.copaciu on 8/7/2015.
 */
public interface OrderDao {

    public void addOrder(Order o);
    public void updateOrder(Order o);
    public void deleteOrder(int orderId);
    public List<Order> viewOrders(int orderId);


}
