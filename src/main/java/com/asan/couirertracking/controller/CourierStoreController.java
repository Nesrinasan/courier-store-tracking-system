package com.asan.couirertracking.controller;

import com.asan.couirertracking.dto.requestdto.CourierStoreSaveRequestDto;
import com.asan.couirertracking.service.CourierStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/courierStore")
@RequiredArgsConstructor
public class CourierStoreController {
	private final CourierStoreService courierService;

	/**
	 * This API send a message to kafka to log the distance of courier between store.
	 * @param courierSaveRequestDto
	 * @return
	 */
	@PostMapping("/eventReport")
	public ResponseEntity<Void> eventReport(@RequestBody CourierStoreSaveRequestDto courierSaveRequestDto){
		courierService.sendEventCourierStoreInfoToKafka(courierSaveRequestDto);
		return new ResponseEntity(HttpStatus.OK);

	}


}
