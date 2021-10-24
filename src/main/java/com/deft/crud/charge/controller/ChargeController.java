package com.deft.crud.charge.controller;

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

import com.deft.crud.charge.model.dto.ChargeDTO;
import com.deft.crud.charge.model.service.ChargeService;
import com.deft.crud.estimate.model.dto.EstimateDTO;
import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.order.model.dto.OrderDTO;
import com.deft.crud.order.model.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/charge")
public class ChargeController {
	
	private final ChargeService chargeService;
	private final OrderService orderService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public ChargeController(ChargeService chargeService, OrderService orderService, ObjectMapper objectMapper) {
		
		this.chargeService = chargeService;
		this.orderService = orderService;
		this.objectMapper = objectMapper;
	}
	
	@GetMapping("/selectAll")
	public ModelAndView selectChargeList(ModelAndView mv, @AuthenticationPrincipal UserImpl userInfo) {
		
		int empNo = userInfo.getEmpNo();
		
		List<ChargeDTO> chargeList = chargeService.selectChargeList(empNo);
		
		mv.addObject("chargeList", chargeList);
		mv.setViewName("charge/selectAllcharge");
		
		return mv;
	}
	
	@GetMapping("/select")
	public ModelAndView selectChargeDetail(ModelAndView mv, @RequestParam String chargeNo) {
		
		ChargeDTO charge = chargeService.selectChargeDetail(chargeNo);
		
		mv.addObject("charge", charge);
		mv.setViewName("charge/selectCharge");
		
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView insertCharge(ModelAndView mv, @AuthenticationPrincipal UserImpl userInfo) {
		
		/* 새로운 청구번호 및 일자 입력 */
		ChargeDTO charge = new ChargeDTO();
		LocalDate newChargeLocalDate = LocalDate.now();
		String newChargeDate = newChargeLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		String newChargeNo = chargeService.selectChargeNo(newChargeDate);
		
		charge.setChargeNo(newChargeNo);
		charge.setChargeDate(newChargeDate);
		
		/* 결재 완료된 주문서 불러오기 */
		int empNo = userInfo.getEmpNo();
		
		List<OrderDTO> orderList = orderService.selectApprovedOrderList(empNo);
		
		mv.addObject("charge", charge);
		mv.addObject("orderList", orderList);
		mv.setViewName("charge/insertCharge");
		
		return mv;
	}

	@GetMapping("/order/select")
	public ModelAndView selectOrderDetail(ModelAndView mv, @RequestParam String orderNo) throws JsonProcessingException {
		
		OrderDTO order = orderService.selectOrderDetail(orderNo);
		
		mv.addObject("order", objectMapper.writeValueAsString(order));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@PostMapping("/insert")
	public ModelAndView insertCharge(ModelAndView mv, HttpServletResponse response,
									@RequestBody ChargeDTO chargeInfo) {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int result = chargeService.insertCharge(chargeInfo);
		String message = "";
		
		if(result > 0) {
			message = "청구서가 등록되었습니다.";
		} else {
			message = "청구서 등록에 실패하였습니다.";
		}
		
		mv.addObject("message", message);
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@GetMapping("/modify")
	public ModelAndView modifyCharge(ModelAndView mv, @RequestParam String chargeNo
									, @AuthenticationPrincipal UserImpl userInfo) {
		
		/* 청구서 정보 조회 */
		ChargeDTO charge = chargeService.selectChargeDetail(chargeNo);
		
		/* 결재 완료된 주문서 불러오기 */
		int empNo = userInfo.getEmpNo();
		
		List<OrderDTO> orderList = orderService.selectApprovedOrderList(empNo);
		
		mv.addObject("charge", charge);
		mv.addObject("orderList", orderList);
		mv.setViewName("charge/modifyCharge");
		
		return mv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modifyEstimate(ModelAndView mv, HttpServletResponse response,
									@RequestBody ChargeDTO chargeInfo) {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int result = chargeService.modifyCharge(chargeInfo);
		String message = "";
		
		if(result > 0) {
			message = "청구서가 수정되었습니다.";
		} else {
			message = "청구서 수정에 실패하였습니다.";
		}
		
		mv.addObject("message", message);
		mv.setViewName("jsonView");
		
		return mv;
	}
}
