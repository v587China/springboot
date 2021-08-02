package com.ultra.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ultra.common.Result;
import com.ultra.dao.entity.RoleResource;
import com.ultra.service.RoleResourceService;
import com.ultra.validated.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 角色资源关系 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-09-06
 */
@Api(tags = {"角色资源关系"})
@RestController
@RequestMapping("/roleResource")
public class RoleResourceController extends BaseController<RoleResourceService, RoleResource> {
    private static final String SEARCH_COLUMNS = "";

    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public RoleResource getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @ApiOperation(value = "查询全部")
    @GetMapping("/list")
    public List<RoleResource> list() {
        return super.list(null);
    }

    @ApiOperation(value = "分页搜索查询")
    @GetMapping("/page")
    public IPage<RoleResource> page(@RequestParam(defaultValue = "1", required = false) int current,
                                    @RequestParam(defaultValue = "10", required = false) int size,
                                    @RequestParam(required = false) String[] descs, @RequestParam(required = false) String[] ascs,
                                    @RequestParam(required = false) String search) {
        Page<RoleResource> page = new Page<RoleResource>(current, size);
        page.setAsc(ascs);
        page.setDesc(descs);
        QueryWrapper<RoleResource> wrapper = null;
        if (StringUtils.isNotBlank(search)) {
            wrapper = setSearch(SEARCH_COLUMNS, search);
        }
        return super.page(page, wrapper);
    }

    @ApiOperation(value = "添加")
    @PostMapping
    public Result save(@Validated @RequestBody RoleResource entity, Errors errors) {
        return super.save(entity);
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public Result updateById(@Validated({UpdateGroup.class}) @RequestBody RoleResource entity, Errors errors) {
        return super.updateById(entity);
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping
    public Result removeIds(@RequestParam List<Long> ids) {
        Assert.notEmpty(ids, "ids can't be empty.");
        return super.removeByIds(ids);
    }
}

