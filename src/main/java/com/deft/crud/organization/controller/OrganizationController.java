package com.deft.crud.organization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.organization.model.dto.DepartmentDTO;
import com.deft.crud.organization.model.dto.JobDTO;
import com.deft.crud.organization.model.service.OrganizationService;

@Controller
@RequestMapping("/organization/*")
public class OrganizationController {

	
	private OrganizationService organizationService;
	
	@Autowired
	public OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	/* 부서목록 조회 */
	@GetMapping("/selectdepartment")
	public ModelAndView selectOrganization(ModelAndView mv) {
		
		List<DepartmentDTO> departmentList = organizationService.selectOrganization();
		
		mv.setViewName("organization/selectdepartment");
		mv.addObject("departmentList", departmentList);
		
		return mv;
	}
	
	/* 직급관리 조회*/
	@GetMapping("/selectjob")
	public ModelAndView selectJob(ModelAndView mv) {
		
		List<JobDTO> jobList = organizationService.selectJob();
		
		mv.setViewName("organization/selectjob");
		mv.addObject("jobList", jobList);
		
		return mv;
	}
	
}
