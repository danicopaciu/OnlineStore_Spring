package service;

import persistence.dao.OrderDao;
import persistence.model.Order;
import persistence.model.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import persistence.model.Product;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public void addOrder(List<Product> cart) {
        if (cart != null) {
            Order order = new Order();
            setOrderItemsList(cart, order);
            order.setDate(new Date());
            orderDao.addOrder(order);
            cart.clear();
        }
    }

    private void setOrderItemsList(List<Product> cart, Order order) {
        Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
        for (Product product : cart) {
            int quantity = product.getQuantity();
            double subTotal = quantity * product.getPrice();
            OrderItem oi = new OrderItem(product, product.getQuantity(), order, subTotal);
            order.setCost(order.getCost() + subTotal);
            orderItemSet.add(oi);
        }
        order.setItemList(orderItemSet);
    }

    @Override
    @Transactional
    public void deleteOrder(int orderId) {
        orderDao.deleteOrder(orderId);
    }

    @Override
    @Transactional
    public void deleteOrderItem(int orderId, int orderItemId) {
        Order o = orderDao.viewOrder(orderId);
        Set<OrderItem> itemSet = o.getItemList();
        for(OrderItem item : itemSet){
            if(item.getId() == orderItemId){
                itemSet.remove(item);
                break;
            }
        }
        o.setItemList(itemSet);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        if(order != null) {
            orderDao.updateOrder(order);
        }
    }

    @Override
    @Transactional
    public List<Order> viewOrders() {
        return orderDao.viewOrders();
    }
}
