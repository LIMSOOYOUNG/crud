package com.deft.crud.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.business.model.dto.BusinessActivityDTO;
import com.deft.crud.business.model.dto.BusinessChanceDTO;
import com.deft.crud.business.model.dto.BusinessChanceHistoryDTO;
import com.deft.crud.business.model.service.BusinessService;
import com.deft.crud.customer.model.dto.CustomerCompanyDTO;
import com.deft.crud.customer.model.service.CustomerService;

@Controller
@RequestMapping("/business/*")
public class BusinessController {

	private final BusinessService businessService;
	private final CustomerService customerService;
	
	@Autowired
	public BusinessController(BusinessService businessService, CustomerService customerService) {
		this.businessService = businessService;
		this.customerService = customerService;
	}
	
	/* 전체 영업기회 목록 조회 */
	@GetMapping("/chance/selectAll")
	public ModelAndView selectBusinessChanceAll(ModelAndView mv) {
		
		List<BusinessChanceDTO> businessChanceList = businessService.selectBusinessChanceAll();
		
		mv.addObject("businessChanceList", businessChanceList);
		mv.setViewName("business/businessChanceList");
		
		return mv;	
	}

	/* 영업기회 상태변경이력 + 선택한 영업기회 정보 + 현재영업기회 관련 고객에 대한 활동 내역*/
	@GetMapping("/chance/selectBasicInfo")
	public ModelAndView selectBasicInfoByNo(ModelAndView mv,
											@RequestParam("customerNo") int customerNo,
											@RequestParam("businessChanceNo") int businessChanceNo) {
		
		/* 선택한 영업기회의 내용 변경내역*/
		List<BusinessChanceHistoryDTO> chanceHistoryList = businessService.selectChanceHistoryByNo(businessChanceNo);
		
		/* 선택한 엽업기회의 정보 */
		BusinessChanceDTO businessChanceInfo = businessService.selectChanceInfoByNo(businessChanceNo);
		
		/* 선택한 영업기회의 활동이력*/
		List<BusinessActivityDTO> businessActivityList = businessService.selectActivityListByNo(customerNo);
		
		/* 선택한 영업기회 대상 고객의 상세정보 */
		CustomerCompanyDTO customerInfo = customerService.selectCustomerInfo(customerNo);
		
		System.out.println("고객상세정보 : " + customerInfo);
		
		mv.addObject("chanceHistoryList", chanceHistoryList);			//영업기회변경이력
		mv.addObject("businessChanceInfo", businessChanceInfo); 	    //영업기회정보
		mv.addObject("businessActivityList", businessActivityList);		//영업활동목록
		mv.addObject("customerInfo", customerInfo);						//고객 상세정보
		mv.setViewName("business/businessChanceInfo");
		
		return mv;
	}
	
	/* 전체사원 영업활동 목록조회 (담당자용)*/
	@GetMapping("/activity/selectAll")
	public ModelAndView selectActivityAll(ModelAndView mv) {
		
		List<BusinessActivityDTO> businessActivityList = businessService.selectActivityAll();
		
		mv.addObject("businessActivityList", businessActivityList);
		mv.setViewName("business/businessActivityList");
		
		return mv;
	}

}



















