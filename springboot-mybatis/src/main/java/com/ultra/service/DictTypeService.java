package com.ultra.service;


import com.ultra.entity.DictType;

import java.util.Collection;
import java.util.List;

public interface DictTypeService {
    /**
     * 新增
     *
     * @param dictType 实体对象
     * @return 条数
     */
    boolean add(DictType dictType);

    /**
     * 根据主键修改
     *
     * @param dictType 实体对象
     * @return 条数
     */
    boolean updateById(DictType dictType);

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return 条数
     */
    boolean deleteById(Long id);

    /**
     * 根据主键删除
     *
     * @param ids 主键
     * @return 条数
     */
    boolean deleteByIds(List<Long> ids);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 实体
     */
    DictType getById(Long id);

    /**
     * 查询名称是否可用
     *
     * @param id   主键
     * @param name 名称
     * @return true/false
     */
    boolean getNameIsUsable(Long id, String name);

    /**
     * 根据条件搜索
     *
     * @param dictType 搜索条件
     * @return 实体集合
     */
    List<DictType> list(DictType dictType);
}
