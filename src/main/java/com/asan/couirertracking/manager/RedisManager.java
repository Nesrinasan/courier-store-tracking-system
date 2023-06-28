package com.asan.couirertracking.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class RedisManager {

	private final RedisTemplate<String, Object> redisTemplate;

	public <T>Boolean isNewValueInRedis(T dto, String redisKey, long timeToLive) {
		return redisTemplate.opsForValue().setIfAbsent(redisKey, dto, timeToLive, TimeUnit.SECONDS);
	}

}
