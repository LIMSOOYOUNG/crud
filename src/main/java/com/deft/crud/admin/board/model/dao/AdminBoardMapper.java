package com.deft.crud.admin.board.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.admin.board.model.dto.AdminBoardDTO;
import com.deft.crud.board.model.dto.BoardDTO;

@Mapper
public interface AdminBoardMapper {

	/* 공지사항 삭제*/
	int deleteNotice(int boardNo);

	/* 공지사항 등록 */
	int noticeInsert(BoardDTO parameters);

	int selectSeqNoticeNo();


	
	
}
