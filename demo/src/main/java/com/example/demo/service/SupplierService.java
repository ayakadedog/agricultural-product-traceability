package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Product.Product;
import com.example.demo.entity.Product.ProductSaveVo;
import com.example.demo.entity.Supplier.Supplier;

;

public interface SupplierService extends IService<Supplier> {

    void saveSupplier(Product product,Double number,Long id);

}
