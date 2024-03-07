package com.example.Orderservice.Repository;

import com.example.Orderservice.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("FROM OrderItem WHERE id = ?1")
    List<OrderItem> findAllById(Long id);
}