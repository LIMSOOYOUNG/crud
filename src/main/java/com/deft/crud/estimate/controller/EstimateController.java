package com.deft.crud.estimate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.estimate.model.dto.EstimateDTO;
import com.deft.crud.estimate.model.service.EstimateService;

@Controller
@RequestMapping("/estimate")
public class EstimateController {
	
	private final EstimateService estimateService;
	
	@Autowired
	public EstimateController(EstimateService estimateService) {
		this.estimateService = estimateService;
	}
	
	
	@GetMapping("/selectAll")
	public ModelAndView selectEstimateSelectAll(ModelAndView mv) {
		
		List<EstimateDTO> estimateList = estimateService.estimateSelectAll();
		
		mv.addObject("estimateList", estimateList);
		mv.setViewName("estimate/selectAll");
		
		return mv;
	}
}
