package com.ultra.dao.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ultra.validated.UpdateGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@ApiModel(value = "用户表")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spring_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = { UpdateGroup.class })
    private Long id;
    @NotBlank
    @ApiModelProperty(value = "名称")
    private String name;
    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;

}