package com.example.Cartservice.Service;

import com.example.Cartservice.Dto.OrderRequest;
import com.example.Cartservice.Dto.OrderResponse;

public interface OrderServiceInterface {

    OrderResponse orderInsert(OrderRequest orderRequest);
}
