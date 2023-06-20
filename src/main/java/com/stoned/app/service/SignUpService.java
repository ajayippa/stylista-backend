package com.stoned.app.service;

import java.util.List;


import com.stoned.app.dto.CustomerData;
import com.stoned.app.dto.SignInData;
import com.stoned.app.dto.UpdatePassword;
import com.stoned.app.model.CustomerSignUp;


public interface SignUpService {
    
	List<CustomerSignUp> getDetails();    //For getting Customer Details


	String saveDetails(CustomerData signup);    //For registering as Customer
    

	String loginValidation(SignInData signInData);    //Customer Login Validation


	String userPresentOrNot(String email);         // For Check Given user present or Not


	String updatePassword(String email, UpdatePassword updatePassword);    //updating the password
}