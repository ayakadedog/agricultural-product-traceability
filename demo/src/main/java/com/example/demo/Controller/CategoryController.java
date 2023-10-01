package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Category.Category;
import com.example.demo.entity.User.User;
import com.example.demo.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 只给农名使用
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param request
     * @param category
     * @return
     */
    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody Category category) {

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getName,category.getName());
        Category one = categoryService.getOne(queryWrapper);
        if (one != null){
            return Result.error("分类已存在");
        }

        category.setUpdateTime(LocalDateTime.now());
        category.setCreateTime(LocalDateTime.now());

        Long userId = (Long) request.getSession().getAttribute("userId");
        category.setCreateUser(userId);

        categoryService.save(category);
        return Result.success("新增分类成功");

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
                              HttpServletRequest request) {
        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        Long userId = (Long) request.getSession().getAttribute("userId");


        //分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getCreateUser,userId);
        //添加排序条件，根据sort进行排序
        queryWrapper.orderByAsc(Category::getSort);

        //分页查询
        categoryService.page(pageInfo, queryWrapper);
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

        //TODO 要考虑删前检测
        categoryService.removeById(ids);
        //categoryService.remove(ids);

        return Result.success("分类信息删除成功");
    }

    /**
     * 根据id修改分类信息
     *
     * @param category
     * @return
     */
    @PutMapping
    public Result<String> update(HttpServletRequest request, @RequestBody Category category) {
        log.info("修改分类信息：{}", category);

        category.setUpdateTime(LocalDateTime.now());
        categoryService.updateById(category);

        return Result.success("修改分类信息成功");
    }


    /**
     * 根据条件查询分类数据
     *
     * @param category
     * @return
     */
    @GetMapping("/list")
    public Result<List<Category>> list(Category category) {
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        queryWrapper.eq(Category::getCreateUser,category.getCreateUser());
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);

        return Result.success(list);
    }

}
