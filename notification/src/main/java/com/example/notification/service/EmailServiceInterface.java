package com.example.notification.service;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailServiceInterface {
    void sendEmail(String email, String subject, String content)throws MessagingException, UnsupportedEncodingException ;
}
