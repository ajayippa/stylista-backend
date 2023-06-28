package com.stoned.app.serviceimpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.stoned.app.service.EmailService;




@Service
public class EmailServiceImplementation implements EmailService{

	@Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    
    
    public String emailVerfication(String email) {
    	
    	SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(email);
        mailMessage.setSubject("Email Verfication");
        Random random = new Random();
        int res=random.nextInt(9000) + 1000;
        String randomValue=String.valueOf(res);
        mailMessage.setText(randomValue);
        javaMailSender.send(mailMessage);
        return randomValue;
    }
}
