package com.stoned.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.stoned.app.model.CustomerSignUp;
import com.stoned.app.repository.CustomerSignupRepo;


@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	
	@Autowired
	private CustomerSignupRepo repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		CustomerSignUp userInfo=repository.findByEmail(username);
		
		//return CustomerSignUp.map(UserInfoUserDetails::new)
				//	.orElseThrow(()->new UsernameNotFoundException("user not found"+username));
		if (userInfo == null) {
		    throw new UsernameNotFoundException("User not found: " + username);
		}
		return new UserInfoUserDetails(userInfo);
		
	}

}
