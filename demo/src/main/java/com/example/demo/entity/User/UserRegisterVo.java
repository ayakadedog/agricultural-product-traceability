package com.example.demo.entity.User;

import lombok.Data;


@Data
public class UserRegisterVo {

    private String name;

    private String account;

    private String password;

    private String checkPassword;

    private Integer userType;

}
