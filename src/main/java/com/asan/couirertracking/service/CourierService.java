package com.asan.couirertracking.service;

import com.asan.couirertracking.exception.StoreException;
import com.asan.couirertracking.dto.modeldto.LocationDto;
import com.asan.couirertracking.utility.CalculateUtil;
import com.asan.couirertracking.utility.StoresUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {
	private final CourierStoreService courierStoreService;

	public Mono<Double> getTotalTravelDistance(Long courierId) {
		try {
			AtomicReference<Double> totalDistance = new AtomicReference<>(0.0);
			Mono<Map<Integer, String>> mapSortedLatLng = mapSortedCourierStoreByCreateDateReactive(courierId);
			return mapSortedLatLng.flatMap(sortedLatLngEntryKey -> {
				sortedLatLngEntryKey.keySet()
						.stream()
						.forEach(sortedLatLngKey -> {
							String afterLatLng = getAfterLatLng(sortedLatLngEntryKey, sortedLatLngKey);
							if (StringUtils.hasText(afterLatLng)) {
								calculateTotalDistance(totalDistance, sortedLatLngEntryKey.get(sortedLatLngKey), afterLatLng);
							}
						});
				return Mono.just(totalDistance.get());

			});

		}catch (Exception e){
			throw new StoreException("Kuryenin toplam gezintisi hesaplanırken bir hata oluştu.", e);
		}

	}

	private Mono<Map<Integer, String>> mapSortedCourierStoreByCreateDateReactive(Long courierId) {

		AtomicInteger counterKey = new AtomicInteger(1);

		return courierStoreService.findAllCourierStoreByCourierIdReactive(courierId)
				.collectMap(key -> counterKey.getAndIncrement(), value -> value.getLatitude() + "," + value.getLongitude());
	}

	private static String getAfterLatLng(Map<Integer, String> mapSortedLatLng, Integer existKey) {
		String afterLatLng = mapSortedLatLng.get(++existKey);
		return afterLatLng;
	}

	private void calculateTotalDistance(AtomicReference<Double> totalDistance, String latLng, String afterLatLng) {
		LocationDto afterLocation = StoresUtil.splitLocationFunction.apply(afterLatLng, ",");
		LocationDto firstLocation = StoresUtil.splitLocationFunction.apply(latLng, ",");
		double distance = CalculateUtil.calculateDistance(firstLocation.lat(), firstLocation.lng(), afterLocation.lat(), afterLocation.lng());
		totalDistance.set(totalDistance.get() + distance);
	}
}