package com.example.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String email;
    private String subject;
    private String content;
    private List<String> to;
    private List<String> cc;
    private List<String> attachmentUrls;
}
