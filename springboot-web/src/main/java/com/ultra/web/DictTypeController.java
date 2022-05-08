package com.ultra.web;


import com.ultra.common.Result;
import com.ultra.entity.DictType;
import com.ultra.service.DictTypeService;
import com.ultra.validated.InsertGroup;
import com.ultra.validated.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * 字典类型
 *
 * @author admin
 * @since 2019-09-06
 */
@Api(tags = {"字典类型"})
@RestController
@RequestMapping("/dictType")
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return Result.ok(dictTypeService.getById(id));
    }

    @ApiOperation(value = "查询名称是否可用")
    @GetMapping("/nameIsUsable")
    public Result getNameIsUsable(@RequestParam(required = false) Long id, @RequestParam String name) {
        return Result.ok(dictTypeService.getNameIsUsable(id, name));
    }

    @ApiOperation(value = "查询全部")
    @GetMapping("/list")
    public Result list() {
        return Result.ok(dictTypeService.list(null));
    }

//    @ApiOperation(value = "分页搜索查询")
//    @GetMapping("/page")
//    public IPage<DictType> page(@RequestParam(defaultValue = "1", required = false) int current,
//                                @RequestParam(defaultValue = "10", required = false) int size,
//                                @RequestParam(required = false) String[] descs, @RequestParam(required = false) String[] ascs,
//                                @RequestParam(required = false) String search) {
//        Page<DictType> page = new Page<>(current, size);
//        page.setAsc(ascs);
//        page.setDesc(descs);
//        QueryWrapper<User> wrapper = null;
//        if (StringUtils.isNotBlank(search)) {
//            wrapper = setSearch(SEARCH_COLUMNS, search);
//        }
//        return super.page(page, wrapper);
//    }

    @ApiOperation(value = "添加")
    @PostMapping
    public Result add(@Validated({InsertGroup.class}) @RequestBody DictType dictType, Errors errors) {
        dictType.setGmtCreate(new Date());
        dictType.setGmtModified(dictType.getGmtCreate());
        return Result.ok(dictTypeService.add(dictType));
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public Result updateById(@Validated({UpdateGroup.class}) @RequestBody DictType dictType, Errors errors) {
        dictType.setGmtModified(new Date());
        return Result.ok(dictTypeService.updateById(dictType));
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping
    public Result deleteByIds(@RequestParam List<Long> ids) {
        Assert.notEmpty(ids, "ids 不能为空。");
        if (ids.size() == 1) {
            return Result.ok(dictTypeService.deleteById(ids.get(0)));
        }
        return Result.ok(dictTypeService.deleteByIds(ids));
    }

}

