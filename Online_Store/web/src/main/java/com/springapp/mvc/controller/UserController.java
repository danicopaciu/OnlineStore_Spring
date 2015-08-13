package com.springapp.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import persistence.model.User;
import service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String printRegister() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "matchingPassword") String matchingPassword,
                           ModelMap model) {
        if (password.equals(matchingPassword)) {
            User u = userService.registerNewAccount(username, password);
            if (u != null) {
                model.addAttribute("msg", "You were successfully registered!");
            } else {
                model.addAttribute("msg", "This username already exists!");
            }
        } else {
            model.addAttribute("msg", "The password is not matching!");
        }
        return "hello";
    }

    @RequestMapping(value = "/user/RemoveAccount", method = RequestMethod.GET)
    public String removeAccount() {
        userService.removeAccount();
        return "hello";
    }

    @RequestMapping(value = "/user/ChangePassword", method = RequestMethod.GET)
    public String printChangePassword() {
        return "change_password";
    }

    @RequestMapping(value = "/user/ChangePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam("newPassword") String newPassword,
                                 @RequestParam("newMatchingPassword") String newMatchingPassword,
                                 ModelMap model) {
        if (newPassword.equals(newMatchingPassword)) {
            userService.changePassword(newPassword);
        } else {
            model.addAttribute("msg", "The password is not matching!");
        }
        return "redirect:/user/ProductList";
    }

    @RequestMapping(value = "/admin/viewUsers", method = RequestMethod.GET)
    public String viewUsers(ModelMap model) {
        List<User> users = userService.viewUsers();
        model.addAttribute("userList", users);
        return "users";
    }

    @RequestMapping(value = "/admin/removeUser", method = RequestMethod.GET)
    public String removeUser(@RequestParam(value = "username") String username) {
        userService.removeAccount(username);
        return "redirect:/admin/viewUsers";
    }

    @RequestMapping(value = "/admin/changeAuthority", method = RequestMethod.GET)
    public String makeAdmin(@RequestParam(value = "username") String username,
                            @RequestParam(value = "type") String type) {
        userService.changeAuthority(username, type);
        return "redirect:/admin/viewUsers";
    }
}
