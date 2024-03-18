//package com.example.notification.kafka;
//
//import com.example.notification.dto.EmailDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EmailProducer {
//
//    @Autowired
//    private KafkaTemplate<String, EmailDTO> kafkaTemplate;
//
//    private static final String TOPIC = "email_topic";
//
//    public void sendEmail(EmailDTO emailDTO) {
//        kafkaTemplate.send(TOPIC, emailDTO);
//    }
//}