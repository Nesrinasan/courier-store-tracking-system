package com.asan.couirertracking.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@Setter
public class Courier{

	@Id
	private Long idCourier;

	@Column
	private String name;

}
