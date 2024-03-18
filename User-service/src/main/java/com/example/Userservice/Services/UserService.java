package com.example.Userservice.Services;
import com.example.Userservice.Dto.UserDTO;
import com.example.Userservice.Entity.User;
import org.springframework.beans.BeanUtils;
import com.example.Userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface{
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        logger.info("Creating new user from DTO: {}", userDTO);

        User user = new User();
        BeanUtils.copyProperties(userDTO, user); // Copy properties from DTO to entity
        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        User savedUser = userRepository.save(user);
        UserDTO savedUserDto = new UserDTO();
        BeanUtils.copyProperties(savedUser, savedUserDto); // Copy properties from entity to DTO
        logger.info("User created successfully: {}", savedUserDto);
        return savedUserDto;
    }

    // Method to retrieve user by ID
   //@Transactional(readOnly = true)
    public UserDTO getUserById(Long userId) {
        logger.info("Fetching user with ID: {}", userId);

        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            UserDTO userDto = new UserDTO();
            BeanUtils.copyProperties(user, userDto); // Copy properties from entity to DTO
            logger.info("User found with ID {}: {}", userId, userDto);
            return userDto;
        }
        logger.warn("User not found with ID: {}", userId);
        return null;
    }

    // Method to update user
    @Transactional
    public UserDTO updateUser(UserDTO userDto) {
        logger.info("Updating user with ID: {}", userDto.getId());

        User user = userRepository.findById(userDto.getId()).orElse(null);
        if (user != null) {

            Date originalCreatedAt = user.getCreatedAt();
            BeanUtils.copyProperties(userDto, user); // Copy properties from DTO to entity
            user.setCreatedAt(originalCreatedAt);
            user.setUpdatedAt(new Date());

            User updatedUser = userRepository.save(user);
            UserDTO updatedUserDto = new UserDTO();
            BeanUtils.copyProperties(updatedUser, updatedUserDto); // Copy properties from entity to DTO
            logger.info("User updated successfully: {}", updatedUserDto);
            return updatedUserDto;
        }
        logger.warn("User not found with ID: {}", userDto.getId());
        return null;
    }

    // Method to delete user by ID
    @Transactional
    public void deleteUser(Long userId) {

        logger.info("Deleting user with ID: {}", userId);

        userRepository.deleteById(userId);

        logger.info("User deleted successfully: {}", userId);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        logger.info("Fetching all users");

        List<User> users = userRepository.findAll();

        List<UserDTO> userDTOs = users.stream()
                .map(user -> {
                    UserDTO userDto = new UserDTO();
                    BeanUtils.copyProperties(user, userDto);
                    return userDto;
                })
                .collect(Collectors.toList());

        logger.info("Found {} users", userDTOs.size());

        return userDTOs;
    }

}
