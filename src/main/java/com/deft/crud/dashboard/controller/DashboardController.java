package com.deft.crud.dashboard.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.dashboard.model.dto.PerformanceDTO;
import com.deft.crud.dashboard.model.service.DashBoardService;
import com.deft.crud.member.model.service.UserImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	private DashBoardService dashBoardService;
	private ObjectMapper objectMapper;
	
	@Autowired
	public DashboardController(DashBoardService dashBoardService, ObjectMapper objectMapper) {
		this.dashBoardService = dashBoardService;
		this.objectMapper = objectMapper;
	}
	
	@GetMapping
	public String dashboard() {
		return "/dashboard/dashboard";
	}
	
	
	@GetMapping("/emp/sales/selectAll")
	public ModelAndView dashboard(ModelAndView mv, HttpServletResponse response, @AuthenticationPrincipal UserImpl loginInfo) throws JsonProcessingException {
		
		response.setCharacterEncoding("UTF-8");

		LocalDate collectBillDate = LocalDate.now();
		
		int collectBillYear = collectBillDate.getYear();
		
		int empNo = loginInfo.getEmpNo();
		
		List<PerformanceDTO> userPerformChart = dashBoardService.userPerformChart(empNo, collectBillYear);
		
		System.out.println("userPerformChart : " + userPerformChart);
		
		
		mv.addObject("userPerformChart", objectMapper.writeValueAsString(userPerformChart));
		mv.setViewName("jsonView");
		return mv;
	}
}
