package com.deft.crud.order.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.customer.model.dto.ExtCustomerDTO;
import com.deft.crud.customer.model.service.CustomerService;
import com.deft.crud.estimate.model.dto.EstimateDTO;
import com.deft.crud.estimate.model.service.EstimateService;
import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.order.model.dto.OrderDTO;
import com.deft.crud.order.model.service.OrderService;
import com.deft.crud.stock.model.dto.StorageDTO;
import com.deft.crud.stock.model.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;
	private final EstimateService estimateService;
	private final CustomerService customerService;
	private final StockService stockService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public OrderController(OrderService orderService, CustomerService customerService,
			EstimateService estimateService, StockService stockService, ObjectMapper objectMapper) {
		
		this.orderService = orderService;
		this.estimateService = estimateService;
		this.customerService = customerService;
		this.stockService = stockService;
		this.objectMapper = objectMapper;
	}
	
	@GetMapping("/selectAll")
	public ModelAndView selectOrderList(ModelAndView mv, @AuthenticationPrincipal UserImpl userInfo) {
		
		int empNo = userInfo.getEmpNo();
		
		List<OrderDTO> orderList = orderService.selectOrderList(empNo);
		
		mv.addObject("orderList", orderList);
		mv.setViewName("order/selectAllOrder");
		
		return mv;
	}
	
	@GetMapping("/select")
	public ModelAndView selectOrderDetail(ModelAndView mv, @RequestParam String orderNo) {
		
		OrderDTO order = orderService.selectOrderDetail(orderNo);
		
		mv.addObject("order", order);
		mv.setViewName("order/selectOrder");
		
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView insertOrder(ModelAndView mv, @AuthenticationPrincipal UserImpl userInfo) {
		
		/* 새로운 주문번호 및 일자 입력 */
		OrderDTO order = new OrderDTO();
		LocalDate newOrderLocalDate = LocalDate.now();
		String newOrderDate = newOrderLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		String newOrderNo = orderService.selectOrderNo(newOrderDate);
		
		order.setOrderNo(newOrderNo);
		order.setOrderDate(newOrderDate);
		
		/* 견적서 불러오기(진행중 또는 보류) */
		String estimateStatus = "progress";
		int empNo = userInfo.getEmpNo();
		List<EstimateDTO> estimateList = estimateService.selectEstimateListByStatus(estimateStatus, empNo);
		
		/* 기존 고객 조회 (해지 고객 제외) */
		List<ExtCustomerDTO> extCustomerList = customerService.selectExtCustomerList();
		
		/* 상품 목록 조회 */
		List<StorageDTO> stockList = stockService.selectSellableProductAll();
		
		for(EstimateDTO estimate : estimateList) {
			System.out.println(estimate);
		}
		
		mv.addObject("order", order);
		mv.addObject("estimateList", estimateList);
		mv.addObject("customerList", extCustomerList);
		mv.addObject("stockList", stockList);
		mv.setViewName("order/insertOrder");
		
		return mv;
	}
	
	@GetMapping("/estimate/select")
	public ModelAndView selectEstimateDetail(ModelAndView mv, @RequestParam String estimateNo) throws JsonProcessingException {
		
		EstimateDTO estimate = estimateService.selectEstimateDetail(estimateNo);
		
		System.out.println(estimate);
		
		mv.addObject("estimate", objectMapper.writeValueAsString(estimate));
		mv.setViewName("jsonView");
		
		return mv;
	}
}
