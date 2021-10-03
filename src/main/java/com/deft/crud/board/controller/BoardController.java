package com.deft.crud.board.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	/* 자유게시판 전체 조회 */
	@GetMapping("/selectfreeboard")
	public ModelAndView selectFreeboard(ModelAndView mv) {
		
		List<BoardDTO> freeboardList = boardService.selectFreeboard();
		System.out.println("freeboardList" + freeboardList);
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
	@GetMapping("/freeboardinsert")
	public void freeboardInsert() {}
	
	
	@PostMapping("/freeboardinsert")
	public ModelAndView insertfreeboardForm(ModelAndView mv, HttpSession session,
			@RequestParam String boardName) 
					throws Exception {

	    String writer = (String) session.getAttribute("emp_id");
		
		BoardDTO board = new BoardDTO();
		board.setEmpName(writer);
	
		System.out.println(board);
		
		System.out.println(board);
		int result = boardService.insertFreeboard(board);

		if(result>0) {
			mv.setViewName("redirect:/board/selectfreeboard?writer=" + writer);
		}

		return mv;
	}
	
	/* 자유게시글 삭제 */
	@GetMapping("/deletefreeboard")
	public void deletefreeboard() {}
	
	@PostMapping("/deletefreeboard")
	public ModelAndView deletefreeboardform(ModelAndView mv, RedirectAttributes rttr
											, @RequestParam(defaultValue = "0")int BoardNo) {
		
		int result = boardService.deleteFreeboard(BoardNo);
		
		if(result > 0) {
			mv.setViewName("board/selectfreeboard");
		}
		
		return mv;
	}
	

	/* 자유게시글 보기*/
	@GetMapping("/freeboarddetail")
	public ModelAndView freeboardDetail(ModelAndView mv, @RequestParam int writeNo) {
		
		BoardDTO boardDTO = boardService.freeboardDetail(writeNo); 
		
		boardService.freeboardviewCount(writeNo);
		
		mv.setViewName("board/freeboarddetail");
		mv.addObject("boardDTO", boardDTO);
		
		
		return mv;
	}
	

	/* 공지사항 보기 */
	@GetMapping("/noticedetail")
	public ModelAndView noticedetail(ModelAndView mv, @RequestParam int writeNo) {
		
		BoardDTO boardDTO = boardService.noticeDetail(writeNo);
		
		boardService.noticeviewCount(writeNo);
		
		mv.setViewName("board/noticedetail");
		mv.addObject("boardDTO", boardDTO);
		
		return mv;
	}
	
	
}
