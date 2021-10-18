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
	public ModelAndView userPerformChart(ModelAndView mv, HttpServletResponse response, @AuthenticationPrincipal UserImpl loginInfo) throws JsonProcessingException {
		
		response.setCharacterEncoding("UTF-8");
		
		/* 현재 연도 정보를 LocalDate에서 가지고 온다. */
		LocalDate collectBillDate = LocalDate.now();
		int collectBillYear = collectBillDate.getYear();
		
		/* 로그인된 사원 정보를 세션에서 가지고 온다. */
		int empNo = loginInfo.getEmpNo();
		
		/* 사원 정보와 부서정보를 조건을 주기 위해 인자값으로 넘겨주고 조회를 한다. */
		List<PerformanceDTO> userPerformChart = dashBoardService.userPerformChart(empNo, collectBillYear);
		
		/* 사원 목표 실적 조회 */
		List<TargetPerfomDTO> userTargetPerformChart = dashBoardService.userTargetPerformChart(empNo, collectBillYear);
		
		System.out.println("userPerformChart : " + userPerformChart);
		
		
		mv.addObject("userPerformChart", objectMapper.writeValueAsString(userPerformChart));
		mv.addObject("userTargetPerformChart", objectMapper.writeValueAsString(userTargetPerformChart));
		mv.setViewName("jsonView");
		return mv;
	}

	/* 로그인된 사원이 속한 부서 실적 그래프 */
	@GetMapping("/dept/sales/selectAll")
	public ModelAndView deptPerformChart(ModelAndView mv, HttpServletResponse response, @AuthenticationPrincipal UserImpl loginInfo) throws JsonProcessingException {
		
		response.setCharacterEncoding("UTF-8");
		
		/* 현재 연도 정보를 LocalDate에서 가지고 온다.*/
		LocalDate collectBillDate = LocalDate.now();
		int collectBillYear = collectBillDate.getYear();

		/* 로그인된 사원 정보와 부서 정보를 세션에서 가지고 온다. */
		int empNo = loginInfo.getEmpNo();
		String deptCode = loginInfo.getDeptCode();
		
		EmpInfoDTO empInfo = new EmpInfoDTO();
		empInfo.setEmpNo(empNo);
		empInfo.setDeptCode(deptCode);
		
		/* 사원 정보와 부서정보를 조건을 주기 위해 인자값으로 넘겨주고 조회를 한다. */
		List<PerformanceDTO> deptPerformChart = dashBoardService.deptPerformChart(empInfo, collectBillYear);
		
		System.out.println("deptPerformChart : " + deptPerformChart);
		
		System.out.println("890890890890802982390890");
		System.out.println("empNo : " + empNo);
		System.out.println(deptCode);
		
		mv.addObject("deptPerformChart", objectMapper.writeValueAsString(deptPerformChart));
		mv.setViewName("jsonView");
		return mv;
	}
}
