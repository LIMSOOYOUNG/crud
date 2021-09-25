package com.deft.crud.organization.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.organization.model.dao.OrganizationMapper;
import com.deft.crud.organization.model.dto.DepartmentDTO;
import com.deft.crud.organization.model.dto.JobDTO;

@Service
public class OrganizationService {

	private OrganizationMapper organizationMapper;
	
	@Autowired
	public OrganizationService(OrganizationMapper organizationMapper) {
		this.organizationMapper = organizationMapper;
	}
	
	/* 부서관리 조회 */
	public List<DepartmentDTO> selectOrganization() {
		
		List<DepartmentDTO> departmentList = organizationMapper.selectDepartment();
		
		return departmentList;
	}


	/* 직급관리 조회*/
	public List<JobDTO> selectJob() {
		
		List<JobDTO> jobList = organizationMapper.selectJob();
		
		return jobList;
	}

	
}
