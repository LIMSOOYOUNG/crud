package com.deft.crud.admin.adminemployee.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.deft.crud.admin.adminemployee.model.dao.AdminEmployeeMapper;
import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;
import com.deft.crud.admin.adminemployee.model.dto.DepartmentDTO;
import com.deft.crud.admin.adminemployee.model.dto.JobDTO;
import com.deft.crud.member.model.dto.MemberDTO;

@Service
public class AdminEmployeeService {

	private AdminEmployeeMapper adminEmployeeMapper;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminEmployeeService(AdminEmployeeMapper adminEmployeeMapper, PasswordEncoder passwordEncoder) {
		this.adminEmployeeMapper = adminEmployeeMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	/* ID중복 체크 여부 */
	public int checkUserId(String empId) {
		
		return adminEmployeeMapper.checkUserId(empId);
	}
	
	/* 사원 등록 */
	public int insertMember(MemberDTO member) {
		
		String encodedPassword = passwordEncoder.encode(member.getEmpPwd());
	      
	      member.setEmpPwd(encodedPassword);
	      
	      int result = adminEmployeeMapper.insertMember(member);
		
		
		return result;
	}
	
	/* 직급 목록 조회 */
	public List<MemberDTO> selectJobList() {

		List<MemberDTO> jobList = adminEmployeeMapper.selectJobList();
		
		return jobList;
	}

	/* 부서목록 조회 */
	public List<MemberDTO> selectDeptList() {

		List<MemberDTO> deptList = adminEmployeeMapper.selectDeptList();
		
		return deptList;
	}

	/* 권한목록 조회 */
	public List<MemberDTO> selectAuthorityList() {

		List<MemberDTO> authorityList = adminEmployeeMapper.selectAuthorityList();
		
		return authorityList;
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

	public List<MemberDTO> selectManagerList(String deptCode) {
		
		List<MemberDTO> managerList = adminEmployeeMapper.selectManagerList(deptCode);
		
		return managerList;
	}

	

	

	




	



}
