package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Order.OrderVo;
import com.example.demo.entity.Order.OrderVo2;
import com.example.demo.entity.Order.Orders;
import com.example.demo.entity.Product.Product;
import com.example.demo.entity.Supplier.Supplier;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.Order2Service;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class Order2ServiceImpl extends ServiceImpl<OrderMapper, Orders> implements Order2Service {


    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ProductService productService;

    @Resource
    private SupplierService supplierService;

}
