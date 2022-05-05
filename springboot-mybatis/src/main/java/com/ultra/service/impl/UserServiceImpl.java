package com.ultra.service.impl;

import com.ultra.entity.User;
import com.ultra.dao.UserMapper;
import com.ultra.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-09-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
