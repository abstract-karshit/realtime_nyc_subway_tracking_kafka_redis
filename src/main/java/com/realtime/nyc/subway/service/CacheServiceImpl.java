package com.realtime.nyc.subway.service;

import java.time.Duration;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final RedisTemplate<String, String> redisTemplate;

    private ValueOperations<String, String> valueOps;

    @PostConstruct
    void init() {
        valueOps = redisTemplate.opsForValue();
    }

    public String getValue(String key) {
        return valueOps.get(key);
    }

    public void setValue(String key, String value) {
        valueOps.set(key, value, Duration.ofMinutes(5L));
    }
}