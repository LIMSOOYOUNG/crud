package com.deft.crud.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.board.model.dto.BoardDTO;
import com.deft.crud.board.model.service.BoardService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService boardService;
	
	/* 자유게시판 조회 */
	@GetMapping(value = "selectBoard")
	public String boardList(BoardDTO boardDTO, Model model) throws Exception {
		
		List<BoardDTO> boardList = boardService.selectBoardList(boardDTO);
		
		model.addAttribute("boardlist", boardList);
		
		return "selectBoard";
	}
	
	
	/* 자유게시판 등록 */
	@GetMapping(value = "writeBoard")
	public String writeBoardForm(ModelAndView mv) throws Exception {
		
		mv.setViewName("주소 값");
		
		return "writeBoard";
	}
	
}
