package com.example.Orderservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long itemId;
    private Date createdAt;
    private Date updatedAt;
    private Integer itemQuantity;
    private String itemName;
    private Double itemPrice;
    private Double itemTotalPrice;
}
