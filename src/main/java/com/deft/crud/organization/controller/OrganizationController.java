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
		
		/* DepartmentDTO를 리스트에 담아 서비스에 보내준다. */
		List<DepartmentDTO> departmentList = organizationService.selectOrganization();
		
		/* 페이지 이동값을 지정한다. */
		mv.setViewName("/organization/selectdepartment");
		
		/* key값과 value값 지정 */
		mv.addObject("departmentList", departmentList);
		
		return mv;
	}
	
	/* 직급관리 조회*/
	@GetMapping("/selectjob")
	public ModelAndView selectJob(ModelAndView mv) {
		
		/* JobDTO를 리스트에 담아 서비스에 보내준다. */
		List<JobDTO> jobList = organizationService.selectJob();
		
		/* 페이지 이동값 지정 */
		mv.setViewName("/organization/selectjob");
		
		/* key값과 value값 지정 */
		mv.addObject("jobList", jobList);
		
		return mv;
	}
	
	
	/* 부서 등록 */
	@PostMapping("departmentinsert")
	public ModelAndView departmentInsertForm(ModelAndView mv, @RequestParam String deptName
			, @RequestParam String deptFax, @RequestParam String deptTel, @RequestParam String deptStatus) {
		
		DepartmentDTO departDTO = new DepartmentDTO();
		
		/* DepartmentDTO에 RequestParam 값들을 받는다. */
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
	@PostMapping("jobinsert")
	public ModelAndView joinInsertForm(ModelAndView mv, RedirectAttributes rttr
			                          , @RequestParam String jobName, @RequestParam String jobStatus) {
		
		/* JobDTO에 RequestParam 값들을 받는다. */
		JobDTO jobDTO = new JobDTO();
		jobDTO.setJobName(jobName);
		jobDTO.setJobStatus(jobStatus);
		
		/* JobDTO를 담아 서비스에 전달한다. */
		int result = organizationService.jobInsert(jobDTO);
		
		if(result > 0) {
			
			/* 페이지 이동값 지정 */
			mv.setViewName("redirect:/organization/selectjob");
			
		}
		
		return mv;
	}
	
	/* 부서 수정 */
	@GetMapping("departmentmodify")
	public ModelAndView departmentModify(ModelAndView mv, @RequestParam String deptCode) {

		/* DepartmentDTO에 deptCode를 담아 서비스에 전달 */
		DepartmentDTO deptDTO = organizationService.deptModifyForm(deptCode);
		
		/* 페이지 이동값 지정 */
		mv.setViewName("organization/departmentmodify");
		
		/* key값과 value값 지정 */
		mv.addObject("deptDTO", deptDTO);
		
		return mv;
	}
	
	@PostMapping("departmentmodify")
	public ModelAndView departmentModifyForm(ModelAndView mv, @RequestParam String deptCode
		     , @RequestParam String deptName, @RequestParam String deptFax, @RequestParam String deptTel, @RequestParam String deptStatus){
		
		/* DepartmentDTO에 RequestParam 값들을 받는다. */
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setDeptCode(deptCode);
		departmentDTO.setDeptName(deptName);
		departmentDTO.setDeptFax(deptFax);
		departmentDTO.setDeptTel(deptTel);
		departmentDTO.setDeptStatus(deptStatus);
		
		/* DepartmentDTO 값을 서비스에 전달한다. */
		int result = organizationService.departmentModify(departmentDTO);
		
		if(result > 0) {
			
			mv.setViewName("redirect:/organization/selectdepartment");
		}
		
		return mv;
	}
	
	/* 직급 수정 */
	@GetMapping("jobmodify")
	public ModelAndView jobModify(ModelAndView mv, @RequestParam String jobCode) {
		
		/* JobDTO에 jobCode를 담아 서비스에 전달 */
		JobDTO jobDTO = organizationService.jobModifyForm(jobCode);
		
		/* 페이지 이동값 지정 */
		mv.setViewName("/organization/jobmodify");
		
		/* key값과 value값을 지정한다. */
		mv.addObject("jobDTO", jobDTO);
		
		return mv;
	}
	
	
	@PostMapping("jobmodify")
	public ModelAndView jobModifyForm(ModelAndView mv, @RequestParam String jobCode
            , @RequestParam String jobName, @RequestParam String jobStatus){
		
		/* JobDTO에 RequestParam값을 받아온다. */
		JobDTO jobDTO = new JobDTO();
		jobDTO.setJobCode(jobCode);
		jobDTO.setJobName(jobName);
		jobDTO.setJobStatus(jobStatus);
		
		/* 서비스에 JobDTO값을 전달한다. */
		int result = organizationService.jobModify(jobDTO);
		
		if(result > 0) {
			
			/* 페이지 이동값을 지정한다. */
			mv.setViewName("redirect:/organization/selectjob");
		}
		
		return mv;
	}
	

}
