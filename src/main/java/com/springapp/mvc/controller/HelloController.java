package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login (@RequestParam(value = "error", required = false) String error,
						 @RequestParam(value = "logout", required = false) String logout,
						 ModelMap model){

		if(error != null){
			model.addAttribute("error", "Invalid username or password");
		}

		if(logout != null){
			model.addAttribute("msg", "You have been successfully logged out!");
		}

		return "hello";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String printAdmin (){
		return "admin";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String printUser (){
		return "user";
	}
}