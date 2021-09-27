package com.deft.crud.estimate.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.estimate.model.dto.EstimateDTO;
import com.deft.crud.estimate.model.service.EstimateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/estimate")
public class EstimateController {
	
	private final EstimateService estimateService;
	
	@Autowired
	public EstimateController(EstimateService estimateService) {
		this.estimateService = estimateService;
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
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<EstimateDTO> estimateList = estimateService.selectEstimateListByStatus(estimateStatus);
		
		mv.addObject("estimateList", mapper.writeValueAsString(estimateList));
		mv.setViewName("jsonView");
		
		return mv;
	}
}
