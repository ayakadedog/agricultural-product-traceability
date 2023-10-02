package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Product.Product;
import com.example.demo.entity.Supplier.Supplier;
import com.example.demo.service.SupplierService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

        @Resource
        private SupplierService supplierService;

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
            Page<Supplier> pageInfo = new Page<>(page, pageSize);
            //条件构造器
            LambdaQueryWrapper<Supplier> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(StringUtils.isNotEmpty(name), Supplier::getName,name);
            queryWrapper.eq(Supplier::getCreateUser,userId);

            queryWrapper.orderByAsc(Supplier::getCategory);

            //分页查询
            supplierService.page(pageInfo, queryWrapper);
            return Result.success(pageInfo);
        }

        /**
         * 根据id删除分类
         *
         * @param ids
         * @return
         */
        @DeleteMapping
        public Result<String> delete(Long ids) {

            supplierService.removeById(ids);

            return Result.success("分类信息删除成功");
        }

    @GetMapping("/{id}")
    public Result<Supplier> get(@PathVariable Long id){

        LambdaQueryWrapper<Supplier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Supplier::getId,id);

        return Result.success(supplierService.getOne(queryWrapper));
    }

}
