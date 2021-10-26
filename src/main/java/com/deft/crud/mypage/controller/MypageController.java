package com.deft.crud.mypage.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;
import com.deft.crud.admin.adminemployee.model.service.AdminEmployeeService;
import com.deft.crud.member.model.service.UserImpl;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	private final AdminEmployeeService adminEmployeeService;
	
	public MypageController(AdminEmployeeService adminEmployeeService) {
		this.adminEmployeeService = adminEmployeeService;
	}
	
	/* 사원상세 정보 */
	@GetMapping("/profile/select")
	public ModelAndView employeeDetailSelect(ModelAndView mv, @AuthenticationPrincipal UserImpl userInfo)  {
		
		int empNo = userInfo.getEmpNo();
		
		AdminEmployeeDTO employeeDTO = adminEmployeeService.empDetail(empNo);
		
		mv.addObject("employeeDTO", employeeDTO);
		mv.setViewName("employee/employeedetail");
		
		return mv;
	}
}
