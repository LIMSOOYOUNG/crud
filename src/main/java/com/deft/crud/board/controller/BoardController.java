package com.deft.crud.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
		
		/* BoardDTO를 리스트로 서비스에 값을 전달한다. */
		List<BoardDTO> freeboardList = boardService.selectFreeboard();
		
		/* 페이지 이동값을 board/selectfreeboard로 지정한다. */
		mv.setViewName("board/selectfreeboard");
		
		/* key값과 value값을 지정한다. */
		mv.addObject("freeboardList", freeboardList);
		
		return mv;
	}
	
	/* 공지사항 전체 조회 */
	@GetMapping("/selectnotice")
	public ModelAndView selectNotice(ModelAndView mv) {
		
		/* BoardDTO에 있는 값을 리스트로 서비스에 전달한다. */
		List<BoardDTO> noticeList = boardService.selectNotice();
		
		/* 페이지 이동값을 board/selectnotice로 지정한다. */
		mv.setViewName("board/selectnotice");
		
		/* key값과 value값을 지정한다. */
		mv.addObject("noticeList", noticeList);
		
		return mv;
	}
	
	/* 자유게시글 등록 */
	@GetMapping("/freeboardinsert")
	public ModelAndView freeboardInsert(ModelAndView mv) {
		
		/* writeNo 변수를 생성하여 서비스에 전달한다. */
		int writeNo = boardService.selectSeqFreeboardNo();
		
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
	
	@PostMapping("/freeboardinsert")
	public ModelAndView insertfreeboardForm(ModelAndView mv, @ModelAttribute BoardDTO parameters,
			@AuthenticationPrincipal UserImpl loginInfo,  @RequestParam int boardNo) 
					throws Exception {
		
	    int loginEmpNo = loginInfo.getEmpNo();
	    parameters.setEmpNo(loginEmpNo);
	    
	    /* parameters(BoardDTO)를 서비스에 전달한다. */
		int result = boardService.insertFreeboard(parameters);
		
		/* result값이 0 보다 클때 페이지 이동값을 /board/selectfreeboard 지정한다.*/
		if(result>0) {
			
			mv.setViewName("redirect:/board/selectfreeboard");
		}

		return mv;
	}
	

	/* 자유게시글 보기*/
	@GetMapping("/freeboarddetail")
	public ModelAndView freeboardDetail(ModelAndView mv, @RequestParam int writeNo) {
		
		/* writeNo의 값을 서비스에 전달한다. */
		BoardDTO boardDTO = boardService.freeboardDetail(writeNo); 
		
		/* 조회수 카운터 */
		boardService.freeboardviewCount(writeNo);
		
		/* 페이지 이동값을 board/freeboarddetail 지정한다. */
		mv.setViewName("board/freeboarddetail");
		
		/* key값과 value값을 지정한다. */
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
	/* GET방식과 POST방식을 쓴 이유는 페이지에 GET방식으로 writeNo를 전달하고  
	 * POST방식으로 수정된 값을 전달하기위해 썻습니다
	 * */
	@GetMapping("freeboardmodify")
	public ModelAndView freeboardModify(ModelAndView mv, @RequestParam int writeNo) throws Exception {
		
		/* BoardDTO를 writeNo 값을 담아서 서비스에 전달한다. */
		BoardDTO boardDTO = boardService.freeboardModifyForm(writeNo);
		
		/* 페이지 이동값을 board/freeboardmodify 지정한다. */
		mv.setViewName("board/freeboardmodify");
		
		mv.addObject("boardDTO", boardDTO);
		
		
		return mv;
	}
	
	@PostMapping("freeboardmodify")
	public ModelAndView freeboardForm(ModelAndView mv, @ModelAttribute BoardDTO parameters,@AuthenticationPrincipal UserImpl loginInfo) {
		
		/* parameters(BoardDTO)를 서비스에 전달한다. */
		int result = boardService.freeboardModify(parameters);
		
		/* result 값이 1 이상이면 페이지 이동값을 /board/freeboarddetail?writeNo로 지정한다. parameters에 있는 writeNo값을 받아온다.  */
		if(result > 0) {
			
			mv.setViewName("redirect:/board/freeboarddetail?writeNo=" + parameters.getWriteNo());
			
		}
		
		mv.addObject("parameters", parameters);
		
		return mv;
	}
	
	/* 자유게시글 삭제 */
	@GetMapping("deletefreeboard")
	public void deletefreeboard() {}
	
	@PostMapping("deletefreeboard")
	public ModelAndView deletefreeboard(ModelAndView mv, @ModelAttribute BoardDTO parameters)throws Exception {
		
		/* parameters(BoardDTO)에 있는 writeNo를 받아온다. */
		int writeNo = boardService.deleteFreeboard(parameters.getWriteNo());
		
		/* 페이지 이동값을 /board/selectfreeboard 지정한다. */
		mv.setViewName("redirect:/board/selectfreeboard");
		
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
}

