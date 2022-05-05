package com.ultra.service.impl;

import com.ultra.entity.DictType;
import com.ultra.service.DictTypeService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Override
    public int add(DictType dictType) {
        return 0;
    }

    @Override
    public int updateById(DictType dictType) {
        return 0;
    }

    @Override
    public int deleteById(Serializable id) {
        return 0;
    }

    @Override
    public int deleteByIds(Collection<Serializable> ids) {
        return 0;
    }

    @Override
    public DictType getById(Serializable id) {
        return null;
    }

    @Override
    public Collection<DictType> getAll() {
        return null;
    }
}
