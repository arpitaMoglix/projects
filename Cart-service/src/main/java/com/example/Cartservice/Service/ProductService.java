package com.example.Cartservice.Service;

import com.example.Cartservice.Dto.ProductDtoResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService implements ProductServiceInterface{
    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductDtoResponse getProductDetails(Long productId) {
        ResponseEntity<ProductDtoResponse> responseEntity = restTemplate.exchange(
                "http://localhost:8080/product/getDetails/" + productId,
                HttpMethod.GET,
                null,
                ProductDtoResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new IllegalArgumentException("Product not found");
        }

        return responseEntity.getBody();
    }
}
