package com.deft.crud.board.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.board.model.dto.BoardDTO;
import com.deft.crud.board.model.service.BoardService;
import com.deft.crud.member.model.service.UserImpl;

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
	public ModelAndView freeboardInsert(ModelAndView mv) {
		
		int writeNo = boardService.selectSeqFreeboardNo();
		
		mv.addObject("writeNo", writeNo);
		
		System.out.println(writeNo);
		
		return mv;
	}
	
	
	@PostMapping("/freeboardinsert")
	public ModelAndView insertfreeboardForm(ModelAndView mv, @ModelAttribute BoardDTO parameters,
			@AuthenticationPrincipal UserImpl loginInfo) 
					throws Exception {
		
	    int loginEmpNo = loginInfo.getEmpNo();
	    parameters.setEmpNo(loginEmpNo);
	    
		int result = boardService.insertFreeboard(parameters);
		
		if(result>0) {
			
			mv.setViewName("redirect:/board/selectfreeboard");
		}

		return mv;
	}
	
	/* 자유게시글 삭제 */
	@GetMapping("/deletefreeboard")
	public ModelAndView deletefreeboard(ModelAndView mv) {
		
		int writeNo = boardService.selectSeqFreeboardNo();
		
		mv.addObject("writeNo", writeNo);
		
		System.out.println(writeNo);
		
		return mv;
	}
	
	@PostMapping("/deletefreeboard")
	public ModelAndView deletefreeboardform(ModelAndView mv) {
		
//		int result = boardService.deleteFreeboard();
		
//		if(result > 0) {
//			mv.setViewName("board/selectfreeboard");
//		}
		
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
	
	/* 자유게시글 수정*/
	@GetMapping("freeboardmodify")
	public void freeboardModify() {}
	
	@PostMapping("freeboardmodify")
	public ModelAndView freeboardForm(ModelAndView mv, @ModelAttribute BoardDTO parameters,@AuthenticationPrincipal UserImpl loginInfo) {
		
		int loginEmpNo = loginInfo.getEmpNo();
		
		parameters.setEmpNo(loginEmpNo);
		
		int result = boardService.freeboardModify(parameters);
		
		
		if(result > 0) {
			
			mv.setViewName("redirect:/board/selectfreeboard");
			
		}
		
		mv.addObject("parameters", parameters);
		
		return mv;
	}
	
}
