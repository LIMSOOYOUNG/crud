package com.deft.crud.stock.model.service;

import java.util.ArrayList;
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

	public List<StockDTO> selectStockAll() {
		
		List<StorageDTO> storageList = mapper.selectStorageAll();
		
		StockDTO stockDTO = new StockDTO();	
		List<StockDTO> stockList = new ArrayList<>();
		
		for(StorageDTO storage: storageList) {
			stockDTO = mapper.selectStockAll(storage.getProductNo());	  //창고에 보유중인 상품의 상품번호로 재고상품 정보 조회
			
			stockList.add(stockDTO);		//ModelAndView에 담아 view페이지로 넘겨줄 재고상품정보 리스트에 위에서 조회한 상품들을 for문을 사용해 넣어줌 
		}
		
		return stockList;
	}



}
