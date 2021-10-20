package com.deft.crud.admin.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	/* GET방식을 쓴 이유는 writeNo라는 게시판 시퀀스 번호를 넘겨주기 위해서 썻다. */
	@GetMapping("noticeinsert")
	public ModelAndView noticeinsert(ModelAndView mv) {
		
		int writeNo = adminBoardService.selectSeqNoticeNo();
		
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
	/* POST방식을 써서 입력된 값을 DB에 전달 시켜준다. */
	@PostMapping("noticeinsert")
	public ModelAndView noticeInsertForm(ModelAndView mv, @ModelAttribute BoardDTO parameters,
			@AuthenticationPrincipal UserImpl loginInfo) 
					throws Exception{
		
		/* 로그인한 정보 */
		int loginEmpNo = loginInfo.getEmpNo();
		
		/* BoardDTO에 로그인 값을 넣는다. */
		parameters.setEmpNo(loginEmpNo);
		
		/* 서비스에 BoardDTO를 담아서 보내준다. */
		int result = adminBoardService.noticeInsert(parameters);
		
		if(result > 0) {
			mv.setViewName("redirect:/board/selectnotice");
		}
		
		return mv;
	}
	
	/* 공지사항 수정 */
	/* GET방식과 POST방식을 쓴 이유는 페이지에 GET방식으로 writeNo를 전달하고  
	 * POST방식으로 수정된 값을 전달하기위해 썻습니다
	 * */
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
		
		/* parameters를 서비스에 전달한다. */
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
		
		/* parameters에 writeNo값을 받아온다. */
		int writeNo = adminBoardService.deleteNotice(parameters.getWriteNo());
		
		/* /board/selectnotice로 페이지 이동값을 지정한다. */
		mv.setViewName("redirect:/board/selectnotice");
		
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
}
