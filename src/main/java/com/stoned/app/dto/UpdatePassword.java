package com.stoned.app.dto;

import org.springframework.stereotype.Controller;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Controller
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassword {
	
	
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" ,
			message="password should contains at least 0-9,a-z,A-Z,! @ # $ % & * ")
	private String newPassword;
	
	private String conformPassword;


}
