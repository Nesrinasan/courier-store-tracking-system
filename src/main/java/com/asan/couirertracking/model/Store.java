package com.asan.couirertracking.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@Setter
public class Store{
	@Id
	private Long storeId;
	@Column
	private String name;

	@Column
	private String latitude;

	@Column
	private String longitude;

}
