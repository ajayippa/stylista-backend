package com.stoned.app.dto;

import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Controller
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInData {
	
	private String email;
	private String password;


}