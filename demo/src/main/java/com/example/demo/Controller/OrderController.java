package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.entity.Order.OrderVo;
import com.example.demo.entity.Product.Product;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private ProductService productService;

    /**
     * 交易
     * @param orderVo
     * @param request
     * @return
     */
    @PostMapping("/transaction")
    public Result<String> transaction (@RequestBody OrderVo orderVo,
                                       HttpServletRequest request) {

        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getId,orderVo.getProductId());
        Product product = productService.getOne(queryWrapper);

        if ((product.getNumber()-orderVo.getNumber()<0)){
            return Result.error("库存没这么多");
        }

        Long id = (Long) request.getSession().getAttribute("userId");

        orderService.saveOrder(orderVo,product,id);

        return Result.success("购买成功");
    }



}
