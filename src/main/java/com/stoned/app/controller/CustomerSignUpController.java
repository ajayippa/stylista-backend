package com.stoned.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stoned.app.dto.CustomerData;
import com.stoned.app.dto.SignInData;
import com.stoned.app.dto.UpdatePassword;
import com.stoned.app.model.CustomerSignUp;
import com.stoned.app.service.SignUpService;
import com.stoned.app.serviceimpl.JwtService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "${frontend.url}")
@RequestMapping("/sign")
public class CustomerSignUpController {
	@Autowired
	private SignUpService service;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/post")
	public ResponseEntity<String> saveDetails(@RequestBody @Valid CustomerData signup)
				
	{
		return new ResponseEntity<String>(service.saveDetails(signup),HttpStatus.OK);
	}
	 
	// Read operation
	@GetMapping("/get")
	public List<CustomerSignUp> getDetails()
	{
		return service.getDetails();
	}
	    
	@PostMapping("/login")
	public ResponseEntity<String> customerLogin(@RequestBody SignInData signInData)
			
	{
		return new ResponseEntity<String>(service.loginValidation(signInData),HttpStatus.OK);
	}
	
	@GetMapping("/userStatus/{email}")
	public ResponseEntity<String> userPresentOrNot(@PathVariable String email)
	{
		return new ResponseEntity<String>(service.userPresentOrNot(email),HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword/{email}")
	public ResponseEntity<String> updatePassword(@PathVariable String email , 
										@RequestBody @Valid UpdatePassword updatePassword)
	{
		return new ResponseEntity<String>(service.updatePassword(email,updatePassword),HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody SignInData signInData)
	{
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInData.getEmail(), signInData.getPassword()));
		if(authentication.isAuthenticated())
		{
			return jwtService.generateToken(signInData.getEmail());
		}
		else {
			throw new UsernameNotFoundException("invalid user request !");
		}
		
	}

}
