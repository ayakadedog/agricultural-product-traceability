package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.common.SessionTool;
import com.example.demo.entity.User.User;
import com.example.demo.entity.User.UserRegisterVo;
import com.example.demo.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wms
 * @since 2022-07-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private SessionTool sessionTool;

    @Override
    public void Register(UserRegisterVo userRegisterVo, HttpServletRequest request) {

        User user = User.builder().
                creatureUser((String) sessionTool.get(request,"userId")).
                updateUser((String) sessionTool.get(request,"userId")).
                createDate(LocalDateTime.now()).
                updateTime(LocalDateTime.now()).
                userType(userRegisterVo.getUserType()).
                account(userRegisterVo.getAccount()).
                build();

        userMapper.insert(user);

    }
}
