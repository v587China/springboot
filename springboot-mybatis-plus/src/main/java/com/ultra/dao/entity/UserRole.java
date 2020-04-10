package com.ultra.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 用户角色关系
 * </p>
 *
 * @author ${author}
 * @since 2019-09-06
 */
@Setter
@Getter
@ToString
@TableName("spring_user_role")
@ApiModel(value = "UserRole对象", description = "用户角色关系")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户主键")
    @NotNull(message = "{attr.required}")
    private Long userId;

    @ApiModelProperty(value = "角色主键")
    @NotNull(message = "{attr.required}")
    private Long roleId;


}
