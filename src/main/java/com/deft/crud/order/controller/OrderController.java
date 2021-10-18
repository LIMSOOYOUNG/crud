package com.deft.crud.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.estimate.model.dto.EstimateDTO;
import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.order.model.dto.OrderDTO;
import com.deft.crud.order.model.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		
		this.orderService = orderService;
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
}
