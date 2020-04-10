package com.ultra.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ultra.bo.CasUser;
import com.ultra.dao.entity.User;
import com.ultra.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * @author admin
 */
@RestController
@RequestMapping("/sso")
public class SsoController {

    private static final String USERNAME_KEY = "username";
    @Autowired
    private UserService userService;

    @PostMapping("/cas")
    public Object login(@RequestHeader HttpHeaders httpHeaders) {

        CasUser casUser = getUserFormHeader(httpHeaders);
        //当没有 传递 参数的情况
        if (casUser == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        //尝试查找用户库是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(USERNAME_KEY, casUser.getUsername());
        User user = userService.getOne(wrapper);
        if (user != null) {
            if (!user.getPassword().equals(casUser.getPassword())) {
                //密码不匹配
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            if (user.getDisabled()) {
                //禁用 403
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
            if (user.getLocked()) {
                //锁定 423
                return new ResponseEntity(HttpStatus.LOCKED);
            }
            if (user.getExpired()) {
                //过期 428
                return new ResponseEntity(HttpStatus.PRECONDITION_REQUIRED);
            }
            //成功
            casUser.setId(user.getId());
            return casUser;
        } else {
            //不存在 404
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 根据请求头获取用户名及密码
     *
     * @param httpHeaders 请求头
     * @return 用户
     */
    private CasUser getUserFormHeader(HttpHeaders httpHeaders) {
        //根据官方文档，当请求过来时，会通过把用户信息放在请求头authorization中，并且通过Basic认证方式加密
        //将得到 Basic Base64(用户名:密码)
        String authorization = httpHeaders.getFirst("authorization");
        if (StringUtils.isEmpty(authorization)) {
            return null;
        }
        String baseCredentials = authorization.split(" ")[1];
        //用户名:密码
        String usernamePassword = new String(Base64Utils.decodeFromString(baseCredentials), StandardCharsets.UTF_8);
        String[] credentials = usernamePassword.split(":");
        return new CasUser(credentials[0], credentials[1]);
    }

}
