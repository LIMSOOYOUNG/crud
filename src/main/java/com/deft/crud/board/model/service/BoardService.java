package com.deft.crud.board.model.service;

import java.util.List;

import com.deft.crud.board.model.dto.BoardDTO;

public interface BoardService {

	List<BoardDTO> selectBoardList(BoardDTO boardDTO);

}
