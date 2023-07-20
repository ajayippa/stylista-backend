package com.stoned.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stoned.app.service.EmailService;


@RestController
@CrossOrigin(origins = "${frontend.url}", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api/email")
public class EmailController {

	@Autowired
	private EmailService service;
	
	
	@GetMapping("/verification/{email}")
	public ResponseEntity<String> sendingVerfication(@PathVariable String email)
	{
		return new ResponseEntity<String>(service.emailVerfication(email),HttpStatus.OK);
	}
	
	
	
	/*@Scheduled(cron = "0 * * * * *") // Run every 10 minutes (in milliseconds)
    public void cleanupExpiredOtp() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes!=null) {
            HttpServletRequest request = attributes.getRequest();
            System.out.println("otp");
            cleanupExpiredOtps(request);
        }
        
    }
	
	public void cleanupExpiredOtps(HttpServletRequest request) {
        HttpSession session = request.getSession(); // Custom method to retrieve the current session
        service.deleteOtp(session);
    }*/
	
}
