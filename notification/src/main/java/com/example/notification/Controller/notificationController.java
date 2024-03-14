package com.example.notification.Controller;


import com.example.notification.service.EmailSender;
import com.example.notification.service.EmailServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/notification")
public class notificationController {

    @Autowired
    private EmailServiceInterface emailServiceInterface;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody Map<String, String> request){
        String email = request.get("email");
        // Call the sendEmail method to send an email
        String subject = "Test Email";
        String content = "<p>Hello,</p><p>This is a test email sent from Spring Boot.</p>";

        try {
            emailServiceInterface.sendEmail(email, subject, content);
            return "Email sent successfully.";
        } catch (Exception e) {
            return "Failed to send email. Error: " + e.getMessage();
        }
    }
}