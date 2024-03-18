package com.example.Orderservice.Service;

import com.example.Orderservice.Dto.EmailDTO;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import org.springframework.http.*;
import java.util.Collections;

@Service
public class EmailSender implements EmailServiceInterface {

    private final RestTemplate restTemplate;

    public EmailSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmail(EmailDTO emailDTO) throws MessagingException, IOException {
        String notificationServiceUrl = "http://localhost:8084/notification/sendEmail";

        // Prepare HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Prepare request entity
        HttpEntity<EmailDTO> requestEntity = new HttpEntity<>(emailDTO, headers);

        // Make POST request to Notification service
        ResponseEntity<String> response = restTemplate.exchange(
                notificationServiceUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Check response status
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Email sent successfully.");
        } else {
            System.err.println("Failed to send email. Status code: " + response.getStatusCode());

        }
    }
}