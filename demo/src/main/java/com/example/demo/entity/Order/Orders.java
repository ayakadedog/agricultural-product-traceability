package com.example.demo.entity.Order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Orders {
    private Long id;

    private Long supplierId;

    private Long buyerId;

    private Long productId;

    private Double number;

    private Double amount;

    private LocalDateTime orderTime;
}
