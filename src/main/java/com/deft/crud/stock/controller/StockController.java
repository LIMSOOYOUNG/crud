package com.deft.crud.stock.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.stock.model.dto.ProductStockInfoDTO;
import com.deft.crud.stock.model.dto.RequestStockDTO;
import com.deft.crud.stock.model.dto.StorageDTO;
import com.deft.crud.stock.model.dto.approval.ReceivingReqDTO;
import com.deft.crud.stock.model.dto.approval.ReceivingReqProductDTO;
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
		List<StorageDTO> stockList = stockService.selectStockAll();
		
		/* 판매가능 상태인 모든 상품들*/
		List<StorageDTO> sellableProductList = stockService.selectSellableProductAll();
		
		mv.addObject("stockList", stockList);
		mv.addObject("sellableProductList", sellableProductList);
		mv.setViewName("stock/stockList");
		
		return mv;
	}
	
	/*  */
	@GetMapping("/productInfo/selectOne")
	public ModelAndView selectProductInfoByNo(ModelAndView mv, HttpServletResponse response
											, @RequestParam("productNo") int productNo) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		ProductStockInfoDTO productStockInfo = stockService.selectOneProductInfoByNo(productNo);
		
		mv.addObject("productStockInfo", objectMapper.writeValueAsString(productStockInfo));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@PostMapping("/stockCondition/modify")
	public ModelAndView modifyStockCondition(ModelAndView mv, RedirectAttributes rttr
											, @ModelAttribute RequestStockDTO parameters) {
		
		boolean result = stockService.modifyStockCondition(parameters);
		
		String message = "";
		
		if(result) {
			message = "상품 재고상태를 변경하였습니다.";
		} else {
			message = "재고상태 변경 실패!";
		}
		
		rttr.addFlashAttribute("message", message);
		
		mv.setViewName("redirect:/stock/selectAll");
		return mv;
	}
	
	/* 입고요청서 작성 */
	@PostMapping("receivingProduct/insert")
	public ModelAndView insertStockReceivingProduct(ModelAndView mv, HttpServletResponse response
													, @AuthenticationPrincipal UserImpl userInfo
													, @RequestBody List<RequestStockDTO> request) {
													
		
		response.setContentType("application/json; charset=UTF-8");
		
		boolean result = stockService.insertStockReceivingProduct(request, userInfo);
		
		if (!result) {
			result = false;
		}
		
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		
		return mv;
	}
	
// ----------------------------------요청 목록------------------------------------------------------------------------	
	
	/* 요청서 목록 전체 조회(담당자) */
	@GetMapping("request/selectAll")
	public ModelAndView selectRequestAll(ModelAndView mv) {
		
		List<ReceivingReqDTO> receivingReqList = stockService.selectReceivingReqAll();
		
		mv.addObject("receivingReqList", receivingReqList);
		mv.setViewName("stock/requestList");
		
		return mv;
	}
	
	@GetMapping("request/selectOne")
	public ModelAndView selectReceivingReqByNo(ModelAndView mv, HttpServletResponse response
												, @RequestParam("reqNo") int reqNo) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		ReceivingReqDTO receivingReqInfo = stockService.selectReceivingReqByNo(reqNo);
		
		List<ReceivingReqDTO> receivingReqProductList = stockService.receivingReqProductByReqNo(reqNo);
		
		System.out.println(receivingReqProductList);
		
		mv.addObject("receivingReqInfo", objectMapper.writeValueAsString(receivingReqInfo));
		mv.addObject("receivingReqProductList", objectMapper.writeValueAsString(receivingReqProductList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
}







