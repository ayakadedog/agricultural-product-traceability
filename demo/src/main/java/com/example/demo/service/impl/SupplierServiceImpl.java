package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Product.Product;
import com.example.demo.entity.Product.ProductSaveVo;
import com.example.demo.entity.Supplier.Supplier;
import com.example.demo.mapper.SupplierMapper;
import com.example.demo.service.SupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {

    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public void saveSupplier(Product product,Double number,Long id) {

        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(product,supplier);
        supplier.setId(null);
        supplier.setCreateTime(LocalDateTime.now());
        supplier.setCreateUser(id);
        supplier.setNumber(number);

        this.save(supplier);
    }
}
