package com.ultra.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.ultra.constant.LengthValidatedConstant;
import com.ultra.constant.RegexpConstant;
import com.ultra.validated.UpdateGroup;
import org.hibernate.validator.constraints.Length;
/**
 * <p>
 * 用户
 * </p>
 *
 * @author ${author}
 * @since 2019-09-06
 */
@Setter
@Getter
@ToString
@TableName("spring_user")
@ApiModel(value="User对象", description="用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(groups = { UpdateGroup.class }, message = "{attr.required}")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "{attr.required}")
    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
    @Pattern(regexp = RegexpConstant.NAME_RE, message = "{attr.illegal}")
    private String name;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "{attr.required}")
    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
    @Pattern(regexp = RegexpConstant.NAME_RE, message = "{attr.illegal}")
    private String password;


}
