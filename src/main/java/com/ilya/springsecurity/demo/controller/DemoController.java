package com.ilya.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
	
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
	
	//add for /leaders
	@RequestMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}
	
	//add for /admins
		@RequestMapping("/admins")
		public String showAdmins() {
			return "admins";
		}
}
