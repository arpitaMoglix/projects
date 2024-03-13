package com.example.Orderservice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    @Column(name = "quantity", nullable = false)
    private Integer itemQuantity;//product quantity

    @Column(name = "item_name")
    private String itemName; ////product name

    @Column(name = "price_per_unit")
    private Double itemPrice; //product price per unit for db

    @Column(name = "total_price")
    private Double itemtotalPrice; // product price (quantity * itemprice to show the client)

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

//    @Column(name = "product_id")
//    private Long productId;

}

