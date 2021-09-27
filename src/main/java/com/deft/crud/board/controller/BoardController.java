package com.deft.crud.board.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.board.model.dto.BoardDTO;
import com.deft.crud.board.model.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	/* 자유게시판 전체 조회 */
	@GetMapping("/selectfreeboard")
	public ModelAndView selectFreeboard(ModelAndView mv) {
		
		List<BoardDTO> freeboardList = boardService.selectFreeboard();
		
		mv.setViewName("board/selectfreeboard");
		mv.addObject("freeboardList", freeboardList);
		
		return mv;
	}
	
	/* 공지사항 전체 조회 */
	@GetMapping("/selectnotice")
	public ModelAndView selectNotice(ModelAndView mv) {
		
		List<BoardDTO> noticeList = boardService.selectNotice();
		
		mv.setViewName("board/selectnotice");
		mv.addObject("noticeList", noticeList);
		
		return mv;
	}
	
	/* 자유게시글 등록 */
	@GetMapping("insertfreeboard")
	public void insertfreeboard() {}

	@PostMapping("insertfreeboard")
	public ModelAndView insertfreeboardForm(ModelAndView mv, RedirectAttributes rttr,
			@RequestParam String boardName, @RequestParam String writerName,
			@RequestParam java.util.Date writerDate, @RequestParam String contents) 
					throws UnsupportedEncodingException{

		BoardDTO board = new BoardDTO();

		board.setBoardName(boardName);
		board.setWriterName(writerName);
		board.setWriteDate(writerDate);
		board.setContents(contents);

		int result = boardService.insertFreeboard(board);

		if(result>0) {
			rttr.addFlashAttribute("flashMessage", "성공!!");
		}else {
			rttr.addFlashAttribute("flashMessage", "실패!!");
		}
		mv.setViewName("redirect:/insertfreeboard");

		return mv;
	}
	
	/* 자유게시글 삭제 */
	@GetMapping("deletefreeboard")
	public void deletefreeboard() {}
	
	@PostMapping("deletefreeboard")
	public ModelAndView deletefreeboardform(ModelAndView mv, RedirectAttributes rttr
											, @RequestParam(defaultValue = "0")int BoardNo) {
		
		int result = boardService.deleteFreeboard(BoardNo);
		
		if(result > 0) {
			rttr.addAttribute("flashMessage", "자유게시글 삭제성공!!");
		}else {
			rttr.addFlashAttribute("flashMessage", "자유게시글 삭제실패!!");
		}
		mv.setViewName("board/selectfreeboard");
		
		return mv;
	}
	

}
