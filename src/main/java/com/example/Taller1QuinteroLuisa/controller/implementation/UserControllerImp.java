package com.example.Taller1QuinteroLuisa.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Taller1QuinteroLuisa.services.UserServiceImp;

@Controller
public class UserControllerImp {
	
	UserServiceImp userService;
	
	@Autowired
	public UserControllerImp(UserServiceImp userService) {
		this.userService= userService;
	}
	
	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@GetMapping("/index")
	public String index() {
		return "/index";
	}

	@GetMapping("/login")
	public String loginPrincipal() {
		return "/login";
	}

}
