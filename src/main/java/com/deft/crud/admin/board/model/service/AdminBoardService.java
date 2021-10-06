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
	/* 공지사항 등록 writeNo 전달 */
	public int selectSeqNoticeNo() {
		
		return adminBoardMapper.selectSeqNoticeNo();
	}
	
	/* 공지사항 등록*/
	public int noticeInsert(BoardDTO parameters) {
		
		int result = adminBoardMapper.noticeInsert(parameters);
		
		return result;
	}
	

	/* 공지사항 페이지 값 전달*/
	public AdminBoardDTO noticeModifyform(int writeNo) {
		
		AdminBoardDTO adminBoardDTO = adminBoardMapper.noticeModifyform(writeNo);
		
		return adminBoardDTO;
	}

	public int noticeModify(BoardDTO parameters) {
		
		int result = adminBoardMapper.noticeModify(parameters);
		
		return result;
	}

	public int deleteNotice(int writeNo) {
		
		int result = adminBoardMapper.deleteNotice(writeNo);
		
		return result;
	}


	


}
