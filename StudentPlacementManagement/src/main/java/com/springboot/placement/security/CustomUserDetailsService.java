package com.springboot.placement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.placement.constants.PlacementConstants;
import com.springboot.placement.entity.User;
import com.springboot.placement.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	   @Autowired
	    private UserRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	        User user = userRepository.findByUserName(username)
	                .orElseThrow(() ->
	                    new UsernameNotFoundException(
	                       PlacementConstants.USER_NOT_FOUND + username
	                    )
	                );

	        return org.springframework.security.core.userdetails.User
	                .withUsername(user.getUserName())
	                .password(user.getPassword())
	                .roles(user.getRole().name())
	                .build();
	    }
	}
