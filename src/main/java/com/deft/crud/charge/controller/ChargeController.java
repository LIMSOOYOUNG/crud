package com.deft.crud.charge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.charge.model.dto.ChargeDTO;
import com.deft.crud.charge.model.service.ChargeService;
import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.order.model.dto.OrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/charge")
public class ChargeController {
	
	private final ChargeService chargeService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public ChargeController(ChargeService chargeService, ObjectMapper objectMapper) {
		
		this.chargeService = chargeService;
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
	public ModelAndView selectOrderDetail(ModelAndView mv, @RequestParam String chargeNo) {
		
//		ChargeDTO charge = chargeService.selectChargeDetail(chargeNo);
		
//		mv.addObject("charge", charge);
		mv.setViewName("charge/selectCharge");
		
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView insertCharge(ModelAndView mv, @AuthenticationPrincipal UserImpl userInfo) {
		
		mv.setViewName("charge/insertCharge");
		
		return mv;
	}
	
	@GetMapping("/modify")
	public ModelAndView modifyCharge(ModelAndView mv, @RequestParam String chargeNo) {
		
		mv.setViewName("charge/modifyCharge");
		
		return mv;
	}
}
