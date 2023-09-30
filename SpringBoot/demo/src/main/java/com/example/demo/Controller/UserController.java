package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.common.SessionTool;
import com.example.demo.entity.User.User;
import com.example.demo.entity.User.UserLoginVo;
import com.example.demo.entity.User.UserRegisterVo;
import com.example.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private SessionTool sessionTool;

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

        sessionTool.set(request,"userId",user.getId());
        sessionTool.set(request,"userType",user.getUserType());

        return Result.success(user);

    }


    /**
     * 注册
     * @param userRegisterVo
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody UserRegisterVo userRegisterVo, HttpServletRequest request){

        String password = userRegisterVo.getPassword();
        String checkPassword = userRegisterVo.getCheckPassword();

        if (!password.equals(checkPassword)){
            return Result.error("两次输入的密码不一致");
        }

        String account = userRegisterVo.getAccount();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        Object eq = queryWrapper.eq(User::getAccount, account);
        if (eq != null){
           return Result.error("用户名重复");
        }

        userService.Register(userRegisterVo,request);

        return Result.success();
    }


    /**
     * 跟新
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        return userService.updateById(user)
                ?Result.success()
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
    @GetMapping("/listPage")
    public Result<Page> page (int page,int pageSize,String account){

        Page<User> pageInfo = new Page<>(page,pageSize);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(account),User::getAccount,account);
        queryWrapper.orderByAsc(User::getUserType,User::getCreateDate);

        userService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }
}
