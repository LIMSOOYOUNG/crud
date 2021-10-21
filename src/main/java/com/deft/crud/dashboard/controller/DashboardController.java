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

import com.deft.crud.dashboard.model.dto.BusinessChanceDTO;
import com.deft.crud.dashboard.model.dto.EmpInfoDTO;
import com.deft.crud.dashboard.model.dto.PerformanceDTO;
import com.deft.crud.dashboard.model.service.DashBoardService;
import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;
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
	
	
	/* 사원 실적 그래프 */
	@GetMapping("/emp/sales/selectAll")
	public ModelAndView userSalesChart(ModelAndView mv, HttpServletResponse response, @AuthenticationPrincipal UserImpl loginInfo) throws JsonProcessingException {
		
		response.setCharacterEncoding("UTF-8");
		
		/* 현재 연도 정보를 LocalDate에서 가지고 온다. */
		LocalDate collectBillDate = LocalDate.now();
		int collectBillYear = collectBillDate.getYear();
		
		
		/* 사원 정보와 부서정보를 조건을 주기 위해 인자값으로 넘겨주고 조회를 한다. */
		List<Integer> userSalesChart = dashBoardService.userSalesChart(loginInfo ,collectBillYear);
		System.out.println("userSalesChart : " + userSalesChart);
		
		/* 사원 목표 실적 조회 */
		List<Integer> userTargetSalesChart = dashBoardService.userTargetSalesChart(loginInfo , collectBillYear);
		
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
	@GetMapping("/business/failedChance")
	public ModelAndView failedChace(ModelAndView mv,  HttpServletResponse response, @AuthenticationPrincipal UserImpl loginInfo) throws JsonProcessingException {
		
		response.setCharacterEncoding("UTF-8");
		
		LocalDate businessChanceDate = LocalDate.now();
		int businessChanceFailedYear = businessChanceDate.getYear();
		
		int empNo = loginInfo.getEmpNo();
		
		List<BusinessChanceDTO> businessChanceFailed = dashBoardService.failedChart(empNo, businessChanceFailedYear);
		
		System.out.println("dasdasdasdassdasdasdasd"+ businessChanceFailed);
		
		mv.addObject("businessChanceFailed", objectMapper.writeValueAsString(businessChanceFailed));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 사원별 영업기회 수 통계 */
	

}
