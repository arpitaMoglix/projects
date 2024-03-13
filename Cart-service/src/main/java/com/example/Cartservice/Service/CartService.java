package com.example.Cartservice.Service;

import com.example.Cartservice.Dto.*;
import com.example.Cartservice.Entity.Cart;
import com.example.Cartservice.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService implements CartServiceInterface{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductServiceInterface productService;

    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderServiceInterface orderService;


    public CartDtoResponse addProductToCart(CartDtoRequest cartItemDtoRequest) {

        if (cartItemDtoRequest == null) {
            throw new IllegalArgumentException("Cart item request cannot be null");
        }

        if (cartItemDtoRequest.getProductId() == null || cartItemDtoRequest.getProductQuantityInCart() == null || cartItemDtoRequest.getProductQuantityInCart() <= 0) {
            throw new IllegalArgumentException("Invalid cart item data");
        }

        if (cartItemDtoRequest.getUserId() == null) {
            throw new IllegalArgumentException("Invalid cart item data");
        }


        UserDtoResponse userDtoResponse = userService.getUserDetails(cartItemDtoRequest.getUserId());
        ProductDtoResponse productDtoResponse = productService.getProductDetails(cartItemDtoRequest.getProductId());

        if (productDtoResponse.getQuantity() >=cartItemDtoRequest.getProductQuantityInCart()) {
            Cart cart = new Cart();

            cart.setUserId(userDtoResponse.getId());

            cart.setProductId(productDtoResponse.getProductId());
            cart.setProductQuantityInCart(cartItemDtoRequest.getProductQuantityInCart());
            cart.setCreatedAt(new Date());
            cart.setUpdatedAt(new Date());
            cart.setOrdered(false);
            cart.setRemoved(false);


            Cart savedCart = cartRepository.save(cart);

            return mapToDto(savedCart);
        }else {

            throw new IllegalArgumentException("Insufficient quantity for the product");
        }
    }

    @Override
    public CartDtoResponse updateCart(CartDtoRequest cartItemDtoRequest){
        // Validate input DTO
        if (cartItemDtoRequest == null) {
            throw new IllegalArgumentException("Cart item request cannot be null");
        }

        if (cartItemDtoRequest.getProductId() == null || cartItemDtoRequest.getProductQuantityInCart() == null || cartItemDtoRequest.getProductQuantityInCart() <= 0) {
            throw new IllegalArgumentException("Invalid cart item data");
        }

        if (cartItemDtoRequest.getUserId() == null) {
            throw new IllegalArgumentException("Invalid cart item data");
        }

        // Fetch product details
        UserDtoResponse userDtoResponse = userService.getUserDetails(cartItemDtoRequest.getUserId());
        ProductDtoResponse productDtoResponse = productService.getProductDetails(cartItemDtoRequest.getProductId());

        // Check if cart already exists for the user and product
        Optional<Cart> existingCartOptional = cartRepository.findByUserIdAndProductId(userDtoResponse.getId(), productDtoResponse.getProductId());
        if (existingCartOptional.isPresent()) {
            // Cart already exists, update the quantity
            Cart existingCart = existingCartOptional.get();
            existingCart.setProductQuantityInCart(existingCart.getProductQuantityInCart() + cartItemDtoRequest.getProductQuantityInCart());
            existingCart.setUpdatedAt(new Date()); // Update the cart's updated timestamp
            Cart savedCart = cartRepository.save(existingCart);
            return mapToDto(savedCart);
        }

        throw new IllegalArgumentException("Product is not in the cart");
    }

    public List<CartDtoResponse> getCartItemsByUserId(Long userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        return carts.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Long placeOrder(OrderRequest orderRequest ) {

        if (orderRequest == null || orderRequest.getUserId() == null) {
            throw new IllegalArgumentException("Invalid order request");
        }

        // Find carts by user ID
        UserDtoResponse userDtoResponse = userService.getUserDetails(orderRequest.getUserId());
        if (userDtoResponse == null) {
            throw new IllegalArgumentException("User not found");
        }

        List<Cart> carts = cartRepository.findCartsByUserIdAndNotOrderedAndNotRemoved(userDtoResponse.getId());
        if (carts.isEmpty()) {
            throw new IllegalStateException("No items in the cart for user: " + userDtoResponse.getId());
        }


        // Create an order request
        orderRequest.setUserId(userDtoResponse.getId());
        orderRequest.setStatus(10); // Set order status as required

        // Add order items to the order request
        for (Cart cart : carts) {
            // Fetch product details for each cart item
            ProductDtoResponse productDtoResponse = productService.getProductDetails(cart.getProductId());
            if (productDtoResponse == null) {
                throw new IllegalArgumentException("Product not found for ID: " + cart.getProductId());
            }

            // Create an order item DTO
            OrderItem orderItemDTO = new OrderItem();
            orderItemDTO.setItemQuantity(cart.getProductQuantityInCart());
            orderItemDTO.setItemName(productDtoResponse.getProductName());
            orderItemDTO.setItemPrice(productDtoResponse.getProductPrice());
            // Add the order item to the order request
            orderRequest.getOrderItems().add(orderItemDTO);
        }

        // Call the order service to place the order
        OrderResponse orderResponse = orderService.orderInsert(orderRequest);

        for (Cart cart : carts) {
            cart.setOrderId(orderResponse.getId());
            cart.setOrdered(true);
            cart.setRemoved(true);
        }
        cartRepository.saveAll(carts);
        // Return the order ID
        return orderResponse.getId();

    }


    public boolean deleteCart(Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setRemoved(true);//soft deletion
            cartRepository.save(cart);
            return true;
        } else {
            // Cart with given ID not found
            return false;
        }
    }

    public CartDtoResponse mapToDto(Cart cart) {
        CartDtoResponse cartDtoResponse = new CartDtoResponse();
        cartDtoResponse.setId(cart.getId());
        cartDtoResponse.setUserId(cart.getUserId());
        cartDtoResponse.setProductId(cart.getProductId());
        cartDtoResponse.setProductQuantityInCart(cart.getProductQuantityInCart());
        cartDtoResponse.setCreatedAt(cart.getCreatedAt());
        cartDtoResponse.setUpdatedAt(cart.getUpdatedAt());
        cartDtoResponse.setOrdered(cart.isOrdered());
        cartDtoResponse.setRemoved(cart.isRemoved());
        return cartDtoResponse;
    }

}
