package com.deft.crud.admin.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.admin.board.model.service.AdminBoardService;

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
}
