package com.example.Cartservice.Controller;

import com.example.Cartservice.Dto.CartDtoResponse;
import com.example.Cartservice.Dto.CartDtoRequest;
import com.example.Cartservice.Dto.OrderRequest;
import com.example.Cartservice.Service.CartService;
import com.example.Cartservice.Service.CartServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceInterface cartService;

    @PostMapping("/addItems")
    public ResponseEntity<CartDtoResponse> addProductToCart(@RequestBody CartDtoRequest cartDtoRequest) {
        CartDtoResponse cartItemDtoResponse = cartService.addProductToCart(cartDtoRequest);
        return ResponseEntity.ok(cartItemDtoResponse);
    }

    @PutMapping("/updateItems")
    public ResponseEntity<CartDtoResponse> updateCart(@RequestBody CartDtoRequest cartItemDtoRequest) {
        CartDtoResponse updatedCart = cartService.updateCart(cartItemDtoRequest);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping("/{userId}/cartItems")
    public ResponseEntity<List<CartDtoResponse>> getCartItemsByUserId(@PathVariable Long userId) {
        List<CartDtoResponse> cartItems = cartService.getCartItemsByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        boolean deleted = cartService.deleteCart(cartId);
        if (deleted) {
            return ResponseEntity.ok("Cart with ID " + cartId + " deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Cart with ID " + cartId + " not found.");
        }
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            Long orderId = cartService.placeOrder(orderRequest);
            return ResponseEntity.ok(orderId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}