package com.deft.crud.employee.model.dao;

import java.util.List;

import com.deft.crud.employee.model.dto.EmployeeDTO;

public interface EmployeeMapper {

	List<EmployeeDTO> selectEmployee(EmployeeDTO empDTO);

}
