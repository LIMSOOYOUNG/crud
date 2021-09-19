package com.deft.crud.admin.model.service;

import com.deft.crud.member.model.dto.MemberDTO;

public interface AdminService {
	
	int checkUserId(String userId);

	int insertMember(MemberDTO member);
}
