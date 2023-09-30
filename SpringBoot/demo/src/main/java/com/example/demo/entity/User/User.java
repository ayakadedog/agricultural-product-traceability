package com.example.demo.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName user
*/
@Data
@Builder
public class User implements Serializable {

    /**
    * 用户唯一标识符
    */
    @NotNull(message="[用户唯一标识符]不能为空")
    @ApiModelProperty("用户唯一标识符")
    private Integer id;
    /**
    * 用户名称
    */
    @Size(max= 40,message="编码长度不能超过40")
    @ApiModelProperty("用户名称")
    @Length(max= 40,message="编码长度不能超过40")
    private String name;
    /**
    * 用户名称
    */
    @Size(max= 40,message="编码长度不能超过40")
    @ApiModelProperty("用户名称")
    @Length(max= 40,message="编码长度不能超过40")
    private String account;
    /**
    * 用户密码
    */
    @Size(max= 40,message="编码长度不能超过40")
    @ApiModelProperty("用户密码")
    @Length(max= 40,message="编码长度不能超过40")
    private String password;
    /**
    * 用户联系人电子邮件
    */
    @Size(max= 40,message="编码长度不能超过40")
    @ApiModelProperty("用户联系人电子邮件")
    @Length(max= 40,message="编码长度不能超过40")
    private String email;
    /**
    * 用户联系人电话号码
    */
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("用户联系人电话号码")
    @Length(max= 20,message="编码长度不能超过20")
    private String phone;
    /**
    * 用户类型
    */
    @Size(max= 40,message="编码长度不能超过40")
    @ApiModelProperty("用户类型")
    @Length(max= 40,message="编码长度不能超过40")
    private String userType;
    /**
    * 用户地址
    */
    @Size(max= 40,message="编码长度不能超过40")
    @ApiModelProperty("用户地址")
    @Length(max= 40,message="编码长度不能超过40")
    private String address;
    /**
    * 
    */
    @ApiModelProperty("")
    private LocalDateTime createDate;
    /**
    * 
    */
    @ApiModelProperty("")
    private String creatureUser;
    /**
    * 
    */
    @ApiModelProperty("")
    private String updateUser;
    /**
    * 
    */
    @ApiModelProperty("")
    private LocalDateTime updateTime;

}
