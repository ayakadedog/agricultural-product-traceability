package com.example.demo.entity.Product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductSaveVo {

    private String name;

    private String category;

    private LocalDateTime harvestTime;

    private String productionLocation;

    private Double price;

    private Double number;
}
