package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.entity.Order.OrderVo2;
import com.example.demo.entity.Order.Orders;
import com.example.demo.entity.Product.Product;
import com.example.demo.entity.Supplier.Supplier;
import com.example.demo.service.Order2Service;
import com.example.demo.service.ProductService;
import com.example.demo.service.SupplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


// TODO 用以查询
@RestController
@RequestMapping("/order2")
public class Order2Controller {
    @Resource
    private Order2Service order2Service;

    @Resource
    private ProductService productService;

    @Resource
    private SupplierService supplierService;


    /**
     *
     * @param id 商家端的商品id
     * @return
     */
    @PostMapping("/transaction")
    public Result<Product> transaction (@RequestBody Long id) {

        LambdaQueryWrapper<Supplier> supplierQueryWrapper = new LambdaQueryWrapper<>();
        supplierQueryWrapper.eq(Supplier::getId, id);
        Supplier supplier = supplierService.getOne(supplierQueryWrapper);

        if (supplier != null) {
            LambdaQueryWrapper<Product> productQueryWrapper = new LambdaQueryWrapper<>();
            productQueryWrapper.eq(Product::getId, supplier.getProductId());
            Product product = productService.getOne(productQueryWrapper);

            if (product != null) {
                return Result.success(product);
            }
        }

// 处理未找到结果的情况
        return Result.error("未找到相关产品");



    }
}
