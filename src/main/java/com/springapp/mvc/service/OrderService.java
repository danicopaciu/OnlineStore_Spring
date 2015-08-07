package com.springapp.mvc.service;

import com.springapp.mvc.model.Order;
import com.springapp.mvc.model.Product;

import java.util.List;

/**
 * Created by daniel.copaciu on 8/7/2015.
 */
public interface OrderService {

    public void addOrder(List<Product> cart);
    public void deleteOrder(int orderId);
    public List<Order> viewOrders(int orderId);
}
