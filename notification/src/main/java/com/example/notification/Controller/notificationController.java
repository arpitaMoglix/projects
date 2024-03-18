package com.example.notification.Controller;


import com.example.notification.dto.EmailDTO;
//import com.example.notification.kafka.EmailProducer;
import com.example.notification.service.EmailSender;
import com.example.notification.service.EmailServiceInterface;
import jakarta.mail.MessagingException;
import com.example.notification.service.EmailServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/notification")
public class notificationController {

    @Autowired
    private EmailServiceInterface emailService;

//    @Autowired
//    private EmailProducer emailProducer;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailDTO emailDTO) {
        try {
            emailService.sendEmail(emailDTO);
            return "Email sent successfully.";
        } catch (MessagingException | IOException e) {
            return "Failed to send email. Error: " + e.getMessage();
        }
    }

//    @PostMapping("/sendEmail")
//    public String sendEmail(@RequestBody EmailDTO emailDTO) {
//        try {
//            emailProducer.sendEmail(emailDTO);
//            return "Email request sent successfully.";
//        } catch (Exception e) {
//            return "Failed to send email request. Error: " + e.getMessage();
//        }
//    }

}