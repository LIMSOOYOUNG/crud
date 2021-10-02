package com.deft.crud.stock.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestStockListDTO implements Serializable {

	private List<RequestStockDTO> RequestStockList;
	
	
}
