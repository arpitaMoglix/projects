package com.example.notification.service;

import com.example.notification.dto.*;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailServiceInterface {
    void sendEmail(EmailDTO emailDTO) throws MessagingException, IOException;
}
