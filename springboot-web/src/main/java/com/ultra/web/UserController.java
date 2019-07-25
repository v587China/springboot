package com.ultra.web;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ultra.dao.entity.User;
import com.ultra.service.UserService;
import com.ultra.util.ValidUtil;
import com.ultra.validated.UpdateGroup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "用户表" })
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserService, User> {

    @ApiOperation(value = "添加")
    @PostMapping
    public boolean insert(@Validated @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(ValidUtil.getIllegalMess(errors, messageSource));
        }
        return super.insert(user);
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public boolean updateById(@Validated({ UpdateGroup.class }) @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(ValidUtil.getIllegalMess(errors, messageSource));
        }
        return super.updateById(user);
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping
    // TODO 长度校验
    protected boolean deleteBatchIds(@RequestParam List<Long> ids) {
        return super.deleteBatchSeriIds(ids);
    }

}
