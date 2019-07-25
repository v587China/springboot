package com.ultra.web;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

public abstract class BaseController<M extends IService<T>, T> {

    @Autowired
    protected M baseService;
    @Autowired
    protected MessageSource messageSource;

    /**
     * 查询
     *
     * @param id
     * @return
     */
    protected T selectById(Serializable id) {
        return baseService.selectById(id);
    }

    protected List<T> selectList(Wrapper<T> wrapper) {
        return baseService.selectList(wrapper);
    }

    /**
     * 
     * 分页
     *
     * @param page
     * @return
     */
    protected Page<T> selectPage(Page<T> page) {
        return selectPage(page, null);
    }

    protected Page<T> selectPage(Page<T> page, Wrapper<T> wrapper) {
        return baseService.selectPage(page, wrapper);
    }

    /**
     * 新增
     * 
     * @param t
     * @return
     */
    public boolean insert(T t) {
        return baseService.insert(t);
    }

    protected boolean insertBatch(List<T> list) {
        return baseService.insertBatch(list);
    }

    /**
     * 
     * 根据主键修改
     *
     * @param t
     * @return
     */
    protected boolean updateById(T t) {
        return baseService.updateById(t);
    }

    protected boolean updateBatchById(List<T> list) {
        return baseService.updateBatchById(list);
    }

    /**
     * 
     * 根据主键删除
     *
     * @param id
     * @return
     */
    protected boolean deleteById(Serializable id) {
        return baseService.deleteById(id);
    }

    protected boolean deleteBatchSeriIds(List<? extends Serializable> ids) {
        if (ids.size() == 1) {
            return deleteById(ids.get(0));
        }
        return baseService.deleteBatchIds(ids);
    }
}