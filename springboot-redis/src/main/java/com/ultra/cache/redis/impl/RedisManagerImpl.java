package com.ultra.cache.redis.impl;

import com.ultra.cache.redis.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author fan
 */
@Service
public class RedisManagerImpl implements RedisManager {

    @Autowired
    private RedisTemplate redisTemplate;

}
