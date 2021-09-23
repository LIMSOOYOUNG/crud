package com.deft.crud.employee.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.deft.crud.employee.model.dao.EmployeeMapper;
import com.deft.crud.employee.model.dto.EmployeeDTO;

public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public List<EmployeeDTO> selectEmployee(EmployeeDTO empDTO) throws Exception {
		
		List<EmployeeDTO> employeeList = employeeMapper.selectEmployee(empDTO);
		
		return  employeeList;
	}

	
	
	
	

}
