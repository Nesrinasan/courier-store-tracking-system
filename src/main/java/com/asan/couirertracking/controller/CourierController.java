package com.asan.couirertracking.controller;

import com.asan.couirertracking.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/courier")
@RequiredArgsConstructor
public class CourierController {
	private final CourierService courierService;

	/**
	 * This API calculate the total way traveled of courier
	 * @param courierId
	 * @return total distance
	 */
	@GetMapping("/totalDistance")
	public Mono<ResponseEntity<Double>> totalDistance(@RequestParam Long courierId){
		return courierService.getTotalTravelDistance(courierId).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

}
