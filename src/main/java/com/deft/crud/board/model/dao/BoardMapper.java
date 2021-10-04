package com.deft.crud.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.board.model.dto.BoardDTO;


@Mapper
public interface BoardMapper {

	/* 자유게시판 조회*/
	List<BoardDTO> selectFreeboard();

	/* 공지사항 조회*/
	List<BoardDTO> selectNotice();
	
	/* 자유게시글 등록*/
	int insertFreeboard(BoardDTO board);

	/* 자유게시글 보기*/
	BoardDTO freeboardDetail(int writeNo);

	/* 공지사항 보기 */
	BoardDTO noticeDetail(int writeNo);

	/* 자유게시글 조회수 증가 */
	void freeboardviewCount(int writeNo);
	
	/* 공지사항 조회수 증가*/
	void noticeviewCount(int writeNo);

	/* writeNo값을 보내주는 mapper*/
	int selectSeqFreeboardNo();

	/* 자유게시글 수정*/
	int freeboardModify(BoardDTO parameters);



	


}
