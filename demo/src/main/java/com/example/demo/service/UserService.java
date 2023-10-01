package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User.User;
import com.example.demo.entity.User.UserRegisterVo;
;

public interface UserService extends IService<User> {
    void Register(UserRegisterVo userRegisterVo);

}
