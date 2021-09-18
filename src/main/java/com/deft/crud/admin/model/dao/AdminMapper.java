package com.deft.crud.admin.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.member.model.dto.MemberDTO;

@Mapper
public interface AdminMapper {

	int checkUserId(String userId);

	int insertMember(MemberDTO member);

}
