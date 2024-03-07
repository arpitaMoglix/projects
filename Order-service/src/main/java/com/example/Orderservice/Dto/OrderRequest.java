package com.example.Orderservice.Dto;

import com.example.Orderservice.Entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Integer status;
    private Long subtotal;
    private Date createdAt;
    private Date updatedAt;
    private Long userId;
    private List<OrderItemDTO> orderItems;
}
