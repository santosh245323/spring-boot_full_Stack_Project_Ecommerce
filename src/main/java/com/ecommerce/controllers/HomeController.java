package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ecommerce.model.Seller;
import com.ecommerce.model.User;
import com.ecommerce.model.UserDTO;
import com.ecommerce.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	// this is unknown method for testing
	@GetMapping("/test")
	public String getTest() {
		return "test";
	}

	// ----------------------main logic ----------------------------

	@GetMapping({ "/", "/home" })
	public String getHomePage() {

		return "index";
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {

		if (error != null) {
			model.addAttribute("message", "Invalid username or password");
		}
		return "LoginForm";
	}

	
	// for seller
	@GetMapping("/check-seller")
	@ResponseBody
	public boolean checkSellerEmailAndPhone(@RequestParam(required = false) String email,
			@RequestParam(required = false) String phone, @RequestParam String role) {

		if ("SELLER".equalsIgnoreCase(role)) {
			return (email != null && userService.isEmailAndRoleTaken(email, role))
					|| (phone != null && userService.isPhoneAndRoleTaken(phone, role));
		}

		return false;
	}

	// for user
	@GetMapping("/check-user")
	@ResponseBody
	public boolean getEmailAndPhoneForUser(@RequestParam(required = false) String email,
			@RequestParam(required = false) String phone, @RequestParam String role) {

		if ("USER".equalsIgnoreCase(role)) {
			return (email != null && userService.isEmailAndRoleTaken(email, role))
					|| (phone != null && userService.isPhoneAndRoleTaken(phone, role));
		}

		return false;
	}
	
	
		

	
	
	@GetMapping("/registration")
	public String getUserRegistrationForm() {

		return "RegistrationForm";
	}

	@PostMapping("/registration")
	public String postRegistrationUser(@Valid @ModelAttribute User user) {

		UserDTO userDTO = userService.convertUserToDTO(user);

		if (userDTO != null) {

			userService.saveUser(userDTO);

		}

		return "RegistrationForm";
	}

	@GetMapping("/became-a-seller")
	public String getBecameASeller(Model model) {

		return "became-a-seller";
	}

	@GetMapping("/seller-registration")
	public String getRegistrationForm(Model model) {
		return "Seller-Registration-Form";
	}

	@PostMapping("/seller-registration")
	public String postSeller(@ModelAttribute Seller seller) {

		UserDTO convertSellerToDTO = userService.convertSellerToDTO(seller);

		userService.saveSeller(convertSellerToDTO);

		return "Seller-Registration-Form";
	}

}
