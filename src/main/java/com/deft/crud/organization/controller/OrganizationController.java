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
	@GetMapping("departmentinsert")
	public void departmentInsert() {}
	
	@PostMapping("departmentinsert")
	public ModelAndView departmentInsertForm(ModelAndView mv, RedirectAttributes rttr
										     , @RequestParam String deptName, @RequestParam String deptFax, @RequestParam String deptTel, @RequestParam String deptStatus) {
		
		DepartmentDTO departDTO = new DepartmentDTO();
		
		departDTO.setDeptName(deptName);
		departDTO.setDeptFax(deptFax);
		departDTO.setDepttel(deptTel);
		departDTO.setDeptStatus(deptStatus);
		
		int result = organizationService.departmentInsert(departDTO);
		
		if(result>0) {
			rttr.addFlashAttribute("flashMessage", "성공!!");
		}else {
			rttr.addFlashAttribute("flashMessage", "실패!!");
		}
		mv.setViewName("redirect:/departmentinsert");
		
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
		
		int result = organizationService.jobInsert(jobDTO);
		
		if(result > 0) {
			rttr.addFlashAttribute("flashMessage", "성공!!");
		}else {
			rttr.addFlashAttribute("flashMessage", "실패!!");
		}
		mv.setViewName("redirect:/jobinsert");
		
		return mv;
	}
	
	/* 부서 수정 */
	@GetMapping("departmentmodify")
	public void departmentModify() {}
	
	@PostMapping("departmentmodify")
	public ModelAndView departmentModifyForm(ModelAndView mv, RedirectAttributes rttr, @RequestParam String deptCode
		     , @RequestParam String deptName, @RequestParam String deptFax, @RequestParam String deptTel, @RequestParam String deptStatus) {
		
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setDeptCode(deptCode);
		departmentDTO.setDeptName(deptName);
		departmentDTO.setDeptFax(deptFax);
		departmentDTO.setDepttel(deptTel);
		departmentDTO.setDeptStatus(deptStatus);
		
		int result = organizationService.departmentModify(departmentDTO);
		
		if(result > 0) {
			rttr.addFlashAttribute("flashMessage", "성공!!");
		}else {
			rttr.addFlashAttribute("flashMessage", "실패!!");
		}
		mv.setViewName("redirect:/departmentmodify");
		
		return mv;
	}
	
	/* 직급 수정 */
	@GetMapping("jobmodify")
	public void jobModify() {}
	
	@PostMapping("jobmodify")
	public ModelAndView jobModifyForm(ModelAndView mv, RedirectAttributes rttr, @RequestParam String jobCode
            , @RequestParam String jobName, @RequestParam String jobStatus) {
		
		JobDTO jobDTO = new JobDTO();
		jobDTO.setJobCode(jobCode);
		jobDTO.setJobName(jobName);
		jobDTO.setJobStatus(jobStatus);
		
		int result = organizationService.jobModify(jobDTO);
		
		if(result > 0) {
			rttr.addFlashAttribute("flashMessage", "성공!!");
		}else {
			rttr.addFlashAttribute("flashMessage", "실패!!");
		}
		mv.setViewName("redirect:/jobmodify");
		
		return mv;
	}
	
	/* 부서 비활성화 */
	@GetMapping("departmentdisabled")
	public void departDisabled() {}
	
	@PostMapping("departmentdisabled")
	public ModelAndView departDisabled(ModelAndView mv, RedirectAttributes rttr, @RequestParam String deptCode, @RequestParam String deptStatus) {
		
		DepartmentDTO deptDTO = new DepartmentDTO();
		deptDTO.setDeptCode(deptCode);
		deptDTO.setDeptStatus(deptStatus);
		
		int result = organizationService.departmentDisabled(deptDTO);
		
		if(result > 0) {
			rttr.addFlashAttribute("flashMessage", "성공!!");
		}else {
			rttr.addFlashAttribute("flashMessage", "실패!!");
		}
		mv.setViewName("redirect:/departmentDisabled");
		
		return mv;
	}
	
	/* 직급 비활성화 */
	@GetMapping("jobdisabled")
	public void jobDisabled() {}
	
	@PostMapping("jobdisabled")
	public ModelAndView jobDisabledForm(ModelAndView mv, RedirectAttributes rttr, @RequestParam String jobCode, @RequestParam String deptStatus) {
		
		JobDTO jobDTO = new JobDTO();
		jobDTO.setJobCode(jobCode);
		jobDTO.setJobStatus(deptStatus);
		
		int result = organizationService.jobDisabled(jobDTO);
		
		if(result > 0) {
			rttr.addFlashAttribute("flashMessage", "성공!!");
		}else {
			rttr.addFlashAttribute("flashMessage", "실패!!");
		}
		mv.setViewName("redirect:/jobdisabled");
		
		
		return mv;
	}
	
}
