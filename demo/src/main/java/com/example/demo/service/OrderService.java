package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Order.OrderVo2;
import com.example.demo.entity.Order.Orders;
import com.example.demo.entity.Order.OrderVo;
import com.example.demo.entity.Product.Product;
import com.example.demo.entity.Supplier.Supplier;

public interface OrderService extends IService<Orders> {

    void saveOrder(OrderVo OrderVo, Product product,Long id);

    void saveOrder2(OrderVo2 OrderVo2, Supplier supplier, Long id);


}
