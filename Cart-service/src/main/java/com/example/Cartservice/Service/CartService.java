package com.example.Cartservice.Service;

import com.example.Cartservice.Dto.CartDtoResponse;
import com.example.Cartservice.Dto.CartDtoRequest;
import com.example.Cartservice.Dto.ProductDtoResponse;
import com.example.Cartservice.Dto.UserDtoResponse;
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

//    public CartService(ProductService productService) {
//        this.productService = productService;
//    }

    public CartDtoResponse addProductToCart(CartDtoRequest cartItemDtoRequest) {
        // Validate input DTO
        if (cartItemDtoRequest == null) {
            throw new IllegalArgumentException("Cart item request cannot be null");
        }
        if (cartItemDtoRequest.getProductId() == null || cartItemDtoRequest.getProductQuantityInCart() == null || cartItemDtoRequest.getProductQuantityInCart() <= 0) {
            throw new IllegalArgumentException("Invalid cart item data");
        }

        if(cartItemDtoRequest.getUserId()== null){
            throw new IllegalArgumentException("Invalid cart item data");
        }

        // Fetch product details
        UserDtoResponse userDtoResponse = userService.getUserDetails(cartItemDtoRequest.getUserId());
        ProductDtoResponse productDtoResponse = productService.getProductDetails(cartItemDtoRequest.getProductId());


        Cart cart = new Cart();

        cart.setUserId(userDtoResponse.getId());

        cart.setProductId(productDtoResponse.getProductId());
        cart.setProductQuantityInCart(cartItemDtoRequest.getProductQuantityInCart());
        cart.setCreatedAt(new Date());
        cart.setUpdatedAt(new Date());
        cart.setOrdered(false);
        cart.setRemoved(false);


        Cart savedCart = cartRepository.save(cart);

        // Convert the saved cart entity to DTO and return

        CartDtoResponse cartItemDtoResponse = new CartDtoResponse();
        cartItemDtoResponse.setId(savedCart.getId());
        cartItemDtoResponse.setUserId(savedCart.getUserId());
        cartItemDtoResponse.setProductId(savedCart.getProductId());
        cartItemDtoResponse.setProductQuantityInCart(savedCart.getProductQuantityInCart());
        cartItemDtoResponse.setCreatedAt(savedCart.getCreatedAt());
        cartItemDtoResponse.setUpdatedAt(savedCart.getUpdatedAt());
        cartItemDtoResponse.setOrdered(savedCart.isOrdered());
        cartItemDtoResponse.setRemoved(savedCart.isRemoved());

        return cartItemDtoResponse;
    }

}
