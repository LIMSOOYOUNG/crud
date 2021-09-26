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



}
