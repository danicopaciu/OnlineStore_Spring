package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import persistence.dao.OrderDao;
import persistence.model.Order;
import persistence.model.OrderItem;
import persistence.model.Product;
import persistence.model.UserRole;
import utils.UserUtils;

import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    @Transactional
    public void addOrder(List<Product> cart) {
        if (cart != null) {
            Order order = new Order();
            setOrderItemsList(cart, order);
            setUser(order);
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

    private void setUser(Order order) {
        User user = UserUtils.getAuthenticatedUser();
        if (user != null) {
            String username = user.getUsername();
            String password = user.getPassword();
            persistence.model.User u = new persistence.model.User(username, password, true);
            setUserRole(user, u);
            order.setUser(u);
        }
    }

    private void setUserRole(User user, persistence.model.User u) {
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        Set<UserRole> userRoles = new HashSet<UserRole>();
        for (GrantedAuthority authority : authorities) {
            userRoles.add(new UserRole(u, authority.getAuthority()));
        }
        u.setUserRoles(userRoles);
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
        for (OrderItem item : itemSet) {
            if (item.getId() == orderItemId) {
                itemSet.remove(item);
                break;
            }
        }
        if (itemSet.size() == 0) {
            orderDao.deleteOrder(orderId);
        }
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        if (order != null) {
            orderDao.updateOrder(order);
        }
    }

    @Override
    @Transactional
    public List<Order> viewOrders() {
        return orderDao.viewOrders();
    }

    @Override
    @Transactional
    public List<Order> viewOrdersForUser() {
        User user = UserUtils.getAuthenticatedUser();
        if (user != null) {
            String username = user.getUsername();
            return orderDao.viewOrdersForUser(username);
        }
        return null;
    }
}
