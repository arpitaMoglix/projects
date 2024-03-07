package com.example.Orderservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private Integer status;
    private Long subtotal;
    private Date createdAt;
    private Date updatedAt;
    private Long userId;
    private List<OrderItemDTO> orderItems;
}
