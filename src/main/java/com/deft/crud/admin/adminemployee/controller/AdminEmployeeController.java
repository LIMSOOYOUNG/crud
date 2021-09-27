package com.deft.crud.admin.adminemployee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;
import com.deft.crud.admin.adminemployee.model.service.AdminEmployeeService;
import com.deft.crud.member.model.dto.MemberDTO;

@Controller
@RequestMapping("/admin")
public class AdminEmployeeController {

	private AdminEmployeeService adminEmployeeService;
	
	@Autowired
	public AdminEmployeeController(AdminEmployeeService adminEmployeeService) {
		this.adminEmployeeService = adminEmployeeService;
	}
	
	/* 사원등록 페이지로 이동 */
	@GetMapping(value = {"/emp/insert"})
	public ModelAndView insertMember(ModelAndView mv) {
		
		mv.setViewName("/admin/insertMember");
		
		return mv;
	}
	
	/* 사원 등록 페이지에서 데이터 가져옴 */
	@PostMapping(value = "/emp/insert")
	
	public String redirectInsertMember(MemberDTO member) {
		
		System.out.println(member);
		
//		int result = adminService.insertMember();
		
		
		return "main/main";
	}
	
	
	@GetMapping(value = "/emp/checkUserId")
	@ResponseBody
	public boolean checkUserId(@RequestParam String empId) {
		
		return adminEmployeeService.checkUserId(empId);
	}
	 
	/* 사원상세 정보 */
	@GetMapping("detailselectemployee")
	public ModelAndView employeeDetailSelect(ModelAndView mv, @RequestParam int employeeNo) {
		
		List<AdminEmployeeDTO> employeList = adminEmployeeService.detailselect(employeeNo);
		
		System.out.println(employeList);
		
		mv.setViewName("admin/detailselectemployee");
		mv.addObject("employeList", employeList);
		
		return mv;
	}

	
	
}
