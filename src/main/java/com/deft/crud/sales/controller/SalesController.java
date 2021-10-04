package com.deft.crud.sales.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;
import com.deft.crud.sales.model.service.SalesService;

@Controller
@RequestMapping("/sales")
public class SalesController {
	
	private final SalesService salesService;
	
	@Autowired
	public SalesController(SalesService salesService) {
		this.salesService = salesService;
	}
	
	/* 한 명의 사원 목표 실적을 조회하기 위한 컨트롤러 */
	@GetMapping("/selectAll")
	public ModelAndView selectUserSalesAll(ModelAndView mv, @AuthenticationPrincipal UserImpl loginInfo) {
		
		
		int loginEmpNo = loginInfo.getEmpNo();
		
		List<TargetPerfomDTO> empPerformList = salesService.empPerformList(loginEmpNo);
		LocalDate insertTargetDate = LocalDate.now();
		
		String performYear = Integer.toString(insertTargetDate.getYear());
		String performMonth = Integer.toString(insertTargetDate.getMonthValue());
		
		System.out.println("empSalesList : " + empPerformList);
		
		mv.addObject("performYear", performYear);
		mv.addObject("performMonth", performMonth);
		mv.addObject("empPerformList", empPerformList);
		mv.setViewName("sales/selectAllEmpSales");
		 
		return mv;
	}
	
	/* 사원 목표 실적 등록*/
	@PostMapping("/insert/target")
	public ModelAndView insertTargetSales(ModelAndView mv, @AuthenticationPrincipal UserImpl loginInfo, 
			@ModelAttribute TargetPerfomDTO parameters, RedirectAttributes rttr) {
		
		/* 목표실적을 등록한 연과 월을 디비에 입력하기 위해 LocalDate타입으로 현재 시간기준으로 연과 월을 생성*/
		
		System.out.println("parameters : " + parameters);
		
		int result = salesService.insertTargetSales(parameters);
		
		String message = "";
		
		if(result > 0) {
			message = "목표 실적 등록을 성공하셨습니다!";
		} else {
			message = "목표 실적 등록에 실패하셨습니다!";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/sales/selectAll");
		return mv;
	}
	
}
