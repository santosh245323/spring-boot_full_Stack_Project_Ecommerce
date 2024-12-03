package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Seller {

	@NotBlank(message = "Name is required")
	private String fullName;

	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Phone is required")
	private String phone;

	@NotBlank(message = "Password is required")
	private String password;

	
}
