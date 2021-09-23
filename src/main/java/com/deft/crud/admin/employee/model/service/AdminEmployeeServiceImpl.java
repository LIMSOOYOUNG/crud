package com.deft.crud.admin.employee.model.service;

import org.springframework.stereotype.Service;

@Service
public class AdminEmployeeServiceImpl implements AdminEmployeeService{

	public boolean checkUserId(String userId) {
		//dao 요청 해서 중복인지 아닌지 true / false
		return true;
	}
}
