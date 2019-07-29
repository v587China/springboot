package com.ultra.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ultra.dao.entity.User;
import com.ultra.service.UserService;
import com.ultra.validated.UpdateGroup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "用户" })
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserService, User> {

    private static final String SEARCH_COLUMNS = "";

    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public User selectById(@PathVariable Long id) {
        return super.selectById(id);
    }

    @ApiOperation(value = "查询全部")
    @GetMapping("/list")
    public List<User> selectList() {
        return super.selectList(null);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "分页搜索查询")
    @GetMapping("/page")
    public Page<User> selectPage(@RequestParam(defaultValue = "1", required = false) int current,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(value = "order", required = false) String orderByField,
            @RequestParam(defaultValue = "true", required = false) boolean isAsc,
            @RequestParam(required = false) String search) {
        Page<User> page = new Page<User>(current, size, orderByField, isAsc);
        Wrapper<User> wrapper = null;
        if (StringUtils.isNotBlank(search)) {
            wrapper = (Wrapper<User>) setSearch(SEARCH_COLUMNS, search);
        }
        return super.selectPage(page, wrapper);
    }

    @ApiOperation(value = "添加")
    @PostMapping
    public boolean insert(@Validated @RequestBody User user, Errors errors) {
        return super.insert(user);
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public boolean updateById(@Validated({ UpdateGroup.class }) @RequestBody User user, Errors errors) {
        return super.updateById(user);
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping
    public boolean deleteBatchIds(@RequestParam List<Long> ids) {
        Assert.notEmpty(ids, "ids can't be empty.");
        return super.deleteBatchSeriIds(ids);
    }

}
