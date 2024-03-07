package com.example.Orderservice.Service;

import com.example.Orderservice.Dto.OrderRequest;
import com.example.Orderservice.Dto.OrderResponse;
import com.example.Orderservice.Entity.Order;

import java.util.List;

public interface OrderServiceInterface {

    OrderResponse findById(Long id);

    OrderResponse orderInsert(OrderRequest orderRequest);

//    void insertAll(List<OrderRequest> orderRequest);

    boolean cancle(Long id);

    OrderResponse update(OrderRequest orderRequest, Long id);

//    boolean updateAll(List<OrderRequest> orderRequest, Long id);
}
