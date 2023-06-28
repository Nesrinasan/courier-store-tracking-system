package com.asan.couirertracking.repository;

import com.asan.couirertracking.model.CourierStoreLog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CourierReactiveStoreRepository extends ReactiveCrudRepository<CourierStoreLog, Long> {

	Flux<CourierStoreLog> findAllDistinctByCourierIdOrderByCreateDateDesc(Long couierId);


}
