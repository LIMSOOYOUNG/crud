package com.deft.crud.admin.board.controller;

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

import com.deft.crud.admin.board.model.dto.AdminBoardDTO;
import com.deft.crud.admin.board.model.service.AdminBoardService;
import com.deft.crud.board.model.dto.BoardDTO;
import com.deft.crud.member.model.service.UserImpl;

@Controller
@RequestMapping("admin")
public class AdminBoardController {
	
	private AdminBoardService adminBoardService;
	
	@Autowired
	public AdminBoardController(AdminBoardService adminBoardService) {
		this.adminBoardService = adminBoardService;
	}
	
	
	/* 공지사항 삭제 */
	@GetMapping("deletenotice")
	public void deleteNotice() {}
	
	@PostMapping("deletenotice")
	public ModelAndView deleteNoticeForm(ModelAndView mv, RedirectAttributes rttr
			, @RequestParam(defaultValue = "0")int BoardNo) {
		
		int result = adminBoardService.deleteNotice(BoardNo);
		
		if(result > 0) {
			rttr.addAttribute("flashMessage", "공지사항 삭제성공!!");
		}else {
			rttr.addFlashAttribute("flashMessage", "공지사항 삭제실패!!");
		}
		mv.setViewName("board/selectfreeboard");
		
		
		
		return mv;
	}
	
	/* 공지사항 등록 */
	@GetMapping("noticeinsert")
	public ModelAndView noticeinsert(ModelAndView mv) {
		
		int writeNo = adminBoardService.selectSeqNoticeNo();
		
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
	@PostMapping("noticeinsert")
	public ModelAndView noticeInsertForm(ModelAndView mv, @ModelAttribute BoardDTO parameters,
			@AuthenticationPrincipal UserImpl loginInfo) 
					throws Exception{
		
		int loginEmpNo = loginInfo.getEmpNo();
		parameters.setEmpNo(loginEmpNo);
		
		int result = adminBoardService.noticeInsert(parameters);
		
		if(result > 0) {
			mv.setViewName("redirect:/board/selectnotice");
		}
		
		return mv;
	}
	
	
	
}
