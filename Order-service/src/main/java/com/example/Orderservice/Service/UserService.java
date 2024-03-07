package com.example.Orderservice.Service;

import com.example.Orderservice.Dto.UserDtoResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements UserServiceInterface{
    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    public UserDtoResponse getUserDetails(Long userId){
        ResponseEntity<UserDtoResponse> responseEntity = restTemplate.exchange(
                "http://localhost:8081/user/GetUser/" + userId,
                HttpMethod.GET,
                null,
                UserDtoResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new IllegalArgumentException("Product not found");
        }

        return responseEntity.getBody();
    }
}
