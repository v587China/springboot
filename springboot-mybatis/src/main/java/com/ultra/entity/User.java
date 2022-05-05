package com.ultra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ultra.validated.InsertGroup;
import com.ultra.validated.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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
    @NotBlank(groups = {InsertGroup.class}, message = "{attr.required}")
//    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
//    @Pattern(regexp = RegexpConstant.NAME_REGEXP, message = "{attr.illegal}")
    private String username;

    /**
     * @JsonIgnore:不传递给前台的属性等价于在类上的注解@JsonIgnoreProperties(value = "{password}")
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(groups = {InsertGroup.class}, message = "{attr.required}")
//    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(groups = {InsertGroup.class}, message = "{attr.required}")
//    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
//    @Pattern(regexp = RegexpConstant.EMAIL_REGEXP, message = "{attr.illegal}")
    private String email;

    @ApiModelProperty(value = "地址")
    @NotBlank(groups = {InsertGroup.class}, message = "{attr.required}")
//    @Length(min = LengthValidatedConstant.PASSWORD_MIN_LENGTH, max = LengthValidatedConstant.PASSWORD_MAX_LENGTH, message = "{attr.range.length}")
    private String address;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "是否过期 0否 1是")
    @TableField("is_expired")
    private Boolean expired;

    @ApiModelProperty(value = "是否禁用 0否 1是")
    @TableField("is_disabled")
    private Boolean disabled;

    @ApiModelProperty(value = "是否锁定 0否 1是")
    @TableField("is_locked")
    private Boolean locked;

    @ApiModelProperty(value = "备注")
    @NotBlank(message = "{attr.required}")
    //@Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

}
