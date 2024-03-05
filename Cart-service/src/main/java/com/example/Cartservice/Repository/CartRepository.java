package com.example.Cartservice.Repository;

import com.example.Cartservice.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByIdAndUserId(Long cartId, Long userId);
    List<Cart> findByUserId(Long userId);

}