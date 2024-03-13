package com.example.Cartservice.Service;

import com.example.Cartservice.Dto.CartDtoRequest;
import com.example.Cartservice.Dto.CartDtoResponse;
import com.example.Cartservice.Dto.OrderRequest;

import java.util.List;

public interface CartServiceInterface {
    CartDtoResponse addProductToCart(CartDtoRequest cartItemDtoRequest);
    List<CartDtoResponse> getCartItemsByUserId(Long userId);

    boolean deleteCart(Long cartId);

    CartDtoResponse updateCart(CartDtoRequest cartItemDtoRequest);

    Long placeOrder(OrderRequest orderRequest);

}
