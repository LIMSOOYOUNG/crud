package com.deft.crud.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.stock.model.dto.StockDTO;
import com.deft.crud.stock.model.service.StockService;


@Controller
@RequestMapping("/stock/*")
public class StockController {

	private final StockService stockService;
	
	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	/* 창고보유(재고) 상품목록 조회 */
	@GetMapping("/selectAll")
	public ModelAndView selectStockAll(ModelAndView mv) {
		
		List<StockDTO> stockList = stockService.selectStockAll();		
		
		mv.addObject("stockList", stockList);
		mv.setViewName("stock/stockList");
		
		return mv;
	}
	
	
}
