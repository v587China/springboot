package com.ultra.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ultra.common.Result;
import com.ultra.dao.entity.User;
import com.ultra.service.UserService;
import com.ultra.validated.InsertGroup;
import com.ultra.validated.UpdateGroup;

import io.swagger.annotations.Api;

@Api(tags = { "用户表" })
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserService, User> {

    @PostMapping
    public ResponseEntity<Result> insert(@Validated({ InsertGroup.class }) @RequestBody User user, Errors errors) {
        return super.insert(user);
    }

    @PutMapping
    public ResponseEntity<Result> updateById(@Validated({ UpdateGroup.class }) @RequestBody User user, Errors errors) {
        return super.updateById(user);
    }

    @DeleteMapping
    protected ResponseEntity<Result> deleteBatchIds(@RequestParam List<Long> ids, Errors errors) {
        return super.deleteBatchIds(ids);
    }

}
