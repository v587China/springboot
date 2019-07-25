package com.ultra.dao.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ultra.validated.UpdateGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value = "用户表")
@Setter
@Getter
@ToString
@TableName("spring_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = { UpdateGroup.class }, message = " {attr.required}")
    private Long id;
    @ApiModelProperty(value = "名称")
    @NotBlank(message = " {attr.required}")
    @Length(min = 3, max = 30, message = " {attr.range.length}")
    private String name;
    @ApiModelProperty(value = "密码")
    @NotBlank(message = " {attr.required}")
    @Length(min = 3, max = 30, message = " {attr.range.length}")
    private String password;

}