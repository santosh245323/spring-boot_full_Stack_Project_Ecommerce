package com.ecommerce.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.model.Seller;
import com.ecommerce.model.User;
import com.ecommerce.model.UserDTO;
import com.ecommerce.repositories.UserDTORepository;
import com.ecommerce.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDTORepository userDTORepo;

    @Autowired
    private PasswordEncoder passwordEncoder; // Password encoder for encrypting passwords

    @Override
    public UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setPassword(user.getPassword()); // Password will be encrypted later
        userDTO.setRole("USER");
        return userDTO;
    }

    @Override
    public UserDTO convertSellerToDTO(Seller seller) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(seller.getFullName());
        userDTO.setEmail(seller.getEmail());
        userDTO.setPhone(seller.getPhone());
        userDTO.setPassword(seller.getPassword()); // Password will be encrypted later
        userDTO.setRole("SELLER");
        return userDTO;
    }

    @Override
    public UserDTO createAdmin(String fullName, String email, String phone, String password) {
        UserDTO admin = new UserDTO();
        admin.setFullName(fullName);
        admin.setEmail(email);
        admin.setPhone(phone);
        admin.setPassword(passwordEncoder.encode(password)); // Encrypting password before saving
        admin.setRole("ADMIN");
        return userDTORepo.save(admin);
    }

    @Override
    public void saveUser(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDTORepo.save(user);
    }

    @Override
    public void saveSeller(UserDTO seller) {
      
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        userDTORepo.save(seller);
    }

 
    

	@Override
	public boolean isEmailAndRoleTaken(String email, String role) {
		
		return userDTORepo.findByEmailAndRole(email, role).isPresent();
	}

	@Override
	public boolean isPhoneAndRoleTaken(String phone, String role) {
		
		return userDTORepo.findByPhoneAndRole(phone, role).isPresent();
	}

	@Override
	public UserDTO findByEmail(String email) {
		
		return userDTORepo.findByEmail(email);
	}

	


    

    
}
