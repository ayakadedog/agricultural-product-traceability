package com.example.demo.entity.User;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户信息
 */
@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private String account;

    private String password;

    private String email;

    private String phone;

    //1农，2商，3买
    private Integer userType;

    private String address;

    private LocalDateTime createTime;

    private Long createUser;

    private LocalDateTime updateTime;

}
