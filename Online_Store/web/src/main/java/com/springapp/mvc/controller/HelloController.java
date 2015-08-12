package com.springapp.mvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import persistence.model.Product;
import persistence.model.User;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        HttpSession session,
                        ModelMap model) {

        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }

        if (logout != null) {
            List<Product> cart = (List<Product>) session.getAttribute("cart");
            if (cart != null) {
                return "redirect:/user/DeleteCart";
            }
            model.addAttribute("msg", "You have been successfully logged out!");
        }

        return "hello";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String printAdmin() {
        return "redirect:/admin/ProductList";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String printUser() {
        return "redirect:/user/ProductList";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String printRegister(){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam (value = "username") String username,
                           @RequestParam (value = "password") String password,
                           @RequestParam (value = "matchingPassword") String matchingPassword,
                           ModelMap model){
        if(password.equals(matchingPassword)){
            User u = userService.registerNewAccount(username, password);
            if(u != null) {
                model.addAttribute("msg", "You were successfully registered!");
            }else{
                model.addAttribute("msg", "Registration process went wrong!");
            }
        }else{
            model.addAttribute("msg", "The password is not matching!");
        }
        return "hello";
    }
}