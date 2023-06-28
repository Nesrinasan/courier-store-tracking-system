package com.asan.couirertracking.service;

import com.asan.couirertracking.dto.requestdto.CourierStoreSaveRequestDto;
import com.asan.couirertracking.dto.modeldto.LocationDto;
import com.asan.couirertracking.manager.KafkaProduceManager;
import com.asan.couirertracking.manager.RedisManager;
import com.asan.couirertracking.model.CourierStoreLog;
import com.asan.couirertracking.repository.CourierReactiveStoreRepository;
import com.asan.couirertracking.utility.CalculateUtil;
import com.asan.couirertracking.utility.StoresUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierStoreService {
	private final RedisManager redisManager;
	private final KafkaProduceManager kafkaProduceManager;
	private final CourierReactiveStoreRepository courierReactiveStoreRepository;
	@Value(value = "${redis.courier-arround-store.ttl}")
	private int redisCouierAroundStoreTTL;
	public void sendEventCourierStoreInfoToKafka(CourierStoreSaveRequestDto courierStoreSaveRequestDto){
		kafkaProduceManager.sendMessage(courierStoreSaveRequestDto);
	}

	/**
	 * This method sends a message to queue.
	 * @param courierStoreSaveRequestDto
	 */
	public void sendMessageToLogReport(CourierStoreSaveRequestDto courierStoreSaveRequestDto) {
		LocationDto locationDto = courierStoreSaveRequestDto.locationDto();
		Long courierId = courierStoreSaveRequestDto.courierId();
		AtomicReference<String> storeName = new AtomicReference<>("");
		try {
			boolean isCourierAroundStore = hasStoreBetweenCouirerLocation(locationDto, storeName);
			if (isCourierAroundStore) {
				boolean addValueRedis = redisManager.isNewValueInRedis(courierStoreSaveRequestDto, String.valueOf(courierId), redisCouierAroundStoreTTL);
				if (addValueRedis) {
					CourierStoreLog courierStoreLog = CourierStoreLog.builder().latitude(locationDto.lat()).longitude(locationDto.lng()).courierId(courierId).storeName(storeName.get()).build();
					courierReactiveStoreRepository.save(courierStoreLog);
				}
			}
		} catch (Exception e) {
			log.error("Kurye ile store ilişki kaydı oluşturulurken bir hata oluştu.", e);
		}
	}

	private boolean hasStoreBetweenCouirerLocation(LocationDto couirerLocationDto, AtomicReference<String> storeName) {
		return StoresUtil.storeDtoList.stream()
				.anyMatch(storeDtoInner -> {
					double calculateDistance = CalculateUtil.calculateDistance(couirerLocationDto.lat(), couirerLocationDto.lng(), storeDtoInner.locationDto().lat(), storeDtoInner.locationDto().lng());
					if (calculateDistance <= 1000) {
						storeName.set(storeDtoInner.name());
						return true;
					}
					return false;
				});
	}

	public Flux<CourierStoreLog> findAllCourierStoreByCourierIdReactive(Long courierId) {
		return courierReactiveStoreRepository.findAllDistinctByCourierIdOrderByCreateDateDesc(courierId);
	}

}
