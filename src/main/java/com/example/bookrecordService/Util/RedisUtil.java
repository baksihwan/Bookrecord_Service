package com.example.bookrecordService.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public <T> Optional<T> get(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            return Optional.of((T) value);
        }
        return Optional.empty();
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(10)); // TTL 설정
    }
}
