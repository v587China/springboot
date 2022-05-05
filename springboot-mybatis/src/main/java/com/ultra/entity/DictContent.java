package com.ultra.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DictContent {

    /**
     * 主键
     */
    private Long id;
    /**
     * 字典类型主键
     */
    private Long dictTypeId;
    /**
     * 名称
     */
    private String name;
    /**
     * 逻辑主键（业务需要、可以为空）
     */
    private String logic_id;
    /**
     * 预留字段
     */
    private String memo;
    /**
     * 预留字段2
     */
    private String memo2;
}
