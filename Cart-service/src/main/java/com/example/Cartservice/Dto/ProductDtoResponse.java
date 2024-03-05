package com.example.Cartservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoResponse {
    private Long productId;
    private String productName;
    private Double productPrice;
    private String productDescription;
}
