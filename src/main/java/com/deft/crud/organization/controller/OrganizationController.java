package com.deft.crud.organization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.organization.model.dto.OrganizationDTO;
import com.deft.crud.organization.model.service.OrganizationService;

@Controller
@RequestMapping()
public class OrganizationController {

	
	private OrganizationService organizationService;
	
	@Autowired
	public OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	/* 부서목록 조회 */
	public ModelAndView selectOrganization(ModelAndView mv) {
		
		List<OrganizationDTO> organizationList = organizationService.selectOrganization();
		
		mv.setViewName("");
		mv.addObject("oragnizationList", organizationList);
		
		return mv;
	}
	
}
