package com.deft.crud.admin.adminemployee.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.admin.adminemployee.model.dao.AdminEmployeeMapper;
import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;
import com.deft.crud.admin.adminemployee.model.dto.DepartmentDTO;
import com.deft.crud.admin.adminemployee.model.dto.JobDTO;

@Service
public class AdminEmployeeService {

	private AdminEmployeeMapper adminEmployeeMapper;
	
	@Autowired
	public AdminEmployeeService(AdminEmployeeMapper adminEmployeeMapper) {
		this.adminEmployeeMapper = adminEmployeeMapper;
	}
	
	
	public boolean checkUserId(String empId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* 사원 상세보기 */
	public AdminEmployeeDTO empDetail(int employeeNo) {
		
		AdminEmployeeDTO adminEmployeeDTO = adminEmployeeMapper.empDetail(employeeNo);
		
		return adminEmployeeDTO;
	}


	public AdminEmployeeDTO empInfoModify(int employeeNo) {
		
		AdminEmployeeDTO adminEmployeeDTO = adminEmployeeMapper.empInfoModify(employeeNo);
		
		return adminEmployeeDTO;
	}


	public int employeeModify(AdminEmployeeDTO parameters) {
		
		int result = adminEmployeeMapper.employeeModify(parameters);
		
		return result;
	}


	public List<JobDTO> jobNameList() {
		
		List<JobDTO> JobList = adminEmployeeMapper.jobNameList();
		
		return JobList;
	}


	public List<DepartmentDTO> deptNameList() {
		
		List<DepartmentDTO> deptList = adminEmployeeMapper.deptNameList();
		
		return deptList;
	}


	public List<AdminEmployeeDTO> managerList() {
		
		List<AdminEmployeeDTO> managerList = adminEmployeeMapper.managerList();
		
		return managerList;
	}




	



}
