package com.vsan.myprofileapp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vsan.myprofileapp.dao.User;
import com.vsan.myprofileapp.repository.AppRepository;

@Service
public class UserSecurityService implements UserDetailsService {
	
	@Autowired
	private AppRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userLoaded = null;
		Optional<User> user = repository.findByEmail(email);
        if (user.isPresent()) {
        	userLoaded = repository.getByEmail(email);
            return userLoaded;
        } else {
        	throw new UsernameNotFoundException(email);
        }
        
	}

}
