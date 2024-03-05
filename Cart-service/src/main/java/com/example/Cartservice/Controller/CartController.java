package com.example.Cartservice.Controller;

import com.example.Cartservice.Dto.CartDtoResponse;
import com.example.Cartservice.Dto.CartDtoRequest;
import com.example.Cartservice.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<CartDtoResponse> addProductToCart(@RequestBody CartDtoRequest cartDtoRequest) {
        CartDtoResponse cartItemDtoResponse = cartService.addProductToCart(cartDtoRequest);
        return ResponseEntity.ok(cartItemDtoResponse);
    }

}