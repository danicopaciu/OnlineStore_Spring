package com.springapp.mvc.service;

import com.springapp.mvc.dao.OrderDao;
import com.springapp.mvc.model.Order;
import com.springapp.mvc.model.OrderItem;
import com.springapp.mvc.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public void addOrder(List<Product> cart) {
        if(cart != null){
            Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
            Order order = new Order();
            for(Product product : cart){
                OrderItem oi = new OrderItem(product, product.getQuantity());
                oi.setOrder(order);
                orderItemSet.add(oi);
            }
            order.setDate(new Date());
            order.setItemList(orderItemSet);
            orderDao.addOrder(order);
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        orderDao.deleteOrder(orderId);
    }

    @Override
    public List<Order> viewOrders(int orderId) {
         return orderDao.viewOrders(orderId);
    }
}
