package com.deft.crud.employee.model.service;

import java.util.List;

import com.deft.crud.employee.model.dto.EmployeeDTO;


public interface EmployeeService {

	List<EmployeeDTO> selectEmployee(EmployeeDTO empDTO) throws Exception;


	



}
