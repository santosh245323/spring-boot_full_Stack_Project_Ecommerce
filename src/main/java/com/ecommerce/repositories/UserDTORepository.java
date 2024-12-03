package com.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.UserDTO;

public interface UserDTORepository extends JpaRepository<UserDTO, Long> {

	Optional<UserDTO> findByEmailAndRole(String email, String role);

	Optional<UserDTO> findByPhoneAndRole(String phone, String role);

	// active user 
	 UserDTO findByEmail(String email);
	
//	for login
	
	UserDTO findByEmailOrPhone(String email, String phone);

	

}
