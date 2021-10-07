package com.deft.crud.admin.adminemployee.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;



@Mapper
public interface AdminEmployeeMapper {

	/* 사원 상세보기 */
	AdminEmployeeDTO empDetail(int employeeNo);

	/* 수정 페이지 */
	AdminEmployeeDTO empInfoModify(int employeeNo);

	/* 수정 페이지 수정 부분 */
	int employeeModify(AdminEmployeeDTO parameters);



}
