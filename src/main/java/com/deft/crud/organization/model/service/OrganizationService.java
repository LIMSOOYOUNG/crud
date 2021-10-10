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

	/* 부서 등록 */
	public int departmentInsert(DepartmentDTO departDTO) {
		
		int result = organizationMapper.departmentInsert(departDTO);
		
		return result;
	}
	
	/* 직급 등록 */
	public int jobInsert(JobDTO jobDTO) {
		
		int result = organizationMapper.jobInsert(jobDTO);
		
		return result;
	}

	/* 부서 수정 */
	public int departmentModify(DepartmentDTO departmentDTO) {

		int result = organizationMapper.departmentModify(departmentDTO);

		return result;
	}
	/* 직급 수정 */
	public int jobModify(JobDTO jobDTO) {
		
		int result = organizationMapper.jobModify(jobDTO);
		
		return result;
	}

	/* jobForm 불러오기*/
	public JobDTO jobModifyForm(String jobCode) {
		
		JobDTO jobDTO = organizationMapper.jobModifyForm(jobCode);
		
		return jobDTO;
	}
	
	/* deptFrom 불러오기 */
	public DepartmentDTO deptModifyForm(String deptCode) {

		DepartmentDTO deptDTO = organizationMapper.deptModifyForm(deptCode);
		
		return deptDTO;
	}


	
}
