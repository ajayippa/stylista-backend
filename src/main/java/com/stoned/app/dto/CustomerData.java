package com.stoned.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class CustomerData {
	

	
    @NotBlank(message = "firstname should not be empty")
	private String firstname;
    @NotBlank(message = "lastname should not be empty")
	private String lastname;
    @NotBlank(message = "UserName should not be empty")
	private String username;
    @NotBlank(message = "date of birth should not be empty")
	private String date;
    @NotBlank(message = "gender should not be empty")
	private String gender;
    @Email(message = "email should not be empty")
	private String email;
    @NotBlank(message = "phone number should not be empty")
	private String mobileNumber;
    @NotBlank(message = "password should not be empty")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" ,
	message="password should contains at least 0-9,a-z,A-Z,! @ # $ % & * ")
	private String password;
    private String conformpassword;

}