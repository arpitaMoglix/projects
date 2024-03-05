package com.example.Userservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long Id;
    private String name;
    private String email;
    private String phoneNumber;
    private String location;
    private Date createdAt;
    private Date updatedAt;
}
