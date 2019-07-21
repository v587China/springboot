package com.ultra.web;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ultra.common.Result;
import com.ultra.common.ResultBuilder;

public abstract class BaseController<M extends IService<T>, T> {

    protected M baseService;

    /**
     * 查询
     *
     * @param id
     * @return
     */
    protected ResponseEntity<Result> selectById(Serializable id) {
        T t = baseService.selectById(id);
        return ResponseEntity.ok(ResultBuilder.ok(t));
    }

    protected ResponseEntity<Result> selectList(Wrapper<T> wrapper) {
        List<T> list = baseService.selectList(wrapper);
        return ResponseEntity.ok(ResultBuilder.ok(list));
    }

    /**
     * 
     * 分页
     *
     * @param page
     * @return
     */
    protected ResponseEntity<Result> selectPage(Page<T> page) {
        return selectPage(page, null);
    }

    protected ResponseEntity<Result> selectPage(Page<T> page, Wrapper<T> wrapper) {
        page = baseService.selectPage(page, wrapper);
        return ResponseEntity.ok(ResultBuilder.ok(page));
    }

    /**
     * 新增
     * 
     * @param t
     * @return
     */
    public ResponseEntity<Result> insert(T t) {
        baseService.insert(t);
        return ResponseEntity.ok(ResultBuilder.ok());
    }

    protected ResponseEntity<Result> insertBatch(List<T> list) {
        baseService.insertBatch(list);
        return ResponseEntity.ok(ResultBuilder.ok());
    }

    /**
     * 
     * 根据主键修改
     *
     * @param t
     * @return
     */
    protected ResponseEntity<Result> updateById(T t) {
        baseService.updateById(t);
        return ResponseEntity.ok(ResultBuilder.ok());
    }

    protected ResponseEntity<Result> updateBatchById(List<T> list) {
        baseService.updateBatchById(list);
        return ResponseEntity.ok(ResultBuilder.ok());
    }

    /**
     * 
     * 根据主键删除
     *
     * @param id
     * @return
     */
    protected ResponseEntity<Result> deleteById(Serializable id) {
        baseService.deleteById(id);
        return ResponseEntity.ok(ResultBuilder.ok());
    }

    protected ResponseEntity<Result> deleteBatchIds(List<? extends Serializable> ids) {
        if (ids.size() == 1) {
            return deleteById(ids.get(0));
        }
        baseService.deleteBatchIds(ids);
        return ResponseEntity.ok(ResultBuilder.ok());
    }
}