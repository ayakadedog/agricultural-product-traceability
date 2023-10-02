package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Order.Orders;
import com.example.demo.entity.Order.OrderVo;
import com.example.demo.entity.Product.Product;

public interface OrderService extends IService<Orders> {

    void saveOrder(OrderVo OrderVo, Product product,Long id);

}
