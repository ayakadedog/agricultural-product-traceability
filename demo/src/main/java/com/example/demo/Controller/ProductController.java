package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Category.Category;
import com.example.demo.entity.Product.Product;
import com.example.demo.entity.Product.ProductSaveVo;
import com.example.demo.entity.User.User;
import com.example.demo.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;
    @PostMapping
    public Result<String> save(@RequestBody ProductSaveVo productSaveVo,
                               HttpServletRequest request) {

        Long id = (Long) request.getSession().getAttribute("userId");
        productService.saveProduct(productSaveVo,id);

        return Result.success("创建成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page (@RequestParam(required = false) Integer page,
                              @RequestParam(required = false) Integer pageSize,
                              @RequestParam(required = false) String name,
                              HttpServletRequest request) {

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        Long userId = (Long) request.getSession().getAttribute("userId");

        //分页构造器
        Page<Product> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Product::getName,name);
        queryWrapper.eq(Product::getCreateUser,userId);

        queryWrapper.orderByAsc(Product::getCategory);

        //分页查询
        productService.page(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 根据id删除分类
     *
     * @param ids
     * @return
     */
//    @GetMapping("{id}")
//    public void example(@PathVariable Long id)
    @DeleteMapping
    public Result<String> delete(Long ids) {

        productService.removeById(ids);

        return Result.success("分类信息删除成功");
    }

}