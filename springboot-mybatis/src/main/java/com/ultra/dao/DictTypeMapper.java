package com.ultra.dao;

import com.ultra.entity.DictType;

import java.util.Collection;
import java.util.List;

public interface DictTypeMapper {
    /**
     * 新增
     *
     * @param dictType 实体对象
     * @return 条数
     */
    int add(DictType dictType);

    /**
     * 根据主键修改
     *
     * @param dictType 实体对象
     * @return 条数
     */
    int updateById(DictType dictType);

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return 条数
     */
    int deleteById(Long id);

    /**
     * 根据主键删除
     *
     * @param ids 主键
     * @return 条数
     */
    int deleteByIds(List<Long> ids);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 实体
     */
    DictType getById(Long id);

    /**
     * 根据条件搜索
     *
     * @param dictType 搜索条件
     * @return 实体集合
     */
    Collection<DictType> list(DictType dictType);
}
