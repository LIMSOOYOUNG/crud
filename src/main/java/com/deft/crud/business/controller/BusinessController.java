package com.deft.crud.business.controller;

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

import com.deft.crud.business.model.dto.BusinessActivityDTO;
import com.deft.crud.business.model.dto.BusinessChanceDTO;
import com.deft.crud.business.model.dto.BusinessChanceHistoryDTO;
import com.deft.crud.business.model.service.BusinessService;
import com.deft.crud.customer.model.dto.CustomerCompanyDTO;
import com.deft.crud.customer.model.dto.CustomerDTO;
import com.deft.crud.customer.model.service.CustomerService;
import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.stock.model.dto.approval.ApprovalModifyDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/business/*")
public class BusinessController {

	private final BusinessService businessService;
	private final CustomerService customerService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public BusinessController(BusinessService businessService, CustomerService customerService, ObjectMapper objectMapper) {
		this.businessService = businessService;
		this.customerService = customerService;
		this.objectMapper = objectMapper;
	}
	
	/* 전체 영업기회 목록 조회(담당자 or 사원) */
	@GetMapping("/chance/selectAll")
	public ModelAndView selectBusinessChanceAll(ModelAndView mv, @AuthenticationPrincipal UserImpl userInfo) {
		
		List<BusinessChanceDTO> businessChanceList = businessService.selectBusinessChanceAll(userInfo);
		
		mv.addObject("businessChanceList", businessChanceList);
		mv.setViewName("business/businessChanceList");
		
		return mv;	
	}

	/* 영업기회 진행상태별 조회(담당자 or 사원) */
	@GetMapping("/chance/selectAll/status")
	public ModelAndView selectBusinessChanceByStatus(ModelAndView mv, HttpServletResponse response
													, @RequestParam String businessChanceStatus
													, @AuthenticationPrincipal UserImpl userInfo) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		List<BusinessChanceDTO> businessChanceList = businessService.selectBusinessChanceByStatus(businessChanceStatus, userInfo);
		
		mv.addObject("businessChanceList", objectMapper.writeValueAsString(businessChanceList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	
	/* 영업기회 상태변경이력 + 선택한 영업기회 정보 + 현재영업기회 관련 고객에 대한 활동 내역*/
	@GetMapping("/chance/selectBasicInfo")
	public ModelAndView selectBasicInfoByNo(ModelAndView mv,
										    @RequestParam("customerNo") int customerNo,
										    @RequestParam("businessChanceNo") int businessChanceNo) {
		
		/* 선택한 영업기회의 내용 변경내역 */
		List<BusinessChanceHistoryDTO> chanceHistoryList = businessService.selectChanceHistoryByNo(businessChanceNo);
		
		/* 선택한 엽업기회의 정보 */
		BusinessChanceDTO businessChanceInfo = businessService.selectChanceInfoByNo(businessChanceNo);
		
		/* 선택한 영업기회의 활동이력*/
		List<BusinessActivityDTO> businessActivityList = businessService.selectActivityListByNo(customerNo);
		
		/* 선택한 영업기회 대상 고객의 상세정보 */
		CustomerCompanyDTO customerInfo = customerService.selectCustomerInfo(customerNo);
		
		System.out.println("@@@고객상세정보 : " + businessChanceInfo);
		
		mv.addObject("chanceHistoryList", chanceHistoryList);			//영업기회변경이력
		mv.addObject("businessChanceInfo", businessChanceInfo); 	    //영업기회정보
		mv.addObject("businessActivityList", businessActivityList);		//영업활동목록
		mv.addObject("customerInfo", customerInfo);						//고객 상세정보
		mv.setViewName("business/businessChanceInfo");
		
		return mv;
	}
	
	/* 영업활동 목록조회 + 담당중인 고객목록조회 (담당자 or 사원)*/
	@GetMapping("/activity/selectAll")
	public ModelAndView selectActivityAll(ModelAndView mv,
										  @AuthenticationPrincipal UserImpl userInfo) {
		
		List<BusinessActivityDTO> businessActivityList = businessService.selectActivityAll(userInfo);
		List<CustomerDTO> customerList = businessService.selectMyCustomerList(userInfo);
		
		mv.addObject("businessActivityList", businessActivityList);		// 전체영업활동 내역
		mv.addObject("customerList", customerList);		// 담당중인 고객 리스트
		mv.setViewName("business/businessActivityList");
		
		return mv;
	}
	
	/* 선택한 영업활동의 상세정보 조회 */
	@GetMapping("activity/selectDetailInfo")
	public ModelAndView selectActivityDetailInfoByNo(ModelAndView mv, HttpServletResponse response,
													 @RequestParam("activityNo") int activityNo)
													 throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		BusinessActivityDTO activityInfo = businessService.selectActivityDetailInfoByNo(activityNo);
		
		mv.addObject("activityInfo", objectMapper.writeValueAsString(activityInfo));
		mv.setViewName("jsonView");
		return mv;
	}
	
	/* 영업활동 등록 */
	@PostMapping("activity/insert")
	public ModelAndView insertActivity(ModelAndView mv, RedirectAttributes rttr,
									   @ModelAttribute BusinessActivityDTO parameters) {
		
		System.out.println(parameters);
		
		
		boolean result = businessService.insertActivity(parameters);
		
		String message = "";
		
		if(result) {
			message = "영업활동 등록완료";
		} else {
			message = "영업활동 등록실패!";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/business/activity/selectAll");
		
		return mv;
	}
	
	/* 영업활동 내용 수정 */
	@PostMapping("activity/modify")
	public ModelAndView modifyActivity(ModelAndView mv, RedirectAttributes rttr,
									   @ModelAttribute BusinessActivityDTO parameters) {
		
		System.out.println(parameters);
		
		
		  boolean result = businessService.modifyActivity(parameters);
		  
		  String message = "";
		  
		  if(result) { 
			  message = "영업활동내용 수정완료";
		  } else { 
			  message = "영업활동내용 수정실패!";
		  }
		  
		  rttr.addFlashAttribute("message", message);
		  mv.setViewName("redirect:/business/activity/selectAll");
		
		return mv;
	}
	
	/* 영업 활동 삭제 */
    @PostMapping("activity/delete")
    public ModelAndView deleteDetailActivity(ModelAndView mv, RedirectAttributes rttr,
    										 @ModelAttribute BusinessActivityDTO parameters) {

        int activityNo = parameters.getActivityNo();

        boolean result = businessService.deleteActivity(activityNo);

        String message = "";
		  
		  if(result) { 
			  message = "영업활동 삭제완료";
		  } else { 
			  message = "영업활동 삭제실패!";
		  }

		  rttr.addFlashAttribute("message", message);
		  mv.setViewName("redirect:/business/activity/selectAll");

        return mv;
    }
    
    /* 담당중인 고객리스트 */
    @GetMapping("myCustomer/selectAll")
    public ModelAndView selectMyCustomer(ModelAndView mv, HttpServletResponse response,
    									@AuthenticationPrincipal UserImpl userInfo) throws JsonProcessingException {
    	
    	response.setContentType("application/json; charset=UTF-8");
    	
    	List<CustomerDTO> customerList = businessService.selectMyCustomerList(userInfo);
    	
    	
    	mv.addObject("customerList", objectMapper.writeValueAsString(customerList));
		mv.setViewName("jsonView");
    	
		return mv;
    }
    
    /* 고객 기본정보 조회 */
    @PostMapping("customerInfo/select")
    public ModelAndView selectCustomerBasicInfo(ModelAndView mv, HttpServletResponse response,
    											@RequestParam int customerNo) throws JsonProcessingException {
    	
    	response.setContentType("application/json; charset=UTF-8");
    	
    	BusinessChanceDTO customerInfo = businessService.selectCustomerBasicInfo(customerNo);
    	System.out.println("@@@고객정보 :  " + customerInfo);
    	
    	mv.addObject("customerInfo", objectMapper.writeValueAsString(customerInfo));
		mv.setViewName("jsonView");
    	
    	return mv;
    }

}



















