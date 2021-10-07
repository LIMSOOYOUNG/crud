package com.deft.crud.sales.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.sales.model.dto.PerformanceDTO;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;
import com.deft.crud.sales.model.service.SalesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/sales")
public class SalesController {
	
	private final SalesService salesService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public SalesController(SalesService salesService, ObjectMapper objectMapper) {
		this.salesService = salesService;
		this.objectMapper = objectMapper;
	}
	
	/* 한 명의 사원 목표 실적을 조회하기 위한 컨트롤러 */
	@GetMapping("/selectAll")
	public ModelAndView selectUserSalesAll(ModelAndView mv, @AuthenticationPrincipal UserImpl loginInfo) {
		
		int empNo = loginInfo.getEmpNo();
		
		/* 로그인 된 사원의 목표 실적 조회*/
		List<TargetPerfomDTO> empTargetPerformList = salesService.empTargetPerformList(empNo);
		
		/* 폼에다 실적을 등록하기 위한 연과 월을 확인해주기 위해 LocalDate 생성 후 현재 연도와 월의 객체를 뷰에 전달*/
		LocalDate insertTargetDate = LocalDate.now();
		
		String performYear = Integer.toString(insertTargetDate.getYear());				// 디비에 값 저장을 위해 String 타입으로 형변환(연도)
		String performMonth = Integer.toString(insertTargetDate.getMonthValue());       // 위와 내용 같음 (월)
		System.out.println("monthValue : " + insertTargetDate.getMonthValue());
		
		System.out.println("empTargetPerformList : " + empTargetPerformList);
		
		mv.addObject("performYear", performYear);
		mv.addObject("performMonth", performMonth);
		mv.addObject("empTargetPerformList", empTargetPerformList);
		mv.setViewName("sales/selectAllEmpSales");
		 
		return mv;
	}
	
	@PostMapping("/user/perform/selectAll")
	public ModelAndView selectUserPerformList(ModelAndView mv, HttpServletResponse response, @AuthenticationPrincipal UserImpl loginInfo) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");

		int empNo = loginInfo.getEmpNo();
		
		/* 사원 실제 월간 실적 조회 */
		List<PerformanceDTO> empPerfomList = salesService.empPerformList(empNo);
		System.out.println("empPerfomList : " + empPerfomList);
		
		mv.addObject("empPerfomList", objectMapper.writeValueAsString(empPerfomList));
		mv.setViewName("jsonView");
		return mv;
	}
	
	@GetMapping("/select")
	public ModelAndView userPerformList(ModelAndView mv, 
										@RequestParam("no") String performDate) {
		
		String performYear = performDate.substring(0, 4);
		String performMonth = performDate.substring(4);
		
		System.out.println("performYear : " + performYear);
		System.out.println("performMonth : " + performMonth);
		
		System.out.println("performDate : " + performDate);
		
		
		return null;
	}
	
	
	
	/* 사원 목표 실적 등록*/
	@PostMapping("/insert/target")
	public ModelAndView insertTargetSales(ModelAndView mv, @AuthenticationPrincipal UserImpl loginInfo, 
			@ModelAttribute TargetPerfomDTO parameters, RedirectAttributes rttr) {
		
		int empNo = loginInfo.getEmpNo();
		
		parameters.setEmpNo(empNo);
		
		int result = salesService.insertTargetSales(parameters, loginInfo);
		
		String message = "";
		
		if(result > 0) {
			message = "목표 실적 등록을 성공하셨습니다!";
		} else {
			message = "이미 실적을 등록하셨습니다!";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/sales/selectAll");
		return mv;
	}
	
}
