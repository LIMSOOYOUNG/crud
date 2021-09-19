package com.deft.crud.employee.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.deft.crud.employee.model.dto.EmployeeDTO;
import com.deft.crud.employee.model.service.EmployeeService;

public class EmployeeController {

	/* 사원 조회 */
	@GetMapping("select")
	public String employeeSelect(Model model) {
		
		
		
		return "employee/select";
	}
	
}
