package com.example.notification.service;


import com.example.notification.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;

@Service
public class EmailSender  implements EmailServiceInterface{
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private RestTemplate restTemplate;

    public EmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(EmailDTO emailDTO) throws MessagingException, IOException {
        // Validate required fields
        validateEmailDTO(emailDTO);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set recipients
        helper.setTo(emailDTO.getTo().toArray(new String[0]));
        if (emailDTO.getCc() != null && !emailDTO.getCc().isEmpty()) {
            helper.setCc(emailDTO.getCc().toArray(new String[0]));
        }

        // Set subject and content
        helper.setSubject(emailDTO.getSubject());
        helper.setText(getEmailContent(emailDTO), true); // Set HTML content

        // Attach files from URLs, if any
        if (emailDTO.getAttachmentUrls() != null) {
            for (String attachmentUrl : emailDTO.getAttachmentUrls()) {
                byte[] attachmentContent = restTemplate.getForObject(attachmentUrl, byte[].class);
                String filename = attachmentUrl.substring(attachmentUrl.lastIndexOf('/') + 1);
                helper.addAttachment(filename, new ByteArrayResource(attachmentContent));
            }
        }

        // Send email
        emailSender.send(message);
        System.out.println("Email sent successfully to: " + emailDTO.getTo());
    }

    private void validateEmailDTO(EmailDTO emailDTO) {
        if (emailDTO.getTo() == null || emailDTO.getTo().isEmpty()) {
            throw new IllegalArgumentException("Recipient email address is required.");
        }
        if (emailDTO.getSubject() == null || emailDTO.getSubject().isEmpty()) {
            throw new IllegalArgumentException("Email subject is required.");
        }
        if (emailDTO.getContent() == null || emailDTO.getContent().isEmpty()) {
            throw new IllegalArgumentException("Email content is required.");
        }
    }

    private String getEmailContent(EmailDTO emailDTO) {
        StringBuilder content = new StringBuilder();
        content.append("<p>").append(emailDTO.getContent()).append("</p>");
        if (emailDTO.getAttachmentUrls() != null && !emailDTO.getAttachmentUrls().isEmpty()) {
            content.append("<p>Find below attachment:</p>");
            for (String attachmentUrl : emailDTO.getAttachmentUrls()) {
                String filename = attachmentUrl.substring(attachmentUrl.lastIndexOf('/') + 1);
                content.append("<p><a href='").append(attachmentUrl).append("'>").append(filename).append("</a></p>");
            }
        }
        return content.toString();
    }
}
