package com.stoned.app.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.stoned.app.model.CustomerSignUp;


public class UserInfoUserDetails implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	//private List<GrantedAuthority> authorities;
	
	public UserInfoUserDetails(CustomerSignUp customerSignUp)
	{
		email=customerSignUp.getEmail();
		password=customerSignUp.getPassword();
		//authorities=Arrays.stream(customerSignUp.getRoles().split(","))
					//.map(SimpleGrantedAuthority::new)
					//.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
