package com.deft.crud.admin.adminemployee.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.admin.adminemployee.model.dao.AdminEmployeeMapper;
import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;



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


	public List<AdminEmployeeDTO> detailselect(int employeeNo) {
		
		List<AdminEmployeeDTO> employeList = adminEmployeeMapper.detailselect(employeeNo);
		
		return employeList;
	}



}
