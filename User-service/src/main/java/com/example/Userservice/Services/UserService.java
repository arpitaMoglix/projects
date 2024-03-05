package com.example.Userservice.Services;
import com.example.Userservice.Dto.UserDTO;
import com.example.Userservice.Entity.User;
import org.springframework.beans.BeanUtils;
import com.example.Userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface{
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDTO createUser(UserDTO UserDTO) {
        User user = new User();
        BeanUtils.copyProperties(UserDTO, user); // Copy properties from DTO to entity
        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        User savedUser = userRepository.save(user);
        UserDTO savedUserDto = new UserDTO();
        BeanUtils.copyProperties(savedUser, savedUserDto); // Copy properties from entity to DTO
        return savedUserDto;
    }

    // Method to retrieve user by ID
   //@Transactional(readOnly = true)
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            UserDTO userDto = new UserDTO();
            BeanUtils.copyProperties(user, userDto); // Copy properties from entity to DTO
            return userDto;
        }
        return null;
    }

    // Method to update user
    @Transactional
    public UserDTO updateUser(UserDTO userDto) {
        User user = userRepository.findById(userDto.getId()).orElse(null);
        if (user != null) {

            Date originalCreatedAt = user.getCreatedAt();
            BeanUtils.copyProperties(userDto, user); // Copy properties from DTO to entity
            user.setCreatedAt(originalCreatedAt);
            user.setUpdatedAt(new Date());

            User updatedUser = userRepository.save(user);
            UserDTO updatedUserDto = new UserDTO();
            BeanUtils.copyProperties(updatedUser, updatedUserDto); // Copy properties from entity to DTO
            return updatedUserDto;
        }
        return null;
    }



    // Method to delete user by ID
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserDTO userDto = new UserDTO();
                    BeanUtils.copyProperties(user, userDto);
                    return userDto;
                })
                .collect(Collectors.toList());
    }

}
