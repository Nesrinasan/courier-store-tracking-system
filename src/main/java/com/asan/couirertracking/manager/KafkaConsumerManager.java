package com.asan.couirertracking.manager;

import com.asan.couirertracking.dto.requestdto.CourierStoreSaveRequestDto;
import com.asan.couirertracking.service.CourierStoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaConsumerManager {

	private final ObjectMapper objectMapper;
	private final CourierStoreService courierStoreService;

	@KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}")
	public void listenCourierStoreTopic(String courierStoreDto) {
		try {
			CourierStoreSaveRequestDto courierStoreSaveRequestDto = objectMapper.readValue(courierStoreDto, CourierStoreSaveRequestDto.class);
			courierStoreService.sendMessageToLogReport(courierStoreSaveRequestDto);

		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}


	}


}
