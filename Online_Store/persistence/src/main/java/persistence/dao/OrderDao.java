package persistence.dao;


import persistence.model.Order;

import java.util.List;


public interface OrderDao {

    public void addOrder(Order o);

    public void updateOrder(Order o);

    public void deleteOrder(int orderId);

    public List<Order> viewOrders();

    public Order viewOrder(int orderId);


}
