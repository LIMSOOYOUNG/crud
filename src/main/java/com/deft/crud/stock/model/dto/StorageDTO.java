package com.deft.crud.stock.model.dto;

import java.io.Serializable;

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
public class StorageDTO implements Serializable {

	private String storageSection;
	private String storageSpace;
	private int productStock;
	private int productNo;
	
}


