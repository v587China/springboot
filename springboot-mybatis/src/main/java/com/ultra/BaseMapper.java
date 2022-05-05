package com.ultra;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基本接口
 */
public interface BaseMapper<Entity> {
    /**
     * 新增
     *
     * @param entity 实体对象
     * @return 条数
     */
    int add(Entity entity);

    /**
     * 修改
     *
     * @param entity 实体对象
     * @return 条数
     */
    int updateById(Entity entity);

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
    Entity getById(Serializable id);

    /**
     * 查询
     *
     * @return 实体集合
     */
    Collection<Entity> getAll();

}
