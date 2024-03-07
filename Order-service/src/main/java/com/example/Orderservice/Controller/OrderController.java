package com.example.Orderservice.Controller;

import com.example.Orderservice.Dto.OrderRequest;
import com.example.Orderservice.Dto.OrderResponse;
import com.example.Orderservice.Entity.Order;
import com.example.Orderservice.Service.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceInterface orderService;
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse order = orderService.orderInsert(orderRequest);
        return new ResponseEntity<OrderResponse>(order,HttpStatus.OK);
    }

    @GetMapping("/getDetails/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id){
        OrderResponse result = orderService.findById(id);
        return new ResponseEntity<OrderResponse>(result,HttpStatus.OK);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id){
        if (id != 0) {
            if(orderService.cancle(id)) {
                return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping("/create/bulk")
//    public ResponseEntity<Void> addBulkOrder(@RequestBody List<OrderRequest> order){
//        if(order != null && !order.isEmpty()) {
//            orderService.insertAll(order);
//            return new ResponseEntity<Void>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//        }
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateOrder(@RequestBody OrderRequest order, @PathVariable Long id){
        if (order != null){
            orderService.update(order, id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

//    @PutMapping("/update/bulk/{id}")
//    public ResponseEntity<Void> bulkUpdateOrder(@RequestBody List<OrderRequest> order, @PathVariable Long id){
//        if (order != null){
//            orderService.updateAll(order, id);
//            return new ResponseEntity<Void>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//        }
//    }

}
