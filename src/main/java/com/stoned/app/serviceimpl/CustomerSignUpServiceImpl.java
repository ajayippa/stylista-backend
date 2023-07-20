package com.stoned.app.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stoned.app.dto.CustomerData;
import com.stoned.app.dto.SignInData;
import com.stoned.app.dto.UpdatePassword;
import com.stoned.app.exception.*;
import com.stoned.app.model.CustomerSignUp;
import com.stoned.app.repository.CustomerSignupRepo;
import com.stoned.app.service.SignUpService;

@Service
public class CustomerSignUpServiceImpl  implements SignUpService{
	
	 @Autowired
	 private CustomerSignupRepo cRepo;
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;

	@Override
	public String saveDetails(CustomerData signup) {
		// TODO Auto-generated method stub
		String pass=signup.getPassword();
		String cPass=signup.getConformpassword();
		String mobile=signup.getMobileNumber();
		if(mobile.length()>10||mobile.length()<10)
		{
			throw new MobileNumberException("Mobile Number Should contains 10 numbers");
		}
		else if(!pass.equals(cPass))
		{
			throw new PasswordMisMatchException("Password And Conform Password  mismatched");

		}
		else {
			CustomerSignUp data1=cRepo.findByEmailOrMobileNumberOrUsername(
										signup.getEmail(),signup.getMobileNumber(),signup.getUsername());
			if(data1!=null)
			{
				throw new UserAlreadyExistException("User Already Exist");
			}
			else {
				CustomerSignUp data=CustomerSignUp.build(0,signup.getFirstname(),
						signup.getLastname(), signup.getUsername(), signup.getDateOfBirth(), signup.getGender(),
							signup.getEmail(), signup.getMobileNumber(), passwordEncoder.encode(signup.getPassword()),null);
		
				cRepo.save(data);
			}
		}
		
		return "success";
	}

	@Override
	public List<CustomerSignUp> getDetails() {
		// TODO Auto-generated method stub
		return  cRepo.findAll();
	}

	@Override
	public String loginValidation(SignInData signInData) {
		String email=signInData.getEmail();
		String password=signInData.getPassword();
		CustomerSignUp data=null;
		if(email.endsWith("@gmail.com"))
		{
			data=cRepo.findByEmail(email);
		}
		else
		{
			data=cRepo.findByMobileNumber(email);
		}
		if(data != null)
		{
			String pass=data.getPassword();
			if(pass.equals(password))
			{
				return "Success";
			}
			else
			{
				throw new PasswordMisMatchException("Email/Password mismatched");
			}
		}
		else
		{
			throw new PasswordMisMatchException(email+" not Found");
		}
	}

	@Override
	public String userPresentOrNot(String email) {
		CustomerSignUp data=cRepo.findByEmail(email);
		if(data!=null)
		{
			return "success";
		}
		return "User Not Found";
	}

	@Override
	public String updatePassword(String email, UpdatePassword updatePassword) {
		if(!updatePassword.getNewPassword().equals(updatePassword.getConformPassword()))
		{
			throw new PasswordMisMatchException("Password and Conform Password mismatched");
		}
		else
		{
			cRepo.updatePassword(email,updatePassword.getNewPassword());
			return "success";
		}
	}
	
	
	
}