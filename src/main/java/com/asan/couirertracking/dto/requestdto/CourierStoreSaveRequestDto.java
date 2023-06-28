package com.asan.couirertracking.dto.requestdto;

import com.asan.couirertracking.dto.modeldto.LocationDto;

public record CourierStoreSaveRequestDto(Long courierId, LocationDto locationDto) {
}
