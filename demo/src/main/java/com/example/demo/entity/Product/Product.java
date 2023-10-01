package com.example.demo.entity.Product;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Product {

    private Long id;

    /**
     * 图片
     */
    private String picture;

    /**
     * 产品的名称
     */
    private String name;

    /**
     * 产品类型
     * 水果、蔬菜
     */
    private String category;

    /**
     * 收获时间
     */
    private LocalDateTime harvestTime;

    /**
     * 产地
     */
    private String productionLocation;

    /**
     * 生产日期
     */
    private LocalDateTime productionDate;

    /**
     * 价格
     */
    private Double price;

    /**
     * 创建者
     */
    private Long createUser;

}
