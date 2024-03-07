package com.example.Orderservice.Service;

import com.example.Orderservice.Dto.UserDtoResponse;

public interface UserServiceInterface {
    UserDtoResponse getUserDetails(Long userId);
}
