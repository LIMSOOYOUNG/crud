package com.deft.crud.employee.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.employee.model.dao.EmployeeMapper;
import com.deft.crud.employee.model.dto.EmployeeDTO;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	public EmployeeService(EmployeeMapper employeeMapper) {
		this.employeeMapper = employeeMapper;
	}
	
	public List<EmployeeDTO> selectEmployee(){
		
		List<EmployeeDTO> employeeList = employeeMapper.selectEmployee();
		
		System.out.println("list");
		
		return employeeList;
	}
}
