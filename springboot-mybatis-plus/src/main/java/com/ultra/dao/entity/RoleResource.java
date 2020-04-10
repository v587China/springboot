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
 * 角色资源关系
 * </p>
 *
 * @author ${author}
 * @since 2019-09-06
 */
@Setter
@Getter
@ToString
@TableName("spring_role_resource")
@ApiModel(value = "RoleResource对象", description = "角色资源关系")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色主键")
    @NotNull(message = "{attr.required}")
    private Long roleId;

    @ApiModelProperty(value = "资源主键")
    @NotNull(message = "{attr.required}")
    private Long resourceId;


}
