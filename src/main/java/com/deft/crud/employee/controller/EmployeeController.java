package com.deft.crud.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deft.crud.employee.model.dto.EmployeeDTO;
import com.deft.crud.employee.model.service.EmployeeService;
import com.deft.crud.employee.model.service.EmployeeServiceImpl;

@Controller
@RequestMapping("/employee/*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	/* 사원 조회 */
	@GetMapping("employeeselect")
	public String employeeSelect(EmployeeDTO empDTO, Model model) throws Exception{
		
		List<EmployeeDTO> employeeList = employeeService.selectEmployee(empDTO);
		
		model.addAttribute("employeeList", employeeList);
		
		return "employee/select";
	}
	
}
