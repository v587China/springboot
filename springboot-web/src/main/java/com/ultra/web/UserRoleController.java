package com.ultra.web;


import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import com.ultra.web.BaseController;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ultra.validated.UpdateGroup;
import com.ultra.dao.entity.UserRole;
import com.ultra.service.UserRoleService;

/**
 * <p>
 * 用户角色关系 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-09-06
 */
@Api(tags = {"用户角色关系"})
@RestController
@RequestMapping("/userRole")
public class UserRoleController extends BaseController<UserRoleService, UserRole> {
    private static final String SEARCH_COLUMNS = "";

    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @ApiOperation(value = "查询全部")
    @GetMapping("/list")
    public List<User> list() {
        return super.list(null);
    }

    @ApiOperation(value = "分页搜索查询")
    @GetMapping("/page")
    public IPage<User> page(@RequestParam(defaultValue = "1", required = false) int current,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(required = false) String[] descs, @RequestParam(required = false) String[] ascs,
            @RequestParam(required = false) String search) {
        Page<User> page = new Page<User>(current, size);
        page.setAsc(ascs);
        page.setDesc(descs);
        QueryWrapper<UserRole> wrapper = null;
        if (StringUtils.isNotBlank(search)) {
            wrapper = setSearch(SEARCH_COLUMNS, search);
        }
        return super.page(page, wrapper);
    }

    @ApiOperation(value = "添加")
    @PostMapping
    public boolean save(@Validated @RequestBody UserRole entity, Errors errors) {
        return super.save(entity);
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public boolean updateById(@Validated({ UpdateGroup.class }) @RequestBody UserRole entity, Errors errors) {
        return super.updateById(entity);
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping
    public boolean removeIds(@RequestParam List<Long> ids) {
        Assert.notEmpty(ids, "ids can't be empty.");
        return super.removeByIds(ids);
    }
}

