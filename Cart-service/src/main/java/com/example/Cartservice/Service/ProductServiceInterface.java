package com.example.Cartservice.Service;

import com.example.Cartservice.Dto.ProductDtoResponse;

public interface ProductServiceInterface {
    ProductDtoResponse getProductDetails(Long productId);
}
