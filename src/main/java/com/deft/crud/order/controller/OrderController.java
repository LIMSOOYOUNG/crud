package com.deft.crud.order.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		
		/* 진행 중인 견적서 불러오기 */
		String estimateStatus = "progress";
		int empNo = userInfo.getEmpNo();
		
		List<EstimateDTO> estimateList = estimateService.selectEstimateListByStatus(estimateStatus, empNo);
		
		/* 기존 고객 조회 (해지 고객 제외) */
		List<ExtCustomerDTO> extCustomerList = customerService.selectExtCustomerList();
		
		/* 상품 목록 조회 */
		List<StorageDTO> stockList = stockService.selectSellableProductAll();
		
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
		
		mv.addObject("estimate", objectMapper.writeValueAsString(estimate));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@PostMapping("/insert")
	public ModelAndView insertOrder(ModelAndView mv, HttpServletResponse response,
									@RequestBody OrderDTO orderInfo) {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int result = orderService.insertOrder(orderInfo);
		String message = "";
		
		if(result > 0) {
			message = "주문서가 등록되었습니다.";
		} else {
			message = "주문서 등록에 실패하였습니다.";
		}
		
		mv.addObject("message", message);
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@GetMapping("/modify")
	public ModelAndView modifyOrder(ModelAndView mv, @RequestParam String orderNo) {
		
		/* 주문서 정보 조회 */
		OrderDTO order = orderService.selectOrderDetail(orderNo);
		
		/* 고객 목록 조회 */
		List<ExtCustomerDTO> extCustomerList = customerService.selectExtCustomerList();
		
		/* 상품 목록 조회 */
		List<StorageDTO> stockList = stockService.selectSellableProductAll();
		
		mv.addObject("order", order);
		mv.addObject("customerList", extCustomerList);
		mv.addObject("stockList", stockList);
		mv.setViewName("order/modifyOrder");
		
		return mv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modifyEstimate(ModelAndView mv, HttpServletResponse response,
									@RequestBody OrderDTO orderInfo) {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int result = orderService.modifyOrder(orderInfo);
		String message = "";
		
		if(result > 0) {
			message = "주문서가 수정되었습니다.";
		} else {
			message = "주문서 수정에 실패하였습니다.";
		}
		
		mv.addObject("message", message);
		mv.setViewName("jsonView");
		
		return mv;
	}
}
