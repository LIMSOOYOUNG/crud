package com.deft.crud.admin.board.model.service;

import org.springframework.stereotype.Service;

import com.deft.crud.admin.board.model.dao.AdminBoardMapper;
import com.deft.crud.admin.board.model.dto.AdminBoardDTO;
import com.deft.crud.board.model.dto.BoardDTO;

@Service
public class AdminBoardService {

	private AdminBoardMapper adminBoardMapper;
	
	public AdminBoardService(AdminBoardMapper adminBoardMapper) {
		this.adminBoardMapper = adminBoardMapper;
	}
	
	/* 공지사항 등록*/
	public int noticeInsert(BoardDTO parameters) {
		
		int result = adminBoardMapper.noticeInsert(parameters);
		
		return result;
	}
	
	
	/* 공지사항 삭제 */
	public int deleteNotice(int boardNo) {

		int result = adminBoardMapper.deleteNotice(boardNo);

		return result;
	}

	public int selectSeqNoticeNo() {
		
		return adminBoardMapper.selectSeqNoticeNo();
	}


}
