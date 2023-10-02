package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User.UserRegisterVo;
import com.example.demo.service.UserService;
import com.example.demo.entity.User.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Resource
    private UserMapper userMapper;

    public Object get(HttpServletRequest request,String key){
        return request.getSession().getAttribute(key);
    }

    public void set(HttpServletRequest request,String key,Object value){
        request.getSession().setAttribute(key,value);
    }

    @Override
    public void Register(UserRegisterVo userRegisterVo) {

        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setUserType(userRegisterVo.getUserType());
        user.setAccount(userRegisterVo.getAccount());
        user.setPassword(userRegisterVo.getPassword());

        this.save(user);
    }
}
