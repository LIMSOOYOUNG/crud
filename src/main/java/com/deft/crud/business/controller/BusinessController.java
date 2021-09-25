package com.deft.crud.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/business/*")
public class BusinessController {

	public BusinessController() {
		
	}
	
	@GetMapping("/selectAll")
	public ModelAndView selectBusinessChanceAll(ModelAndView mv) {
		
	return mv;	
	}
	
}
