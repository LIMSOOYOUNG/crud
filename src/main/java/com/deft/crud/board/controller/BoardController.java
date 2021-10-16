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
			@AuthenticationPrincipal UserImpl loginInfo, 
			@RequestParam List<MultipartFile> freeboardfileUpload,
			HttpServletRequest request, @RequestParam int boardAttatchNo) 
					throws Exception {
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		String filePath = root + "";
		
		File mkdir = new File(filePath);
		if(!mkdir.exists()) {
			mkdir.mkdir();
		}
		
		List<Map<String, String>> files = new ArrayList<>();
		for(int i = 0; i < freeboardfileUpload.size(); i++) {
			String originFileName = freeboardfileUpload.get(i).getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			Map<String, String> file = new HashMap<>();
			file.put("originFileName", originFileName);
			file.put("savedName", savedName);
			file.put("filePath", filePath);
			
			files.add(file);	
		}
		
		try {
			for(int i = 0; i < freeboardfileUpload.size(); i++) {
				Map<String, String> file = files.get(i);
				
				freeboardfileUpload.get(i).transferTo(new File(filePath + "\\" + file.get("savedName")));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			/* 실패시 파일 삭제 */
			for(int i = 0; i < freeboardfileUpload.size(); i++) {
				Map<String, String> file = files.get(i);
				
				new File(filePath + "\\" + file.get("savedName")).delete();
			}
			
		}
		
	    int loginEmpNo = loginInfo.getEmpNo();
	    parameters.setEmpNo(loginEmpNo);
	    
		int result = boardService.insertFreeboard(parameters);
		
		
		if(result>0) {
			
			mv.setViewName("redirect:/board/selectfreeboard");
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
	
	/* 자유게시글 수정*/
	@GetMapping("freeboardmodify")
	public ModelAndView freeboardModify(ModelAndView mv, @RequestParam int writeNo) throws Exception {
		
		BoardDTO boardDTO = boardService.freeboardModifyForm(writeNo);
		
		System.out.println("여기는 수정페이지입니다." + boardDTO);
		
		mv.setViewName("board/freeboardmodify");
		mv.addObject("boardDTO", boardDTO);
		
		
		return mv;
	}
	
	@PostMapping("freeboardmodify")
	public ModelAndView freeboardForm(ModelAndView mv, @ModelAttribute BoardDTO parameters,@AuthenticationPrincipal UserImpl loginInfo) {
		
		System.out.println("DTO 입니다: " + parameters);
		
		int result = boardService.freeboardModify(parameters);
		
		
		if(result > 0) {
			
			mv.setViewName("redirect:/board/freeboarddetail?writeNo=" + parameters.getWriteNo());
			
		}
		System.out.println(result);
		
		mv.addObject("parameters", parameters);
		
		return mv;
	}
	
	/* 자유게시글 삭제 */
	@GetMapping("deletefreeboard")
	public void deletefreeboard() {}
	
	@PostMapping("deletefreeboard")
	public ModelAndView deletefreeboard(ModelAndView mv, @ModelAttribute BoardDTO parameters)throws Exception {
		
		int writeNo = boardService.deleteFreeboard(parameters.getWriteNo());
		
		System.out.println("writeNo : " + writeNo);
		
		mv.setViewName("redirect:/board/selectfreeboard");
		
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
}

