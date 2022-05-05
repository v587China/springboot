package com.ultra.dao;

import com.ultra.entity.DictType;

import java.io.Serializable;
import java.util.Collection;

public interface DictTypeMapper {
    /**
     * 新增
     *
     * @param dictType 实体对象
     * @return 条数
     */
    int add(DictType dictType);

    /**
     * 修改
     *
     * @param dictType 实体对象
     * @return 条数
     */
    int updateById(DictType dictType);

    /**
     * 删除
     *
     * @param id 主键
     * @return 条数
     */
    int deleteById(Serializable id);

    /**
     * 删除
     *
     * @param ids 主键
     * @return 条数
     */
    int deleteByIds(Collection<Serializable> ids);

    /**
     * 查询
     *
     * @param id 主键
     * @return 实体
     */
    DictType getById(Serializable id);

    /**
     * 查询
     *
     * @return 实体集合
     */
    Collection<DictType> getAll();
}
