package service;


import persistence.model.Order;
import persistence.model.Product;

import java.util.List;

/**
 * Created by daniel.copaciu on 8/7/2015.
 */
public interface OrderService {

    public void addOrder(List<Product> cart);
    public void deleteOrder(int orderId);
    public void deleteOrderItem(int orderId, int orderItemId);
    public void updateOrder(Order order);
    public List<Order> viewOrders();
    public List<Order> viewOrdersForUser();
}
