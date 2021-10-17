package com.deft.crud.employee.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.employee.model.dto.EmployeeDTO;

@Mapper
public interface EmployeeMapper {

	List<EmployeeDTO> selectEmployee();

	/* 비밀번호 변경 */
	int modifyPwd(EmployeeDTO empInfo);

	

}
