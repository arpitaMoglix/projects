package com.example.notification;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

@SpringBootApplication
public class NotificationApplication /*implements CommandLineRunner*/ {

//	@Autowired
//	private EmailSender emailSender;

	public static void main(String[] args)
	{
		SpringApplication.run(NotificationApplication.class, args);

	}
//
//	@Override
//	public void run(String... args) throws Exception {
//
//
//		try {
//			emailSender.sendEmail("karpita56@gmail.com", "Test Subject", "This is a test email content.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
