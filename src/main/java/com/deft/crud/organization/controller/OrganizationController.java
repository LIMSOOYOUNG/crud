package com.deft.crud.organization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.organization.model.dto.DepartmentDTO;
import com.deft.crud.organization.model.dto.JobDTO;
import com.deft.crud.organization.model.service.OrganizationService;

@Controller
@RequestMapping("/organization/*")
public class OrganizationController {

	
	private final OrganizationService organizationService;
	
	@Autowired
	public OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	/* 부서목록 조회 */
	@GetMapping("/selectdepartment")
	public ModelAndView selectOrganization(ModelAndView mv) {
		
		List<DepartmentDTO> departmentList = organizationService.selectOrganization();
		System.out.println("1111111111" + departmentList);
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
	
	
	/* 부서 등록 */
	@PostMapping("departmentinsert")
	public ModelAndView departmentInsertForm(ModelAndView mv, @RequestParam String deptName
			, @RequestParam String deptFax, @RequestParam String deptTel, @RequestParam String deptStatus) {
		
		DepartmentDTO departDTO = new DepartmentDTO();
		
		/* DTO에 값 전달 */
		departDTO.setDeptName(deptName);
		departDTO.setDeptFax(deptFax);
		departDTO.setDeptTel(deptTel);
		departDTO.setDeptStatus(deptStatus);
		
		/* 서비스에 DTO 전달 */
		int result = organizationService.departmentInsert(departDTO);
		
		if(result>0) {
			
			mv.setViewName("redirect:/organization/selectdepartment");
			
		}
		
		
		return mv;
	}
	
	/* 직급 등록 */
	@GetMapping("jobinsert")
	public void jobInsert() {}
	
	@PostMapping("jobinsert")
	public ModelAndView joinInsertForm(ModelAndView mv, RedirectAttributes rttr
			                          , @RequestParam String jobName, @RequestParam String jobStatus) {
		
		JobDTO jobDTO = new JobDTO();
		jobDTO.setJobName(jobName);
		jobDTO.setJobStatus(jobStatus);
		
		System.out.println(jobDTO);
		
		int result = organizationService.jobInsert(jobDTO);
		
		if(result > 0) {
			
			mv.setViewName("redirect:/organization/selectjob");
			
		}
		
		return mv;
	}
	
	/* 부서 수정 */
	@GetMapping("departmentmodify")
	public ModelAndView departmentModify(ModelAndView mv, @RequestParam String deptCode) {
		
		DepartmentDTO deptDTO = organizationService.deptModifyForm(deptCode);
		
		System.out.println(".1.1.1.1.1.1" + deptDTO);
		
		mv.setViewName("organization/departmentmodify");
		mv.addObject("deptDTO", deptDTO);
		
		return mv;
	}
	
	@PostMapping("departmentmodify")
	public ModelAndView departmentModifyForm(ModelAndView mv, @RequestParam String deptCode
		     , @RequestParam String deptName, @RequestParam String deptFax, @RequestParam String deptTel, @RequestParam String deptStatus){
		
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setDeptCode(deptCode);
		departmentDTO.setDeptName(deptName);
		departmentDTO.setDeptFax(deptFax);
		departmentDTO.setDeptTel(deptTel);
		departmentDTO.setDeptStatus(deptStatus);
		
		System.out.println("............" + departmentDTO);
		
		int result = organizationService.departmentModify(departmentDTO);
		
		if(result > 0) {
			
			mv.setViewName("redirect:/organization/selectdepartment");
		}
		
		return mv;
	}
	
	/* 직급 수정 */
	@GetMapping("jobmodify")
	public ModelAndView jobModify(ModelAndView mv, @RequestParam String jobCode) {
		
		JobDTO jobDTO = organizationService.jobModifyForm(jobCode);
		System.out.println("............................."+ jobDTO);
		
		mv.setViewName("organization/jobmodify");
		mv.addObject("jobDTO", jobDTO);
		
		
		return mv;
	}
	
	
	@PostMapping("jobmodify")
	public ModelAndView jobModifyForm(ModelAndView mv, @RequestParam String jobCode
            , @RequestParam String jobName, @RequestParam String jobStatus){
		
		JobDTO jobDTO = new JobDTO();
		jobDTO.setJobCode(jobCode);
		jobDTO.setJobName(jobName);
		jobDTO.setJobStatus(jobStatus);
		
		System.out.println(jobDTO);
		
		int result = organizationService.jobModify(jobDTO);
		
		if(result > 0) {
			
			mv.setViewName("redirect:/organization/selectjob");
		}
		
		
		return mv;
	}
	

}
