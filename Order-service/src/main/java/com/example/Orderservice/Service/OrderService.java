package com.example.Orderservice.Service;

import com.example.Orderservice.Dto.OrderItemDTO;
import com.example.Orderservice.Dto.OrderRequest;
import com.example.Orderservice.Dto.OrderResponse;
import com.example.Orderservice.Entity.Order;
import com.example.Orderservice.Entity.OrderItem;
import com.example.Orderservice.Entity.OrderStatus;
import com.example.Orderservice.Repository.OrderItemRepository;
import com.example.Orderservice.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public OrderResponse findById(Long id) {
        Optional<Order> orderResult = orderRepository.findById(id);
        OrderResponse response = new OrderResponse();

        if (orderResult.isPresent()) {
            Order order = orderResult.get();
            response = mapOrderToResponse(order);
        } else {
            return null;
        }
        return response;
    }

    @Override
    public OrderResponse orderInsert(OrderRequest orderRequest) {




        Integer status = orderRequest.getStatus();
        Long userId = orderRequest.getUserId();
        List<OrderItemDTO> orderItemsDTO = orderRequest.getOrderItems();
        Double subtotal = 0.0;

        Order orderObj = new Order(status, 0.0, userId, new Date(), new Date());

        for (OrderItemDTO itemDTO : orderItemsDTO) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedAt(new Date());
            orderItem.setUpdatedAt(new Date());
            orderItem.setItemQuantity(itemDTO.getItemQuantity());
            orderItem.setItemName(itemDTO.getItemName());
            orderItem.setItemPrice(itemDTO.getItemPrice());

            // Calculate and set the total price for the item
            double itemTotalPrice = itemDTO.getItemQuantity() * itemDTO.getItemPrice();
            orderItem.setItemtotalPrice(itemTotalPrice);
            // Add itemTotalPrice to subtotal
            subtotal += itemTotalPrice;

            orderItem.setOrder(orderObj);
            orderObj.getOrderItems().add(orderItem);
        }

        orderObj.setSubtotal(subtotal);

        Order savedOrder = orderRepository.save(orderObj);

        OrderResponse orderResponse = mapOrderToResponse(savedOrder);

        return orderResponse;
    }

//    @Override
//    public void insertAll(List<OrderRequest> orderRequest){
//        if (orderRequest != null) {
//            //log.info("Inserting Bulk Order");
//            for (OrderRequest order: orderRequest) {
//                this.orderInsert(order);
//            }
//        }
//    }

    @Override
    public boolean cancle(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setStatus(40);
            orderRepository.save(order);
            return true;
        }
      return false;
    }


//    @Override
//    public boolean updateAll(List<OrderRequest> orderRequest, Long id) {
//        //log.info("Updating list of order of size : {}",orderRequest.size());
//        for (OrderRequest order: orderRequest) {
//            this.update(order, id);
//        }
//        return true;
//    }

    @Override
    public OrderResponse update(OrderRequest orderRequest, Long id) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        OrderResponse response = new OrderResponse();

        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();

            // Update order with data from orderRequest
            order.setStatus(orderRequest.getStatus());
            order.setSubtotal(orderRequest.getSubtotal());
            order.setUserId(orderRequest.getUserId());
            // Update other fields as needed

            // Convert OrderItemDTOs from OrderRequest to OrderItems
            List<OrderItemDTO> orderItemDTOs = orderRequest.getOrderItems();
            List<OrderItem> orderItems = new ArrayList<>();
            for (OrderItemDTO orderItemDTO : orderItemDTOs) {
                OrderItem orderItem = new OrderItem();
                orderItem.setItemQuantity(orderItemDTO.getItemQuantity());
                orderItem.setItemName(orderItemDTO.getItemName());
                orderItem.setItemPrice(orderItemDTO.getItemPrice());
                orderItem.setItemtotalPrice(orderItemDTO.getItemTotalPrice());
                // Map other fields as needed
                orderItems.add(orderItem);
            }
            order.setOrderItems(orderItems);

            // Save the updated order
            Order updatedOrder = orderRepository.save(order);

            response = mapOrderToResponse(updatedOrder);

            return response;
        } else {
            // Order with given id does not exist, cannot update
            return null; // Or throw an exception if needed
        }
    }

    // Method to map OrderItem to OrderItemDTO
    private OrderItemDTO mapOrderItemToDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setItemId(orderItem.getItemId());
        orderItemDTO.setCreatedAt(orderItem.getCreatedAt());
        orderItemDTO.setUpdatedAt(orderItem.getUpdatedAt());
        orderItemDTO.setItemQuantity(orderItem.getItemQuantity());
        orderItemDTO.setItemName(orderItem.getItemName());
        orderItemDTO.setItemPrice(orderItem.getItemPrice());
        orderItemDTO.setItemTotalPrice(orderItem.getItemtotalPrice());
        return orderItemDTO;
    }

    // Method to map Order to OrderResponse
    private OrderResponse mapOrderToResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus());
        response.setSubtotal(order.getSubtotal());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setUserId(order.getUserId());

        // Map order items to OrderItemDTOs
        List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItemDTOs.add(mapOrderItemToDTO(orderItem));
        }
        response.setOrderItems(orderItemDTOs);
        return response;
    }

}

