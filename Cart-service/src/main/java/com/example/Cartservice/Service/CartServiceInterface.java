package com.example.Cartservice.Service;

import com.example.Cartservice.Dto.CartDtoRequest;
import com.example.Cartservice.Dto.CartDtoResponse;

public interface CartServiceInterface {
    CartDtoResponse addProductToCart(CartDtoRequest cartItemDtoRequest);
}
