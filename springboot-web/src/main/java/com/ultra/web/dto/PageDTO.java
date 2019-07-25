package com.ultra.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ApiModel
public class PageDTO {

    @ApiModelProperty(value = "每页显示条数,默认 10")
    private int size = 10;
    @ApiModelProperty(value = "当前页,默认 1")
    private int current = 1;
    @ApiModelProperty(value = "是否为升序(默认:true)")
    private boolean isAsc = true;
    @ApiModelProperty(value = "排序字段")
    private String orderByField;
    @ApiModelProperty(value = "搜索内容")
    private String search;

    void setTField(String order) {
        orderByField = order;
    }
}
