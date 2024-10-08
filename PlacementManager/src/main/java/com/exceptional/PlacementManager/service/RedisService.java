package com.exceptional.PlacementManager.service;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {


    private final RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value, long ttl) {
        redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.MINUTES);
    }

    public <T> T get(String key, Class<T> entityClass) {
        Object value = redisTemplate.opsForValue().get(key);
        return entityClass.cast(value);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

