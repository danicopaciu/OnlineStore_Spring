package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {


	@RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login (@RequestParam(value = "error", required = false) String error,
						 ModelMap model){

		if(error != null){
			model.addAttribute("error", "Invalid username and password");
		}
		return "hello";
	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public String printAdmin(ModelMap model){
		return "admin";
	}
}