package com.example.Cartservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private Integer productQuantityInCart;
    private Date createdAt;
    private Date updatedAt;
    private boolean isRemoved;
    private boolean isOrdered;
    private Long userId;
    private Long orderId;
    private Long productId;

}