package com.example.demo.entity.Category;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 分类
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //类型 0蔬菜 1是水果
    private Integer type;

    //分类名称
    private String name;

    //顺序
    private Integer sort;

    private Long createUser;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
