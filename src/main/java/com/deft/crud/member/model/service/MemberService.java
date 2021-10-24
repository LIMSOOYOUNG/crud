package com.deft.crud.member.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.deft.crud.member.model.dto.MemberDTO;

public interface MemberService extends UserDetailsService {

	String selectEmpId(MemberDTO userInfo);
}