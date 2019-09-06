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
@ApiModel(value="UserRole对象", description="用户角色关系")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
        @NotNull(groups = { UpdateGroup.class }, message = "{attr.required}")
    @NotNull(message = "{attr.required}")
    private Long id;

    @ApiModelProperty(value = "用户主键")
    @NotNull(message = "{attr.required}")
    private Long userId;

    @ApiModelProperty(value = "角色主键")
    @NotNull(message = "{attr.required}")
    private Long roleId;


}
