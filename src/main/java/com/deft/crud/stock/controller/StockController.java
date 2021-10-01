package com.deft.crud.stock.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.stock.model.dto.ProductStockInfoDTO;
import com.deft.crud.stock.model.dto.StockDTO;
import com.deft.crud.stock.model.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/stock/*")
public class StockController {

	private final StockService stockService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public StockController(StockService stockService, ObjectMapper objectMapper) {
		this.stockService = stockService;
		this.objectMapper = objectMapper;
	}

	/* 창고보유(재고) 상품목록 조회 */
	@GetMapping("/selectAll")
	public ModelAndView selectStockAll(ModelAndView mv) {
		
		/* 창고에 보유중인 전체 상품 목록*/
		List<StockDTO> stockList = stockService.selectStockAll();
		
		/* 판매가능 상태인 모든 상품들*/
		List<StockDTO> sellAbleProductList = stockService.selectSellAbleProductAll();
		
		mv.addObject("stockList", stockList);
		mv.addObject("sellAbleProductList", sellAbleProductList);
		mv.setViewName("stock/stockList");
		
		return mv;
	}
	
	@GetMapping("/productInfo/selectOne")
	public ModelAndView selectProductInfoByNo(ModelAndView mv, HttpServletResponse response
											, @RequestParam("productNo") int productNo) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		ProductStockInfoDTO productStockInfo = stockService.selectOneProductInfoByNo(productNo);
		
		System.out.println("선택한 상품의 재고관련 정보 : " + productStockInfo);
		
		mv.addObject("productStockInfo", objectMapper.writeValueAsString(productStockInfo));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@PostMapping("/orderStockAmount/modify")
	public ModelAndView modifyStockAmount(ModelAndView mv, HttpServletResponse response
										, @ModelAttribute ProductStockInfoDTO parameters) {
		
		return mv;
	}
	
	
	
}
