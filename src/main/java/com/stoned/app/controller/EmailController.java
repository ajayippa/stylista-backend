package com.stoned.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stoned.app.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

	@Autowired
	private EmailService service;
	
	@GetMapping("/verification/{email}")
	public ResponseEntity<String> sendingVerfication(@PathVariable String email)
	{
		return new ResponseEntity<String>(service.emailVerfication(email),HttpStatus.OK);
	}
}
