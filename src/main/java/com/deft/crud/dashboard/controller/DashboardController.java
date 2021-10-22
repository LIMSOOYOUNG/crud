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

import com.deft.crud.dashboard.model.service.DashBoardService;
import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.sales.model.dto.PerformanceDTO;
import com.deft.crud.sales.model.service.SalesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	private DashBoardService dashBoardService;
	private SalesService salesService;
	private ObjectMapper objectMapper;
	
	
	@Autowired
	public DashboardController(DashBoardService dashBoardService, ObjectMapper objectMapper, SalesService salesService) {
		this.dashBoardService = dashBoardService;
		this.objectMapper = objectMapper;
		this.salesService = salesService;
	}
	
	/* 로그인이 되면 현재 월에 쌓은 사원의 상품 실적 정보를 가지고 대쉬보드 화면으로 넘어간다. */
	@GetMapping
	public ModelAndView dashboard(ModelAndView mv,  @AuthenticationPrincipal UserImpl loginInfo) {
		
		List<PerformanceDTO> productSalesThisMonth = salesService.productSalesThisMonth(loginInfo);
		
		System.out.println("productSalesThisMonth : " + productSalesThisMonth);
		
		mv.addObject("productSalesThisMonth", productSalesThisMonth);
		mv.setViewName("dashboard/dashboard");
		return mv;
	}
	
	/* 사원 실적 그래프 */
	@GetMapping("/emp/sales/selectAll")
	public ModelAndView userSalesChart(ModelAndView mv, HttpServletResponse response, @AuthenticationPrincipal UserImpl loginInfo) throws JsonProcessingException {
		
		response.setCharacterEncoding("UTF-8");
		
		
		/* 사원 정보와 부서정보를 조건을 주기 위해 인자값으로 넘겨주고 조회를 한다. */
		List<Integer> userSalesChart = dashBoardService.userSalesChart(loginInfo);
		System.out.println("userSalesChart : " + userSalesChart);
		
		/* 사원 목표 실적 조회 */
		List<Integer> userTargetSalesChart = dashBoardService.userTargetSalesChart(loginInfo);
		
		System.out.println("userTargetPerformChart : " + userTargetSalesChart);
		
		
		mv.addObject("userSalesChart", objectMapper.writeValueAsString(userSalesChart));
		mv.addObject("userTargetSalesChart", objectMapper.writeValueAsString(userTargetSalesChart));
		mv.setViewName("jsonView");
		return mv;
	}

	/* 로그인된 사원이 속한 부서 실적 그래프 */
	@GetMapping("/dept/sales/selectAll")
	public ModelAndView deptSalesChart(ModelAndView mv, HttpServletResponse response, @AuthenticationPrincipal UserImpl loginInfo) throws JsonProcessingException {
		
		response.setCharacterEncoding("UTF-8");
		
		/* 사원 정보와 부서정보를 조건을 주기 위해 인자값으로 넘겨주고 조회를 한다. */
		List<Integer> deptSalesChart = dashBoardService.deptSalesChart(loginInfo);
		
		System.out.println("deptPerformChart : " + deptSalesChart);
		
		
		mv.addObject("deptSalesChart", objectMapper.writeValueAsString(deptSalesChart));
		mv.setViewName("jsonView");
		return mv;
	}
	
	/* 영업기회 실패 통계 */
	@GetMapping("/failed/businessChance")
	public ModelAndView failedBusinessChanceChart(ModelAndView mv,  HttpServletResponse response, @AuthenticationPrincipal UserImpl loginInfo) throws JsonProcessingException {
		
		response.setCharacterEncoding("UTF-8");
		
		List<Integer> failedBusinessChanceChart = dashBoardService.failedBusinessChanceChart(loginInfo);
		
		System.out.println("failedBusinessChanceChart" + failedBusinessChanceChart);
		
		mv.addObject("failedBusinessChanceChart", objectMapper.writeValueAsString(failedBusinessChanceChart));
		mv.setViewName("jsonView");
		return mv;
	}
	

//	@GetMapping("/product/sales/selectAll")
//	public ModelAndView productSalesThisMonth(ModelAndView mv, HttpServletResponse response, @AuthenticationPrincipal UserImpl loginInfo) {
//		
//		System.out.println("ajax 통신 ");
//		response.setCharacterEncoding("UTF-8");
//		
//		List<PerformanceDTO> productSalesThisMonth = salesService.productSalesThisMonth(loginInfo);
//		
//		System.out.println("productSalesThisMonth : " + productSalesThisMonth);
//
//		mv.setViewName("jsonView");
//		return mv;
//		
//	}
	
	
	

}
