package com.ecommerce.security;

import com.ecommerce.model.UserDTO;
import com.ecommerce.repositories.UserDTORepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDTORepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find user by email or phone
        UserDTO user = userRepository.findByEmailOrPhone(username, username);

        if (user == null) {
        	
            throw new UsernameNotFoundException("User not found with email or phone: " + username);
        }

        return new CustomUserDetails(user);
    }
}
