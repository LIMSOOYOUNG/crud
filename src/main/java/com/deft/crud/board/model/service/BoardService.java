package com.deft.crud.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.board.model.dao.BoardMapper;
import com.deft.crud.board.model.dto.BoardDTO;

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
	public int insertFreeboard(BoardDTO board) {
		
		int result = boardMapper.insertFreeboard(board);
		
		return result;
	}
	
	/* 공지사항 등록 */

	/* 자유게시글 수정 */
	/* 공지사항 수정*/

	/* 자유게시글 삭제*/
	public int deleteFreeboard(int boardNo) {

		int result = boardMapper.deleteFreeboard(boardNo);

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


}
