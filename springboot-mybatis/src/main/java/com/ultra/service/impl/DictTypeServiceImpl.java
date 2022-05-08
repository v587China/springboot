package com.ultra.service.impl;

import com.ultra.dao.DictTypeMapper;
import com.ultra.entity.DictType;
import com.ultra.service.DictTypeService;
import com.ultra.util.SqlResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Override
    public boolean add(DictType dictType) {
        return SqlResultUtil.single(dictTypeMapper.add(dictType));
    }

    @Override
    public boolean updateById(DictType dictType) {
        return SqlResultUtil.single(dictTypeMapper.updateById(dictType));
    }

    @Override
    public boolean deleteById(Long id) {
        return SqlResultUtil.single(dictTypeMapper.deleteById(id));
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return SqlResultUtil.multiple(dictTypeMapper.deleteByIds(ids), ids.size());
    }

    @Override
    public DictType getById(Long id) {
        return dictTypeMapper.getById(id);
    }

    @Override
    public boolean getNameIsUsable(Long id, String name) {
        return false;
    }

    @Override
    public Collection<DictType> list(DictType dictType) {
        return dictTypeMapper.list(dictType);
    }
}
