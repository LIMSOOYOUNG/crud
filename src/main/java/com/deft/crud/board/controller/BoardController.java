package com.deft.crud.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.board.model.dto.BoardDTO;
import com.deft.crud.board.model.dto.BoardFileDTO;
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
		mv.setViewName("/board/selectfreeboard");
		
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
		mv.setViewName("/board/selectnotice");
		
		/* key값과 value값을 지정한다. */
		mv.addObject("noticeList", noticeList);
		
		return mv;
	}
	
	/* 자유게시글 등록 */
	@GetMapping("/freeboardinsert")
	public ModelAndView freeboardInsert(ModelAndView mv) {
		
		/* writeNo 변수를 생성하여 서비스에 전달한다. */
		int writeNo = boardService.selectSeqFreeboardNo();
		
		/* key값과 value값 지정 */
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
	
	@PostMapping("/freeboardinsert")
	public ModelAndView insertfreeboardForm(ModelAndView mv, @ModelAttribute BoardDTO parameters,
			@AuthenticationPrincipal UserImpl loginInfo,  @RequestParam int writeNo
			, @RequestParam(required = false) MultipartFile freeboardUpload
			,HttpServletRequest request) 
					throws Exception {
		
		/* 로그인한 정보 */
	    int loginEmpNo = loginInfo.getEmpNo();
	    
	    /* BoardDTO에 로그인 값을 넣는다. */
	    parameters.setEmpNo(loginEmpNo);

	    if(!freeboardUpload.isEmpty()) {
	    
	    	/* 절대경로를 변수에 초기화한다. */
		    String root = request.getSession().getServletContext().getRealPath("\\");
		    
		    /* 원본파일과 저장할 경로를 설정 */
		    String filePath  = root + "\\upload\\freeboard";
		    
		    /* 파일이 없으면 생성한다. */
		    File mkdir = new File(filePath);
			if(!mkdir.exists()) {
				mkdir.mkdirs();
			}
				/* 원본파일 */
				String originFileName = freeboardUpload.getOriginalFilename();
		    	
				/* 확장자 */
		    	String ext = originFileName.substring(originFileName.lastIndexOf("."));
		    	
		    	/* 파일명이 중복되지 않도록 설정해준다. */	
		    	String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		    	
		    	/* BoardFileDTO에 원본파일 이름, 저장할 이름, 파일경로, writeNo를 담는다. */
				BoardFileDTO file = new BoardFileDTO();
				file.setOriginalName(originFileName);
				file.setSavedName(savedName);
				file.setSavedPath(filePath);
				file.setWriteNo(writeNo);
				
				parameters.setBoardFileList(file);
		    
		    try {	
		    		/* 파일 저장 */
		    		freeboardUpload.transferTo(new File(filePath + "\\" + file.getSavedName()));
		    	
		    }catch (Exception e) {
		    	e.printStackTrace();
		    	
		    		/* 예외처리가 발생한다면 파일 삭제 */
					new File(filePath + "\\" + file.getSavedName()).delete();
	
		    }
	    }
	    
	    /* parameters(BoardDTO)값을 서비스에 전달한다. */
		int result = boardService.insertFreeboard(parameters);
		
		String message = "";
		
		if(result > 0) {
			
			message = "자유게시글등록에 성공하셨습니다";
		}
		
		/* 페이지 이동값 지정 */
		mv.setViewName("redirect:/board/selectfreeboard");

		return mv;
	}
	

	/* 자유게시글 보기*/
	@GetMapping("/freeboarddetail")
	public ModelAndView freeboardDetail(ModelAndView mv, @RequestParam int writeNo) {
		
		/* writeNo의 값을 서비스에 전달한다. */
		BoardDTO boardDTO = boardService.freeboardDetail(writeNo); 
		BoardFileDTO boardFileDTO = boardService.freeboardFile(writeNo);
		
		/* 조회수 증가 */
		boardService.freeboardviewCount(writeNo);
		
		/* 페이지 이동값 지정 */
		mv.setViewName("/board/freeboarddetail");
		
		/* key값과 value값을 지정한다. */
		mv.addObject("boardDTO", boardDTO);
		mv.addObject("boardFileDTO", boardFileDTO);
		
		return mv;
	}
	

	/* 공지사항 보기 */
	@GetMapping("/noticedetail")
	public ModelAndView noticedetail(ModelAndView mv, @RequestParam int writeNo) {
		
		/* writeNo값을 서비스 쪽으로 넘겨준다. */
		BoardDTO boardDTO = boardService.noticeDetail(writeNo);
		BoardFileDTO boardFileDTO = boardService.noticeFile(writeNo);
		
		/* 조회수 증가 */
		boardService.noticeviewCount(writeNo);
		
		/* 페이지 이동값 지정 */
		mv.setViewName("/board/noticedetail");
		
		/* key값과 value값을 지정한다. */
		mv.addObject("boardDTO", boardDTO);
		mv.addObject("boardFileDTO", boardFileDTO);
		
		return mv;
	}
	
	/* 자유게시글 수정*/
	/* GET방식과 POST방식을 쓴 이유는 페이지에 GET방식으로 writeNo를 전달하고  
	 * POST방식으로 수정된 값을 전달하기위해 썻습니다
	 * */
	@GetMapping("/freeboardmodify")
	public ModelAndView freeboardModify(ModelAndView mv, @RequestParam int writeNo) throws Exception {
		
		/* BoardDTO,BoardFileDTO를 writeNo 값을 담아서 서비스에 전달한다. */
		BoardDTO boardDTO = boardService.freeboardModifyForm(writeNo);
		BoardFileDTO boardFileDTO = boardService.freeboardFile(writeNo);
		
		/* 페이지 이동값을 board/freeboardmodify 지정한다. */
		mv.setViewName("/board/freeboardmodify");
		
		/* key값과 value값을 지정한다. */
		mv.addObject("boardDTO", boardDTO);
		mv.addObject("boardFileDTO", boardFileDTO);
		
		return mv;
	}
	
	@PostMapping("/freeboardmodify")
	public ModelAndView freeboardForm(ModelAndView mv, @ModelAttribute BoardDTO parameters,@AuthenticationPrincipal UserImpl loginInfo 
			,@RequestParam MultipartFile freeboardUpdate
			,HttpServletRequest request
			,RedirectAttributes rttr) {
		
		int result = 0;
		BoardFileDTO boardFileDTO = new BoardFileDTO();
		
		if(!freeboardUpdate.isEmpty()) {
			String root = request.getSession().getServletContext().getRealPath("\\");
			
			/* 원본파일을 저장할 경로 설정 */
			String fileUploadDirectory = root + "\\upload\\freeboard";
			
			File directory = new File(fileUploadDirectory);
			
			if(directory.exists()) {
				System.out.println("폴더생성 : " + directory.mkdirs());
			}
			
			/* 원본 파일 */
			String originFileName = freeboardUpdate.getOriginalFilename();
			
			/* 텍스트 확장자 */
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			
			/* 파일명이 중복되지 않도록 설정 */
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			boardFileDTO.setOriginalName(originFileName);
			boardFileDTO.setSavedName(savedName);
			boardFileDTO.setSavedPath(fileUploadDirectory);
			boardFileDTO.setWriteNo(parameters.getWriteNo());
			
			try {
				
				/* 파일 저장 */
				freeboardUpdate.transferTo(new File(fileUploadDirectory + "\\" + savedName));
				
			}catch (IllegalStateException e) {
				
				e.printStackTrace();
				
			} catch (IOException e) {
				
				/* 예외처리가 발생하면 파일 삭제 */
				new File(fileUploadDirectory + "\\" + savedName).delete();
				e.printStackTrace();
			}
			
			result = boardService.modifyFreeboardFile(parameters, boardFileDTO);
		}else {
			
			result = boardService.freeboardModify(parameters);
		}
		
		String message= "";
		
		if(result > 0) {
			message = "게시판 수정 완료";
		}else {
			message = "게시판 수정 실패";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/board/freeboarddetail\\?writeNo=" + parameters.getWriteNo());
		
		return mv;
	}
	
	/* 자유게시글 삭제 */
	@PostMapping("deletefreeboard")
	public ModelAndView deletefreeboard(ModelAndView mv, @ModelAttribute BoardDTO parameters)throws Exception {
		
		/* parameters(BoardDTO)에 있는 writeNo를 받아온다. */
		int writeNoAttatch = boardService.deleteFile(parameters.getWriteNo());
		int writeNo = boardService.deleteFreeboard(parameters.getWriteNo());
		
		/* 페이지 이동값을 /board/selectfreeboard 지정한다. */
		mv.setViewName("redirect:/board/selectfreeboard");
		
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
}

