package com.example.Orderservice.Service;

import com.example.Orderservice.Dto.EmailDTO;
import jakarta.mail.MessagingException; // Import MessagingException class
import java.io.IOException;
import java.io.IOException;

public interface EmailServiceInterface {
    void sendEmail(EmailDTO emailDTO) throws MessagingException, IOException;
}
