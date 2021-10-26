package com.deft.crud.admin.board.controller;

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

import com.deft.crud.admin.board.model.dto.AdminBoardDTO;
import com.deft.crud.admin.board.model.service.AdminBoardService;
import com.deft.crud.board.model.dto.BoardDTO;
import com.deft.crud.board.model.dto.BoardFileDTO;
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
		
		/* writeNo 변수를 생성하여 서비스에 전달한다. */
		int writeNo = adminBoardService.selectSeqNoticeNo();
		
		/* key값과 value값 지정 */
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
	/* POST방식을 써서 입력된 값을 DB에 전달 시켜준다. */
	@PostMapping("noticeinsert")
	public ModelAndView noticeInsertForm(ModelAndView mv, @ModelAttribute BoardDTO parameters,
			@AuthenticationPrincipal UserImpl loginInfo, @RequestParam int writeNo
			, @RequestParam(required = false) MultipartFile noticeUpload
			, HttpServletRequest request) throws Exception{
		
		/* 로그인한 정보 */
		int loginEmpNo = loginInfo.getEmpNo();
		
		/* BoardDTO에 로그인 값을 넣는다. */
		parameters.setEmpNo(loginEmpNo);
		
		 if(!noticeUpload.isEmpty()) {
			 
			 /* 절대경로를 변수에 초기화한다. */
			 String root = request.getSession().getServletContext().getRealPath("\\");
			 
			 /* 원본파일과 저장할 경로를 설정 */
			 String filePath  = root + "\\upload\\notice";
			 
			 /* 파일이 없으면 생성한다. */
			 File mkdir = new File(filePath);
				if(!mkdir.exists()) {
					mkdir.mkdirs();
				}
				
				/* 원본파일 */
				String originFileName = noticeUpload.getOriginalFilename();
		    	
				/* 확장자 */
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				
				/* 파일명이 중복되지 않도록 설정해준다. */	
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
	
				BoardFileDTO file = new BoardFileDTO();
				file.setOriginalName(originFileName);
				file.setSavedName(savedName);
				file.setSavedPath(filePath);
				file.setWriteNo(writeNo);
				
	
				parameters.setBoardFileList(file);
				
				try {
			    		
					/* 파일 저장 */
					noticeUpload.transferTo(new File(filePath + "\\" + file.getSavedName()));
			    	
			    }catch (Exception e) {
			    	e.printStackTrace();
						
			    		/* 예외처리가 발생한다면 파일 삭제 */
						new File(filePath + "\\" + file.getSavedName()).delete();
		
			    }
		 }
		
		/* 서비스에 BoardDTO를 담아서 보내준다. */
		int result = adminBoardService.noticeInsert(parameters);
		
		String message = "";
		
		if(result > 0) {
			
			message = "공지사항 등록에 성공하셨습니다";
		}
		
		mv.setViewName("redirect:/board/selectnotice");
		return mv;
	}
	
	/* 공지사항 수정 */
	/* GET방식과 POST방식을 쓴 이유는 페이지에 GET방식으로 writeNo를 전달하고  
	 * POST방식으로 수정된 값을 전달하기위해 썻습니다
	 * */
	@GetMapping("noticemodify")
	public ModelAndView noticeModify(ModelAndView mv, @RequestParam int writeNo) {
		
		/* BoardDTO,BoardFileDTO를 writeNo 값을 담아서 서비스에 전달한다. */
		AdminBoardDTO boardDTO = adminBoardService.noticeModifyform(writeNo);
		BoardFileDTO boardFileDTO = adminBoardService.noticeFileLook(writeNo);
		
		/* 페이지 이동값 지정 */
		mv.setViewName("admin/noticemodify");
		
		/* key값과 value값을 지정한다. */
		mv.addObject("boardDTO", boardDTO);
		mv.addObject("boardFileDTO", boardFileDTO);
		
		return mv;
	}
	
	@PostMapping("noticemodify")
	public ModelAndView noticeModifyForm(ModelAndView mv, @ModelAttribute BoardDTO parameters
			,@AuthenticationPrincipal UserImpl loginInfo
			,@RequestParam MultipartFile noticeUpdate
			,HttpServletRequest request
			,RedirectAttributes rttr) {
		int result = 0;
		BoardFileDTO boardFileDTO = new BoardFileDTO();
		if(!noticeUpdate.isEmpty()) {
			String root = request.getSession().getServletContext().getRealPath("\\");
			
			String fileUploadDirectory = root + "\\upload\\notice";
			
			File directory = new File(fileUploadDirectory);
			
			if(directory.exists()) {

			}
			
			/* 원본 파일 */
			String originFileName = noticeUpdate.getOriginalFilename();
			
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
				noticeUpdate.transferTo(new File(fileUploadDirectory + "\\" + savedName));
				
			}catch (IllegalStateException e) {
				
				e.printStackTrace();
				
			} catch (IOException e) {
				
				/* 예외처리가 발생하면 파일 삭제 */
				new File(fileUploadDirectory + "\\" + savedName).delete();
				e.printStackTrace();
			}
			
			result = adminBoardService.noticeTextFileModify(parameters, boardFileDTO);
		}else {
			result = adminBoardService.noticeModify(parameters);
		}
		
		String message= "";
		
		if(result > 0) {
			message = "게시판 수정 완료";
		}else {
			message = "게시판 수정 실패";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/board/noticedetail\\?writeNo=" + parameters.getWriteNo());
		
		return mv;
		
	}
	
	/* 공지사항 삭제 */
	@PostMapping("noticedelete")
	public ModelAndView deleteNotice(ModelAndView mv, @ModelAttribute BoardDTO parameters) throws Exception {
		
		/* parameters에 writeNo값을 받아온다. */
		int writeNoAttatch = adminBoardService.deleteFile(parameters.getWriteNo());
		int writeNo = adminBoardService.deleteNotice(parameters.getWriteNo());
		
		/* /board/selectnotice로 페이지 이동값을 지정한다. */
		mv.setViewName("redirect:/board/selectnotice");
		
		mv.addObject("writeNo", writeNo);
		
		return mv;
	}
	
}
