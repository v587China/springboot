package com.ultra.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel(value="RoleResource对象", description="角色资源关系")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
        @NotNull(groups = { UpdateGroup.class }, message = "{attr.required}")
    @NotNull(message = "{attr.required}")
    private Long id;

    @ApiModelProperty(value = "角色主键")
    @NotNull(message = "{attr.required}")
    private Long roleId;

    @ApiModelProperty(value = "资源主键")
    @NotNull(message = "{attr.required}")
    private Long resourceId;


}
