package com.deft.crud.stock.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.stock.model.dao.StockMapper;
import com.deft.crud.stock.model.dto.StockDTO;
import com.deft.crud.stock.model.dto.StorageDTO;

@Service
public class StockService {

	private StockMapper mapper;
	
	@Autowired
	public StockService(StockMapper mapper) {
		this.mapper = mapper;
	}


	public List<StorageDTO> storageSelectAll() {
		List<StorageDTO> storageList = mapper.storageSelectAll();
		
		return storageList;
	}



	public List<StockDTO> prodcutSelectByNo(int productNo) {
		List<StockDTO> productList = mapper.prodcutSelectByNo(productNo);
		
		return productList;
	}

}
