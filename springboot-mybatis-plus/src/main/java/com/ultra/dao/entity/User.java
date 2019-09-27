package com.ultra.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ultra.constant.LengthValidatedConstant;
import com.ultra.constant.RegexpConstant;
import com.ultra.validated.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author ${author}
 * @since 2019-09-25
 */
@Setter
@Getter
@ToString
@TableName("spring_user")
@ApiModel(value = "User对象", description = "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(groups = {UpdateGroup.class}, message = "{attr.required}")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "{attr.required}")
    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
    @Pattern(regexp = RegexpConstant.NAME_RE, message = "{attr.illegal}")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "{attr.required}")
    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "{attr.required}")
    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
    @Pattern(regexp = RegexpConstant.NAME_RE, message = "{attr.illegal}")
    private String email;

    @ApiModelProperty(value = "地址")
    @NotBlank(message = "{attr.required}")
    @Length(min = LengthValidatedConstant.PASSWORD_MIN_LENGTH, max = LengthValidatedConstant.PASSWORD_MAX_LENGTH, message = "{attr.range.length}")
    @Pattern(regexp = RegexpConstant.NAME_RE, message = "{attr.illegal}")
    private String address;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "是否过期 0否 1是")
    private Integer expired;

    @ApiModelProperty(value = "是否禁用 0否 1是")
    private Integer disabled;

    @ApiModelProperty(value = "是否锁定 0否 1是")
    private Integer locked;

    @ApiModelProperty(value = "备注")
    @NotBlank(message = "{attr.required}")
    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
    @Pattern(regexp = RegexpConstant.NAME_RE, message = "{attr.illegal}")
    private String note;

    //需要返回实现org.apereo.cas.authentication.principal.Principal的类名接口
    @TableField(exist = false)
    @JsonProperty("@class")
    private String clazz = "org.apereo.cas.authentication.principal.SimplePrincipal";

    @JsonProperty("attributes")
    @TableField(exist = false)
    private Map<String, Object> attributes = new HashMap<>();
}
