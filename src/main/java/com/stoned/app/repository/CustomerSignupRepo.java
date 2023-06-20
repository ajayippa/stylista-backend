package com.stoned.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.stoned.app.model.CustomerSignUp;

import jakarta.transaction.Transactional;


public interface CustomerSignupRepo extends JpaRepository<CustomerSignUp, Integer> {

	CustomerSignUp findByEmail(String email);

	CustomerSignUp findByMobileNumber(String phnumber);

	CustomerSignUp findByEmailOrMobileNumberOrUsername(String email, String mobileNumber, String username);

	@Modifying
	@Transactional
	@Query("update CustomerSignUp cu set cu.password=:newPassword where cu.email=:email or cu.mobileNumber=:email or cu.username=:email")
	void updatePassword(String email, String newPassword);

	
}
