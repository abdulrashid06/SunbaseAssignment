package com.sunbase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sunbase.exception.CustomerException;
import com.sunbase.model.Customer;
import com.sunbase.model.User;
import com.sunbase.repository.CustomerRepository;
import com.sunbase.repository.UserRepository;

@Service
public class ManualUserDetailService implements UserDetailsService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Optional<User> opt = userRepository.findByEmail(username);
	    if(opt.isEmpty()) throw new CustomerException("User not found");

	    User user = opt.get();
	    List<GrantedAuthority> grantedAuthority = new ArrayList<>();
	    grantedAuthority.add(new SimpleGrantedAuthority("ROLE_USER")); // or set the role dynamically

	    return new org.springframework.security.core.userdetails.User(
	            user.getEmail(),
	            user.getPassword(),
	            grantedAuthority
	    );
	}

}
