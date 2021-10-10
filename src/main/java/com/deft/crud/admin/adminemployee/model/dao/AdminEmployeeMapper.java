package com.deft.crud.admin.adminemployee.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;
import com.deft.crud.admin.adminemployee.model.dto.DepartmentDTO;
import com.deft.crud.admin.adminemployee.model.dto.JobDTO;



@Mapper
public interface AdminEmployeeMapper {

	/* 사원 상세보기 */
	AdminEmployeeDTO empDetail(int employeeNo);

	/* 수정 페이지 */
	AdminEmployeeDTO empInfoModify(int employeeNo);

	/* 수정 페이지 수정 부분 */
	int employeeModify(AdminEmployeeDTO parameters);

	/* JobName List 불러오기 */
	List<JobDTO> jobNameList();

	/* DeptName List 불러오기*/
	List<DepartmentDTO> deptNameList();

	/* manager List 불러오기*/
	List<AdminEmployeeDTO> managerList();

}
