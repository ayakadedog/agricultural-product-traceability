package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Product.Product;
import com.example.demo.entity.Product.ProductSaveVo;
import com.example.demo.entity.User.UserRegisterVo;

;

public interface ProductService extends IService<Product> {

    void saveProduct(ProductSaveVo productSaveVo,Long id);


}
