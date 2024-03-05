package com.example.Userservice.Services;

import com.example.Userservice.Dto.UserDTO;

import java.util.List;

public interface UserServiceInterface {
    UserDTO createUser(UserDTO userDto);

    UserDTO getUserById(Long userId);

    UserDTO updateUser(UserDTO userDto);

    void deleteUser(Long userId);

    List<UserDTO> getAllUsers();
}
