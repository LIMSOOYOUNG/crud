package com.deft.crud.stock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.stock.model.dto.StockDTO;
import com.deft.crud.stock.model.dto.StorageDTO;
import com.deft.crud.stock.model.service.StockService;


@Controller
@RequestMapping("/stock/*")
public class StockController {

	private final StockService stockService;
	
	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@GetMapping("/selectAll")
	public ModelAndView selectStockAll(ModelAndView mv) {
		
		
		List<StorageDTO> storageList = new ArrayList<>();
		
		storageList = stockService.storageSelectAll();		//창고보유(재고) 상품목록
		
		List<StockDTO> stockList = new ArrayList<>();
		StockDTO stockDTO = new StockDTO();					
		
		for(StorageDTO storage: storageList) {
			stockDTO = stockService.stockSelectAll(storage.getProductNo());	  //창고에 보유중인 상품의 상품번호로 재고상품 정보 조회
			
			stockList.add(stockDTO);		//ModelAndView에 담아 view페이지로 넘겨줄 재고상품정보 리스트에 위에서 조회한 상품들을 for문을 사용해 넣어줌 
		}
		
		mv.addObject("stockList", stockList);
		mv.setViewName("stock/stockList");
		
		return mv;
	}
	
	
}
