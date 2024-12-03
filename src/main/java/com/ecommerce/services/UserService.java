package com.ecommerce.services;

import com.ecommerce.model.Seller;
import com.ecommerce.model.User;
import com.ecommerce.model.UserDTO;

public interface UserService {

	public UserDTO convertUserToDTO(User user);

	public UserDTO convertSellerToDTO(Seller seller);
	
	

	public UserDTO createAdmin(String fullName, String email, String phone, String password);
	
	public void saveUser(UserDTO user);
	public void saveSeller(UserDTO seller);
	
	
	public UserDTO findByEmail(String email) ;
	
	

	
	boolean isEmailAndRoleTaken(String email, String role);
	boolean isPhoneAndRoleTaken(String phone, String role);
	

}
