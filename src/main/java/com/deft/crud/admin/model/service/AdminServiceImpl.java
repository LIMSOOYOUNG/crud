package com.deft.crud.admin.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.deft.crud.admin.model.dao.AdminMapper;
import com.deft.crud.member.model.dto.MemberDTO;

@Service
public class AdminServiceImpl implements AdminService{

	private AdminMapper mapper;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminServiceImpl(AdminMapper mapper, PasswordEncoder passwordEncoder) {
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public int checkUserId(String userId) {				
	
		int result = mapper.checkUserId(userId); 	//dao 요청 해서 중복된 아이디가 존재하는지(몇개있는지) 확인
		
		return result;
		
	}

	@Override
	public int insertMember(MemberDTO member) {

		String encodedPassword = passwordEncoder.encode(member.getEmpPwd());
		
		member.setEmpPwd(encodedPassword);
		
		int result = mapper.insertMember(member);
				
		return result;
	}
}