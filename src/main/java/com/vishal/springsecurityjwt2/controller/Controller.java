package com.vishal.springsecurityjwt2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class 	Controller {

	@GetMapping("/home")
	public String gethome() {
		return "this is  my home";
	}
	
	@GetMapping("/user")
	public String getUser() {
		return "this is vishal";
	}
	
	
	
	
}
	