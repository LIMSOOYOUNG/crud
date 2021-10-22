package com.deft.crud.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.deft.crud.board.model.dto.BoardDTO;
import com.deft.crud.board.model.dto.BoardFileDTO;


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

	/* 자유게시글 수정 페이지 전달 */
	BoardDTO freeboardModifyForm(int writeNo);
	
	/* 자유게시글 수정*/
	int freeboardModify(BoardDTO parameters);

	/* 자유게시글 삭제 */
	int deleteFreeboard(int writeNo);

	/* 파일 업로드 */
	int insertFile(BoardFileDTO file);

	/* 게시글 삭제시 파일 삭제 */
	int deleteFile(int writeNo);

	/* 게시글 파일 */
	BoardFileDTO detailFile(int writeNo);






	


}
