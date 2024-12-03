package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table
public class UserDTO {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "Name is required")
	    private String fullName;

	    @Email(message = "Email is not valid")
	    @NotBlank(message = "Email is required")
	    private String email;

	    @NotBlank(message = "Phone is required")
	    private String phone;
	   
	    @NotBlank(message = "Password is required")
	    private String password;

	    
	    
	    @NotBlank(message = "Role is required")
	    private String role;

	    
	    // seller geting list of product
	    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
	   
		private List<Product> products = new ArrayList<>();
	    
}
