package com.asan.couirertracking.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class KafkaProduceManager {

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${kafka.topic}")
	private String courierTopic;

	public void sendMessage(Object courierStoreInfo) {
		ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send(courierTopic, courierStoreInfo);
		send.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(Throwable ex) {
				log.error("Fail sended log", ex);
			}

			@Override
			public void onSuccess(SendResult<String, Object> result) {
			}
		});
	}

}
