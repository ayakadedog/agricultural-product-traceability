package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Product.Product;
import com.example.demo.entity.Product.ProductSaveVo;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public void saveProduct(ProductSaveVo productSaveVo,Long id) {

        Product product = new Product();
        BeanUtils.copyProperties(productSaveVo,product);

        product.setCreateTime(LocalDateTime.now());
        product.setCreateUser(id);

        this.save(product);
    }
}
