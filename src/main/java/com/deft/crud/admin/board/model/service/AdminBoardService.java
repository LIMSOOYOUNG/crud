package com.deft.crud.admin.board.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deft.crud.admin.board.model.dao.AdminBoardMapper;
import com.deft.crud.admin.board.model.dto.AdminBoardDTO;
import com.deft.crud.board.model.dto.BoardDTO;
import com.deft.crud.board.model.dto.BoardFileDTO;

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
	@Transactional
	public int noticeInsert(BoardDTO parameters) {
		
		int result = 0;
		
		int boardResult = adminBoardMapper.noticeInsert(parameters);
		
		BoardFileDTO file = parameters.getBoardFileList();
		
		if(file != null) {
			
			int boardFile = adminBoardMapper.insertFile(file);
			
		
			if(boardResult > 0 && boardFile > 0) {
				
				result = 1;
				
			}
		}else {
			if(boardResult > 0) {
				result = 1;
			}
		}
		
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
	
	/* 게시글 파일 삭제 */
	public int deleteFile(int writeNo) {
		
		int result = adminBoardMapper.deleteFile(writeNo);
		
		return result;
	}
	public BoardFileDTO noticeFileLook(int writeNo) {
		
		BoardFileDTO result = adminBoardMapper.noticeFileLook(writeNo);
		
		return result;
	}
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
			rollbackFor = {Exception.class})
	public int noticeTextFileModify(BoardDTO parameters, BoardFileDTO boardFileDTO) {
		
		int result = 0;
		
		int modifyNoticeText = adminBoardMapper.modifyNoticeText(parameters);
		
		int modifyNoticeFile = adminBoardMapper.modifyNoticeFile(boardFileDTO);
		
		if(modifyNoticeFile > 0 && modifyNoticeText > 0) {
			
			result = 1;
			
		}
		
		return result;
	}



	


}
