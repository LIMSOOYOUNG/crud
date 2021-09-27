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

	/* 자유게시글 삭제 */
	int deleteFreeboard(int boardNo);


}
