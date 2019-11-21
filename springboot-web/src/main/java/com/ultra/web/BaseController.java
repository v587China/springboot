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

/**
 * @author admin
 */
public abstract class BaseController<M extends IService<T>, T> {

    @Autowired
    protected M baseService;
    @Autowired
    protected MessageSource messageSource;

    /**
     * 查询
     */
    T getById(Serializable id) {
        return baseService.getById(id);
    }

    List<T> list(Wrapper<T> wrapper) {
        return baseService.list(wrapper);
    }

    /**
     * 分页
     */
    protected IPage<T> page(IPage<T> page) {
        return page(page, null);
    }

    IPage<T> page(IPage<T> page, Wrapper<T> wrapper) {
        return baseService.page(page, wrapper);
    }

    /**
     * 新增
     */
    boolean save(T t) {
        return baseService.save(t);
    }

    boolean saveBatch(List<T> list) {
        return baseService.saveBatch(list);
    }

    /**
     * 根据主键修改
     *
     * @param t
     * @return
     */
    boolean updateById(T t) {
        return baseService.updateById(t);
    }

    boolean updateBatchById(List<T> list) {
        return baseService.updateBatchById(list);
    }

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    private boolean removeById(Serializable id) {
        return baseService.removeById(id);
    }

    boolean removeByIds(List<? extends Serializable> ids) {
        if (ids.size() == 1) {
            return removeById(ids.get(0));
        }
        return baseService.removeByIds(ids);
    }

    /**
     * 封装搜索条件
     *
     * @param searchColumnS 需要搜索表的列名
     * @param search        搜索的内容
     */
    QueryWrapper<T> setSearch(String searchColumnS, String search) {
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