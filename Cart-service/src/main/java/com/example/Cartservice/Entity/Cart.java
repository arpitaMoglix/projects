package com.example.Cartservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer productQuantityInCart = 0;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;


    @Column(name = "is_removed", columnDefinition = "boolean default false")
    private boolean isRemoved;

    @Column(name = "is_ordered", columnDefinition = "boolean default false")
    private boolean isOrdered;


    @Column(name = "user_id")
    private Long userId;


    @Column(name = "order_id")
    private Long orderId;


    @Column(name = "product_id")
    private Long productId;

}

