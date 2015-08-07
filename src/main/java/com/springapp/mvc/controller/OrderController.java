package com.springapp.mvc.controller;

import com.springapp.mvc.model.Product;
import com.springapp.mvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by daniel.copaciu on 8/7/2015.
 */
@Controller
@SessionAttributes({"cart"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/user/SubmitOrder", method = RequestMethod.POST)
    public String submitOrder(@ModelAttribute(value = "cart")List<Product> cart,
                              ModelMap model){
        if(cart != null){
            orderService.addOrder(cart);
        }
        return "user";
    }
}
