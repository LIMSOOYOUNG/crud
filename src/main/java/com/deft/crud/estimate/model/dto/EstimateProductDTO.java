package com.deft.crud.estimate.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EstimateProductDTO implements Serializable {
	
	private int estimateNo;
	private int productNo;
	private int productAmount;

}
