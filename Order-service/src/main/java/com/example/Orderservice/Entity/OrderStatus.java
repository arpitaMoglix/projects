package com.example.Orderservice.Entity;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    ORDER_PLACED(10),      // passing 10 to the constructor
    PROCESSING(15),
    OUT_FOR_DELIVERY(20),
    COMPLETED(30),
    CANCELLED(40);

    private final int statusCode;

    // Constructor for initializing the statusCode field
    OrderStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    // Getter method for accessing the statusCode
    public int getStatusCode() {
        return statusCode;
    }

    private static final Map<Integer, OrderStatus> codeToStatusMap = new HashMap<>();
    static {
        for (OrderStatus status : OrderStatus.values()) {
            codeToStatusMap.put(status.getStatusCode(), status);
        }
    }

    // Method to get OrderStatus enum from status code
    public static OrderStatus fromStatusCode(int statusCode) {
        return codeToStatusMap.get(statusCode);
    }
}