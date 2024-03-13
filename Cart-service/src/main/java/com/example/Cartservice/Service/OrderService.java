package com.example.Cartservice.Service;

import com.example.Cartservice.Dto.OrderResponse;
import com.example.Cartservice.Dto.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService implements OrderServiceInterface{
    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OrderResponse orderInsert(OrderRequest orderRequest) {
        String orderUrl = "http://localhost:8082/order/create";
        ResponseEntity<OrderResponse> responseEntity = restTemplate.postForEntity(
                orderUrl,
                orderRequest,
                OrderResponse.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new IllegalStateException("Failed to place order");
        }

        return responseEntity.getBody();
    }



}
