package com.example.Cartservice.Repository;

import com.example.Cartservice.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByIdAndUserId(Long cartId, Long userId);
    List<Cart> findByUserId(Long userId);

//    @Query(value = "SELECT * FROM cart WHERE is_ordered = 0 AND is_removed = 0 and userId = :userId", nativeQuery = true)
//    List<Cart> findCartsByUserIdAndNotOrderedAndNotRemoved(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM cart WHERE is_ordered = 0 AND is_removed = 0 AND user_id = :userId", nativeQuery = true)
    List<Cart> findCartsByUserIdAndNotOrderedAndNotRemoved(@Param("userId") Long userId);

    Optional<Cart> findById(Long cartId);

    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);

}