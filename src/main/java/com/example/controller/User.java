package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class User {
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	

}
