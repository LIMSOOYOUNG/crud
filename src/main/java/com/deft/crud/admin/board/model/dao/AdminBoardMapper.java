package com.deft.crud.admin.board.model.dao;

import com.deft.crud.admin.board.model.dto.AdminBoardDTO;

public interface AdminBoardMapper {

	/* 공지사항 삭제*/
	int deleteNotice(int boardNo);

	/* 공지사항 등록 */
	int noticeInsert(AdminBoardDTO adminBoardDTO);

	
	
}
