package com.deft.crud.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.deft.crud.board.model.dao.BoardMapper;
import com.deft.crud.board.model.dto.BoardDTO;
import com.deft.crud.board.model.dto.BoardFileDTO;

@Service
public class BoardService {

	private BoardMapper boardMapper;

	@Autowired
	public BoardService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	/* 자유게시판 전체 조회 */
	public List<BoardDTO> selectFreeboard() {

		List<BoardDTO> boardList = boardMapper.selectFreeboard();

		return boardList;
	}

	/* 공지사항 전체 조회 */
	public List<BoardDTO> selectNotice() {

		List<BoardDTO> noticeList = boardMapper.selectNotice();

		return noticeList;
	}

	/* 자유게시글 등록 */
	@Transactional
	public int insertFreeboard(BoardDTO board) {
		
		System.out.println("sdasdasdasda" + board);
		
		int result = 0;
		
//		int boardResult = boardMapper.insertFreeboard(board);
		
//		if(!board.getBoardFileList().isEmpty()) {
//			
//			int boardFile = 0;
//			
//			for(BoardFileDTO file : board.getBoardFileList()) {
//				
//				boardFile += boardMapper.insertFile(file);
//				
//			}
//			
//			if(boardResult > 0 && boardFile > board.getBoardFileList().size()) {
//				
//				result = 1;
//				
//			}
//		}else {
//			if(boardResult > 0) {
//				result = 1;
//			}
//		}
		
		return result;
	}
	



	/* 자유게시글 보기*/
	public BoardDTO freeboardDetail(int writeNo) {
		
	    BoardDTO freeboardDTO = boardMapper.freeboardDetail(writeNo);
		
	    System.out.println(freeboardDTO);
	    
		return freeboardDTO;
	}

	/* 공지사항 보기*/
	public BoardDTO noticeDetail(int writeNo) {
		
		BoardDTO noticeboardDTO = boardMapper.noticeDetail(writeNo);
		
		return noticeboardDTO;
	}

	public void freeboardviewCount(int writeNo) {
		
		boardMapper.freeboardviewCount(writeNo);
	}

	public void noticeviewCount(int writeNo) {
		
		boardMapper.noticeviewCount(writeNo);
		
	}

	public int selectSeqFreeboardNo() {
		
		return boardMapper.selectSeqFreeboardNo();

	}
	/* 수정페이지에 값전달 */
	public BoardDTO freeboardModifyForm(int writeNo) {
		 
		BoardDTO boardDTO = boardMapper.freeboardModifyForm(writeNo);
		
		return boardDTO;
	}

	/* 자유게시글 수정 서비스*/
	public int freeboardModify(BoardDTO parameters) {
		
		System.out.println("서비스 쪽의 DTO 입니다:" + parameters);
		
		int result = boardMapper.freeboardModify(parameters);
		
		
		return result;
	}

	/* 자유게시글 삭제 */
	public int deleteFreeboard(int writeNo) {
		
		int result = boardMapper.deleteFreeboard(writeNo);
		
		return result;
	}





	







}
