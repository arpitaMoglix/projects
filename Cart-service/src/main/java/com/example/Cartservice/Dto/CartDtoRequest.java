package com.example.Cartservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDtoRequest {
    private Long Id;
    private Long productId;
    private Long userId;
    private Integer productQuantityInCart;
    private Date createdAt;
    private Date updatedAt;
    private boolean isRemoved;
    private boolean isOrdered;

}