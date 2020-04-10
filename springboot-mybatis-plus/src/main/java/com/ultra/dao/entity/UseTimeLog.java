package com.ultra.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author ${author}
 * @since 2020-04-10
 */
@Setter
@Getter
@ToString
@TableName("spring_use_time_log")
@ApiModel(value = "UseTimeLog对象", description = "")
public class UseTimeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "父id")
    private Long pId;

    @ApiModelProperty(value = "类名")
    private String className;

    @ApiModelProperty(value = "方法名")
    private String methodName;

    @ApiModelProperty(value = "修饰符")
    private Integer modifiers;

    @ApiModelProperty(value = "方法参数")
    private String parameters;

    @ApiModelProperty(value = "返回值类型")
    private String returnType;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "耗时(毫秒)")
    private Integer useTime;


}
