package com.deft.crud.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO findMemberById(String username);

	String selectEmpId(MemberDTO userInfo);
}
