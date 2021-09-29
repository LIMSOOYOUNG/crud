package com.deft.crud.organization.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.organization.model.dto.DepartmentDTO;
import com.deft.crud.organization.model.dto.JobDTO;

@Mapper
public interface OrganizationMapper {

	/* 부서관리 조회*/
	List<DepartmentDTO> selectDepartment();

	/* 직급관리 조회*/
	List<JobDTO> selectJob();

	/* 부서 등록 */
	int departmentInsert(DepartmentDTO departDTO);

	/* 직급 등록 */
	int jobInsert(JobDTO jobDTO);

	/* 부서 수정 */
	int departmentModify(DepartmentDTO departmentDTO);

	/* 직급 수정 */
	int jobModify(JobDTO jobDTO);

//	/* 부서 비활성화 */
//	int departmentDisabled(DepartmentDTO deptDTO);
//
//	/* 직급 비활성화 */
//	int jobDisabled(JobDTO jobDTO);



}
