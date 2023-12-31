package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User.User;
import com.example.demo.entity.User.UserLoginVo;
import com.example.demo.entity.User.UserRegisterVo;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;
    /**
     * 登录
     * @param userLoginVo
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginVo userLoginVo, HttpServletRequest request){

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,userLoginVo.getAccount());

        User user = userService.getOne(queryWrapper);

        if(user == null){
            return Result.error("用户不存在，请先注册");
        }

        if (!user.getPassword().equals(userLoginVo.getPassword())) {
            return Result.error("密码错误");
        }

        set(request,"userId",user.getId());
        set(request,"userType",user.getUserType());

        return Result.success(user);

    }


    /**
     * 注册
     * @param userRegisterVo
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegisterVo userRegisterVo, HttpServletRequest request){

        String password = userRegisterVo.getPassword();
        String checkPassword = userRegisterVo.getCheckPassword();

        if (!password.equals(checkPassword)){
            return Result.error("两次输入的密码不一致");
        }

        String account = userRegisterVo.getAccount();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getAccount, account);
        User user = userService.getOne(queryWrapper);
        if (user != null){
            return Result.error("用户名重复");
        }

        userService.Register(userRegisterVo);

        return Result.success("注册成功");
    }


    /**
     * 跟新
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Result<String> update(@RequestBody User user){
        return userService.updateById(user)
                ?Result.success("更新成功")
                :Result.error("更新失败");
    }


    /**
     * 注销
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return userService.removeById(id);
    }

    /**
     * 展示
     * @param page
     * @param pageSize
     * @param account
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page (@RequestParam(required = false) Integer page,
                              @RequestParam(required = false) Integer pageSize,
                              @RequestParam(required = false) String account){
        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }
        Page<User> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(account),User::getAccount,account);
        queryWrapper.orderByAsc(User::getUserType,User::getCreateTime);

        userService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }

    public Object get(HttpServletRequest request,String key){
        return request.getSession().getAttribute(key);
    }

    public void set(HttpServletRequest request,String key,Object value){
        request.getSession().setAttribute(key,value);
    }
}
