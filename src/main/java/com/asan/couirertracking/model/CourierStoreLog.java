package com.asan.couirertracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "COURIER_STORE_LOG")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierStoreLog{

	@Id
	private Long idcs;

	@Column
	private String storeName;

	@Column
	private Long courierId;

	@Column
	private Double latitude;

	@Column
	private Double longitude;

	@Column
	@CreatedDate
	private LocalDateTime createDate;


}
