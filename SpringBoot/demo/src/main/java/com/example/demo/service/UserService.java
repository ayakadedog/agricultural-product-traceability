package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User.User;
import com.example.demo.entity.User.UserRegisterVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms
 * @since 2022-07-21
 */
public interface UserService extends IService<User> {

    void Register(UserRegisterVo userRegisterVo, HttpServletRequest request);
}
