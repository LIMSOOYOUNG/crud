package com.deft.crud.business.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.business.model.dto.BusinessActivityDTO;
import com.deft.crud.business.model.dto.BusinessChanceDTO;
import com.deft.crud.business.model.service.BusinessService;

@Controller
@RequestMapping("/business/*")
public class BusinessController {

	private final BusinessService businessService;
	
	@Autowired
	public BusinessController(BusinessService businessService) {
		this.businessService = businessService;
	}
	
	/* 전체 영업기회 목록 조회 */
	@GetMapping("/selectAll")
	public ModelAndView selectBusinessChanceAll(ModelAndView mv) {
		
		List<BusinessChanceDTO> businessChanceList = new ArrayList<>();
		
		businessChanceList = businessService.businessChanceSelectAll();
		
		mv.addObject("businessChanceList", businessChanceList);
		mv.setViewName("business/businessChanceList");
		
	return mv;	
	}

	@GetMapping("/selectBasicInfo")
	public ModelAndView selectBasicInfoByNo(ModelAndView mv, @RequestParam("customerNo") int customerNo ) {
		
		/* 선택한 영업기회 내용 변경 이력*/
		
		
		/* 선택한 엽업기회의 기본정보 */
		
		
		
		/* 선택한 영업기회의 활동이력*/
		List<BusinessActivityDTO> businessActivityList = businessService.selectActivityInfoByNo(customerNo);
		
		mv.addObject("businessActivityList", businessActivityList);
		mv.setViewName("business/businessChanceBasicInfo");
		
		return mv;
	}
	
}



















