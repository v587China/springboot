package com.ultra.dao.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ultra.constant.LengthValidatedConstant;
import com.ultra.constant.RegexpConstant;
import com.ultra.validated.UpdateGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ${author}
 * @since 2019-08-09
 */
@Setter
@Getter
@ToString
@TableName("spring_user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = { UpdateGroup.class }, message = "{attr.required}")
    private Long id;
    @ApiModelProperty(value = "名称")
    @NotBlank(message = "{attr.required}")
    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
    @Pattern(regexp = RegexpConstant.NAME_RE, message = "{attr.illegal}")
    private String name;
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "{attr.required}")
    @Length(min = LengthValidatedConstant.PASSWORD_MIN_LENGTH, max = LengthValidatedConstant.PASSWORD_MAX_LENGTH, message = "{attr.range.length}")
    private String password;

}