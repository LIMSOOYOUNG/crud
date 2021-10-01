package com.deft.crud.admin.board.model.service;

import com.deft.crud.admin.board.model.dao.AdminBoardMapper;
import com.deft.crud.admin.board.model.dto.AdminBoardDTO;

public class AdminBoardService {

	private AdminBoardMapper adminBoardMapper;
	
	public AdminBoardService(AdminBoardMapper adminBoardMapper) {
		this.adminBoardMapper = adminBoardMapper;
	}
	
	
	
	/* 공지사항 삭제 */
	public int deleteNotice(int boardNo) {

		int result = adminBoardMapper.deleteNotice(boardNo);

		return result;
	}



	public int noticeInsert(AdminBoardDTO adminBoardDTO) {
		
		int result = adminBoardMapper.noticeInsert(adminBoardDTO);
		
		return result;
	}

}
