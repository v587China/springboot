package com.ultra.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ultra.validated.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author ${author}
 * @since 2019-09-06
 */
@Setter
@Getter
@ToString
@TableName("spring_resource")
@ApiModel(value = "Resource对象", description = "")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @NotNull(groups = {UpdateGroup.class}, message = "{attr.required}")
    @NotNull(message = "{attr.required}")
    private Long id;

    @ApiModelProperty(value = "资源名称")
    @NotBlank(message = "{attr.required}")
//    @Length(min = LengthValidatedConstant.STR_MIN_LENGTH, max = LengthValidatedConstant.STR_MAX_LENGTH, message = "{attr.range.length}")
//    @Pattern(regexp = RegexpConstant.NAME_REGEXP, message = "{attr.illegal}")
    private String name;

    @ApiModelProperty(value = "url")
    @NotNull(message = "{attr.required}")
    private byte[] url;


}
