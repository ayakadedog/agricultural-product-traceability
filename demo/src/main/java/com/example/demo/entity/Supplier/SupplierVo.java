package com.example.demo.entity.Supplier;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SupplierVo {

    private String name;

    private String category;

    private LocalDateTime harvestTime;

    private String productionLocation;

    private Double price;

    private Double number;
}
