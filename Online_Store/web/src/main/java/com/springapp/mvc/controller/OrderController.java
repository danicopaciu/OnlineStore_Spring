package com.springapp.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import persistence.model.Order;
import persistence.model.Product;
import service.OrderService;

import java.util.List;

@Controller
@SessionAttributes({"cart"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/user/SubmitOrder", method = RequestMethod.POST)
    public String submitOrder(@ModelAttribute(value = "cart") List<Product> cart) {
        if (cart != null) {
            orderService.addOrder(cart);
        }
        return "redirect:/user/ProductList";
    }

    @RequestMapping(value = "/admin/ViewOrders", method = RequestMethod.GET)
    public String viewOrders(ModelMap model) {
        List<Order> orders = orderService.viewOrders();
        model.addAttribute("orderList", orders);
        return "orders";
    }

    @RequestMapping(value = "/admin/deleteOrder", method = RequestMethod.GET)
    public String deleteOrder(@RequestParam(value = "orderId") int orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/admin/ViewOrders";
    }

    @RequestMapping(value = "/admin/deleteOrderItem", method = RequestMethod.GET)
    public String deleteOrderItem(@RequestParam(value = "orderId") int orderId,
                                  @RequestParam(value = "orderItemId") int orderItemId) {
        orderService.deleteOrderItem(orderId, orderItemId);
        return "redirect:/admin/ViewOrders";
    }

    @RequestMapping(value = "/user/deleteOrder", method = RequestMethod.GET)
    public String deleteUserOrder(@RequestParam(value = "orderId") int orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/user/ViewOrders";
    }

    @RequestMapping(value = "/user/deleteOrderItem", method = RequestMethod.GET)
    public String deleteUserOrderItem(@RequestParam(value = "orderId") int orderId,
                                      @RequestParam(value = "orderItemId") int orderItemId) {
        orderService.deleteOrderItem(orderId, orderItemId);
        return "redirect:/user/ViewOrders";
    }

    @RequestMapping(value = "/user/ViewOrders", method = RequestMethod.GET)
    public String viewUserOrders(ModelMap model) {
        List<Order> orderList = orderService.viewOrdersForUser();
        if (orderList != null) {
            model.addAttribute("orderList", orderList);
        }
        return "user_orders";
    }

    @RequestMapping(value = "/admin/ViewOrdersForUser", method = RequestMethod.GET)
    public String viewOrdersForUser(@RequestParam(value = "username") String username,
                                    ModelMap model) {
        List<Order> orders = orderService.viewOrdersForUser(username);
        model.addAttribute("orderList", orders);
        return "orders";
    }
}
