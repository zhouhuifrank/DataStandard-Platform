package com.frankzhou.datastandard.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: this.FrankZhou
 * @date: 2022/1/2
 * @description: 封装stringRedisTemplate相关操作
 */
@Slf4j
@Component
public class StringRedisCache {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
}
