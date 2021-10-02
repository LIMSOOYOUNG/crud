package com.deft.crud.estimate.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.customer.model.dto.CustomerCompanyDTO;
import com.deft.crud.customer.model.dto.ExtCustomerDTO;
import com.deft.crud.customer.model.service.CustomerService;
import com.deft.crud.estimate.model.dto.EstimateDTO;
import com.deft.crud.estimate.model.service.EstimateService;
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
	
	@GetMapping("/selectAll")
	public ModelAndView selectEstimateList(ModelAndView mv) {
		
		List<EstimateDTO> estimateList = estimateService.selectEstimateList();
		
		mv.addObject("estimateList", estimateList);
		mv.setViewName("estimate/selectAllEstimate");
		
		return mv;
	}
	
	@GetMapping("/select")
	public ModelAndView selectEstimateByStatus(ModelAndView mv, HttpServletResponse response,
			@RequestParam String estimateStatus) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		List<EstimateDTO> estimateList = estimateService.selectEstimateListByStatus(estimateStatus);
		
		mv.addObject("estimateList", objectMapper.writeValueAsString(estimateList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@GetMapping("/insert")
	public ModelAndView insertEstimate(ModelAndView mv) {
		
		String estimate = estimateService.selectLastEstimateNo();
		List<ExtCustomerDTO> extCustomerList = customerService.selectExtCustomerList();
		
		mv.addObject("customerList", extCustomerList);
		mv.setViewName("estimate/insertEstimate");
		
		return mv;
	}
}
