package com.example.demo.entity.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName product
*/
@Data
@Builder
public class Product implements Serializable {

    /**
    * 产品唯一标识符
    */
    @NotNull(message="[产品唯一标识符]不能为空")
    @ApiModelProperty("产品唯一标识符")
    private Integer id;
    /**
    * 产品图片
    */
    @Size(max= 80,message="编码长度不能超过80")
    @ApiModelProperty("产品图片")
    @Length(max= 80,message="编码长度不能超过80")
    private String picture;
    /**
    * 产品名字
    */
    @Size(max= 40,message="编码长度不能超过40")
    @ApiModelProperty("产品名字")
    @Length(max= 40,message="编码长度不能超过40")
    private String name;
    /**
    * 产品品种
    */
    @Size(max= 40,message="编码长度不能超过40")
    @ApiModelProperty("产品品种")
    @Length(max= 40,message="编码长度不能超过40")
    private String variety;
    /**
    * 采摘日期
    */
    @ApiModelProperty("采摘日期")
    private Date harvestTime;
    /**
    * 生产地
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("生产地")
    @Length(max= 100,message="编码长度不能超过100")
    private String productionLocation;
    /**
    * 生产日期
    */
    @ApiModelProperty("生产日期")
    private Date productionDate;
    /**
    * 单价
    */
    @ApiModelProperty("单价")
    private Double price;
    /**
    * 创建人
    */
    @Size(max= 40,message="编码长度不能超过40")
    @ApiModelProperty("创建人")
    @Length(max= 40,message="编码长度不能超过40")
    private String createUser;

    /**
    * 产品唯一标识符
    */
    private void setId(Integer id){
    this.id = id;
    }

    /**
    * 产品图片
    */
    private void setPicture(String picture){
    this.picture = picture;
    }

    /**
    * 产品名字
    */
    private void setName(String name){
    this.name = name;
    }

    /**
    * 产品品种
    */
    private void setVariety(String variety){
    this.variety = variety;
    }

    /**
    * 采摘日期
    */
    private void setHarvestTime(Date harvestTime){
    this.harvestTime = harvestTime;
    }

    /**
    * 生产地
    */
    private void setProductionLocation(String productionLocation){
    this.productionLocation = productionLocation;
    }

    /**
    * 生产日期
    */
    private void setProductionDate(Date productionDate){
    this.productionDate = productionDate;
    }

    /**
    * 单价
    */
    private void setPrice(Double price){
    this.price = price;
    }

    /**
    * 创建人
    */
    private void setCreateUser(String createUser){
    this.createUser = createUser;
    }


    /**
    * 产品唯一标识符
    */
    private Integer getId(){
    return this.id;
    }

    /**
    * 产品图片
    */
    private String getPicture(){
    return this.picture;
    }

    /**
    * 产品名字
    */
    private String getName(){
    return this.name;
    }

    /**
    * 产品品种
    */
    private String getVariety(){
    return this.variety;
    }

    /**
    * 采摘日期
    */
    private Date getHarvestTime(){
    return this.harvestTime;
    }

    /**
    * 生产地
    */
    private String getProductionLocation(){
    return this.productionLocation;
    }

    /**
    * 生产日期
    */
    private Date getProductionDate(){
    return this.productionDate;
    }

    /**
    * 单价
    */
    private Double getPrice(){
    return this.price;
    }

    /**
    * 创建人
    */
    private String getCreateUser(){
    return this.createUser;
    }

}
