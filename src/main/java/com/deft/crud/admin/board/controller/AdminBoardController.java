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
	
	/* 공지사항 수정 */
	@GetMapping("noticemodify")
	public ModelAndView noticeModify(ModelAndView mv, @RequestParam int writeNo) {
		
		AdminBoardDTO boardDTO = adminBoardService.noticeModifyform(writeNo);
		
		System.out.println("여기는 수정페이지입니다." + boardDTO);
		
		mv.setViewName("admin/noticemodify");
		mv.addObject("boardDTO", boardDTO);
		
		return mv;
	}
	
	@PostMapping("noticemodify")
	public ModelAndView noticeModifyForm(ModelAndView mv, @ModelAttribute BoardDTO parameters
			,@AuthenticationPrincipal UserImpl loginInfo) {
		
		System.out.println("DTO 입니다: " + parameters);
		
		int result = adminBoardService.noticeModify(parameters);
		
		if(result > 0) {
			
			mv.setViewName("redirect:/board/noticedetail?writeNo=" + parameters.getWriteNo());
			
		}
		
		mv.addObject("parameters", parameters);
		
		return mv;
		
	}
	
	/* 공지사항 삭제 */
	@GetMapping("noticedelete")
	public void deletenotice() {}
	
	@PostMapping("noticedelete")
	public ModelAndView deleteNotice(ModelAndView mv, @ModelAttribute BoardDTO parameters) throws Exception {
		
		int writeNo = adminBoardService.deleteNotice(parameters.getWriteNo());
		
		mv.setViewName("redirect:/board/selectnotice");
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
}
