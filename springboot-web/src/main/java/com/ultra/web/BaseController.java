package com.ultra.web;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.io.Serializable;
import java.util.List;

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
    protected T getById(Serializable id) {
        return baseService.getById(id);
    }

    protected List<T> list(Wrapper<T> wrapper) {
        return baseService.list(wrapper);
    }

    /**
     * 分页
     *
     * @param page
     * @return
     */
    protected IPage<T> page(IPage<T> page) {
        return page(page, null);
    }

    protected IPage<T> page(IPage<T> page, Wrapper<T> wrapper) {
        return baseService.page(page, wrapper);
    }

    /**
     * 新增
     *
     * @param t
     * @return
     */
    public boolean save(T t) {
        return baseService.save(t);
    }

    protected boolean saveBatch(List<T> list) {
        return baseService.saveBatch(list);
    }

    /**
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
     * 根据主键删除
     *
     * @param id
     * @return
     */
    protected boolean removeById(Serializable id) {
        return baseService.removeById(id);
    }

    protected boolean removeByIds(List<? extends Serializable> ids) {
        if (ids.size() == 1) {
            return removeById(ids.get(0));
        }
        return baseService.removeByIds(ids);
    }

    /**
     * 封装搜索条件
     *
     * @param searchColumnS
     * @param search
     * @return
     */
    protected QueryWrapper<T> setSearch(String searchColumnS, String search) {
        QueryWrapper<T> wrapper = null;
        if (StringUtils.isNotBlank(search) && StringUtils.isNotBlank(searchColumnS)) {
            wrapper = new QueryWrapper<>();
            String[] columns = searchColumnS.split(",");
            int length = columns.length;
            for (int i = 0; i < length; i++) {
                wrapper = wrapper.like(columns[i], search);
                if (i < length - 1) {
                    wrapper = wrapper.or();
                }
            }
        }
        return wrapper;
    }
}