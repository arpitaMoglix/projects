package com.example.Cartservice.Service;

import com.example.Cartservice.Dto.ProductDtoResponse;
import com.example.Cartservice.Dto.UserDtoResponse;

public interface UserServiceInterface {
    UserDtoResponse getUserDetails(Long userId);
}
