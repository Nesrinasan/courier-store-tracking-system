package com.asan.couirertracking.integrationtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yaml")
public class RedisIntegrationTest {

	@Value("${test.data.keyCourierId}")
	private String keyCourierId;

	@Value("${test.data.value}")
	private String value;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Test
	public void redisIntegration_Test() {
		redisTemplate.opsForValue().set(keyCourierId, value);
		Object incomingData = redisTemplate.opsForValue().get(keyCourierId);
		Assertions.assertEquals(value, incomingData);
	}

}
