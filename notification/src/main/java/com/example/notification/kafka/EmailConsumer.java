//package com.example.notification.kafka;
//
//import com.example.notification.dto.EmailDTO;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EmailConsumer {
//
//    @KafkaListener(topics = "email_topic", groupId = "email_group")
//    public void sendEmail(EmailDTO emailDTO) {
//        try {
//            // Call existing email sending logic here
//            // emailService.sendEmail(emailDTO);
//            System.out.println("Email sent successfully to: " + emailDTO.getTo());
//        } catch (Exception e) {
//            System.out.println("Failed to send email. Error: " + e.getMessage());
//        }
//    }
//}