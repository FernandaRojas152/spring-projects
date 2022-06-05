package com.example.Taller1QuinteroLuisa.frontend.controller.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Taller1QuinteroLuisa.backend.model.person.UserApp;
import com.example.Taller1QuinteroLuisa.backend.services.UserServiceImp;
import com.example.Taller1QuinteroLuisa.backend.validation.CompleteInfoValidation;
import com.example.Taller1QuinteroLuisa.backend.validation.CredentialInfoValidation;
import com.example.Taller1QuinteroLuisa.frontend.controller.interfaces.UserController;

@Controller
public class UserControllerImp implements UserController{
	
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

	@GetMapping("/login")
	public String loginPrincipal() {
		return "/login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "/login";
	}
	
	@GetMapping("/users/add")
	public String addUser(Model model) {
		model.addAttribute("user", new UserApp());
		model.addAttribute("types", userService.getTypes());
		return "users/add-user";
	}
	
	@PostMapping("/users/add")
	public String saveUser(@Validated(CredentialInfoValidation.class) @ModelAttribute("user") UserApp user, BindingResult bindingResult, Model model, 
			@RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("types", userService.getTypes());
				return "users/add-user";
			}
			userService.save(user);
		}
		return "redirect:/users/";
	}
	
	@PostMapping("/users/edit/{id}")
	public String updateUser(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			@Validated(CompleteInfoValidation.class) @ModelAttribute("user") UserApp user,
			BindingResult bindingResult, Model model) {

		if (action.equals("Cancel")) {
			return "redirect:/users/";
		} else {
			if (bindingResult.hasErrors()) {
				model.addAttribute("types", userService.getTypes());
				return "users/update-user";
			}
			String pass = user.getPassword();
			String re = user.getRepeatPassword();
			if((pass.length()>=8 && pass.equals(re))) {
				userService.update(user,id);
			}else {
				user.setPassword(userService.findById(id).get().getPassword());
				userService.update(user,id);
			}
			model.addAttribute("users", userService.findAll());
			return "redirect:/users/";
		}
	}
	
	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<UserApp> user = userService.findById(id);
		if (user.isEmpty())
			throw new IllegalArgumentException("Invalid id:" + id);
		model.addAttribute("user", user.get());
		model.addAttribute("types", userService.getTypes());
		return "users/update-user";
	}


}
