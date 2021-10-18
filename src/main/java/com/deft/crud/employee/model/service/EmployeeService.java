package com.deft.crud.employee.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.deft.crud.employee.model.dao.EmployeeMapper;
import com.deft.crud.employee.model.dto.EmployeeDTO;
import com.deft.crud.member.model.service.UserImpl;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;
    
    public EmployeeService(EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder) {
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<EmployeeDTO> selectEmployee(){

        List<EmployeeDTO> employeeList = employeeMapper.selectEmployee();

        System.out.println("list");

        return employeeList;
    }

    /* 비밀번호 변경 */
	public int modifyPwd(UserImpl userInfo, String pwdCheck1) {

		EmployeeDTO empInfo = new EmployeeDTO();

		String encodedPassword =  passwordEncoder.encode(pwdCheck1);
		
		empInfo.setEmployeePwd(encodedPassword);
		empInfo.setEmployeeId(userInfo.getEmpId());
		
		int result = employeeMapper.modifyPwd(empInfo);
		
		return result;
	}
}