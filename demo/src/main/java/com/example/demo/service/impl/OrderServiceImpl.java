package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Order.OrderVo2;
import com.example.demo.entity.Order.Orders;
import com.example.demo.entity.Order.OrderVo;
import com.example.demo.entity.Product.Product;
import com.example.demo.entity.Supplier.Supplier;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {


    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ProductService productService;

    @Resource
    private SupplierService supplierService;
    @Override
    public void saveOrder(OrderVo orderVo,Product product,Long id) {

        /**
         * 对订单表进行添加
         */
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderVo, orders);
        orders.setOrderTime(LocalDateTime.now());
        orders.setAmount(orderVo.getNumber()*product.getPrice());
        orders.setBuyerId(id);
        orders.setSupplierId(product.getCreateUser());

        this.save(orders);


        /**
         * 对供应商的库进行修改
         */
        LambdaUpdateWrapper<Product> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(Product::getId,orderVo.getProductId());
        queryWrapper.set(Product::getNumber,product.getNumber()-orderVo.getNumber());
        productService.update(queryWrapper);


        /**
         * 对购买者的库进行添加
         */
        supplierService.saveSupplier(product,orderVo.getNumber(),orders.getSupplierId());
    }

    @Override
    public void saveOrder2(OrderVo2 orderVo2, Supplier supplier, Long id) {

        /**
         * 对订单表进行添加
         */
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderVo2, orders);
        orders.setOrderTime(LocalDateTime.now());
        orders.setAmount(orderVo2.getNumber()*supplier.getPrice());
        orders.setBuyerId(id);
        orders.setSupplierId(supplier.getCreateUser());

        this.save(orders);


        /**
         * 对供应商的库进行修改
         */
        LambdaUpdateWrapper<Supplier> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(Supplier::getId,orderVo2.getSupplierId());
        queryWrapper.set(Supplier::getNumber,supplier.getNumber()-orderVo2.getNumber());
        supplierService.update(queryWrapper);

    }
}
