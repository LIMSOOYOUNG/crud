package com.deft.crud.estimate.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.customer.model.dto.ExtCustomerDTO;
import com.deft.crud.customer.model.service.CustomerService;
import com.deft.crud.estimate.model.dto.EstimateDTO;
import com.deft.crud.estimate.model.dto.EstimateProductDTO;
import com.deft.crud.estimate.model.service.EstimateService;
import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.stock.model.dto.StorageDTO;
import com.deft.crud.stock.model.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/estimate")
public class EstimateController {
	
	private final EstimateService estimateService;
	private final CustomerService customerService;
	private final StockService stockService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public EstimateController(EstimateService estimateService, CustomerService customerService,
			StockService stockService, ObjectMapper objectMapper) {
		this.estimateService = estimateService;
		this.customerService = customerService;
		this.stockService = stockService;
		this.objectMapper = objectMapper;
	}
	
	@GetMapping("selectAll")
	public ModelAndView selectEstimateList(ModelAndView mv, @AuthenticationPrincipal UserImpl userInfo) {
		
		System.out.println("페이지 이동!!");
		
		int empNo = userInfo.getEmpNo();
		
		List<EstimateDTO> estimateList = estimateService.selectEstimateList(empNo);
		
		mv.addObject("estimateList", estimateList);
		mv.setViewName("estimate/selectAllEstimate");
		
		return mv;
	}
	
	@GetMapping("/selectAll/status")
	public ModelAndView selectEstimateListByStatus(ModelAndView mv, HttpServletResponse response,
			@RequestParam String estimateStatus, @AuthenticationPrincipal UserImpl userInfo) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int empNo = userInfo.getEmpNo();
		
		List<EstimateDTO> estimateList = estimateService.selectEstimateListByStatus(estimateStatus, empNo);
		
		mv.addObject("estimateListByStatus", objectMapper.writeValueAsString(estimateList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@GetMapping("/select")
	public ModelAndView selectEstimateDetail(ModelAndView mv, @RequestParam String estimateNo) {
		
		EstimateDTO estimate = estimateService.selectEstimateDetail(estimateNo);
		
		mv.addObject("estimate", estimate);
		mv.setViewName("estimate/selectEstimate");
		
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView insertEstimate(ModelAndView mv) {
		
		System.out.println("견적서 작성!!");
		/* 새로운 견적번호 및 일자 입력 */
		EstimateDTO estimate = new EstimateDTO();
		LocalDate newEstimateLocalDate = LocalDate.now();
		String newEstimateDate= newEstimateLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		String newEstimateNo = estimateService.selectEstimateNo(newEstimateDate);
		
		estimate.setEstimateNo(newEstimateNo);
		estimate.setEstimateDate(newEstimateDate);
		
		/* 고객 목록 조회 */
		List<ExtCustomerDTO> extCustomerList = customerService.selectExtCustomerList();
		
		/* 상품 목록 조회 */
		List<StorageDTO> stockList = stockService.selectSellableProductAll();
		
		mv.addObject("estimate", estimate);
		mv.addObject("customerList", extCustomerList);
		mv.addObject("stockList", stockList);
		mv.setViewName("estimate/insertEstimate");
		
		return mv;
	}
	
	@PostMapping("/insert")
	public ModelAndView insertEstimate(ModelAndView mv, HttpServletResponse response,
			RedirectAttributes rttr, @RequestBody EstimateDTO estimate) {
		
		response.setContentType("application/json; charset=UTF-8");
		
//		int result = estimateService.insertEstimate(estimate);
		int result = 1;
		String message = "";
		
		if(result > 0) {
			message = "견적서가 등록되었습니다.";
		} else {
			message = "견적서 등록에 실패하였습니다.";
		}
		
		rttr.addFlashAttribute("message", message);
		System.out.println("데이터 전송!!");
		mv.setViewName("redirect:/estimate/selectAll");
		
		return mv;
	}
	
	@GetMapping("/update")
	public ModelAndView updateEstimate(ModelAndView mv, @RequestParam String estimateNo) {
		
		/* 견적서 정보 조회 */
		EstimateDTO estimate = estimateService.selectEstimateDetail(estimateNo);
		
		/* 고객 목록 조회 */
		List<ExtCustomerDTO> extCustomerList = customerService.selectExtCustomerList();
		
		/* 상품 목록 조회 */
		List<StorageDTO> stockList = stockService.selectSellableProductAll();
		
		mv.addObject("estimate", estimate);
		mv.addObject("customerList", extCustomerList);
		mv.addObject("stockList", stockList);
		mv.setViewName("estimate/updateEstimate");
		
		return mv;
	}
}
