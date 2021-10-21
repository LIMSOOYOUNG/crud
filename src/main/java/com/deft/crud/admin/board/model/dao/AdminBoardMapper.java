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

	/* 공지사항 등록페이지 공지사항 글번호 전달*/
	int selectSeqNoticeNo();

	/* 공지사항 수정 페이지에 로그인값 전달 */
	AdminBoardDTO noticeModifyform(int writeNo);

	/* 공지사항 수정 */
	int noticeModify(BoardDTO parameters);

	/* 게시글 파일 삭제 */
	int deleteFile(int writeNo);


	
	
}
