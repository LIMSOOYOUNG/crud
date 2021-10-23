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
		
		int writeNo = adminBoardService.selectSeqNoticeNo();
		
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
			 
			 String root = request.getSession().getServletContext().getRealPath("\\");
			 
			 String filePath  = root + "\\upload\\notice";
			 
			 File mkdir = new File(filePath);
				if(!mkdir.exists()) {
					mkdir.mkdirs();
				}
				
				String originFileName = noticeUpload.getOriginalFilename();
		    	String ext = originFileName.substring(originFileName.lastIndexOf("."));
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
	
				BoardFileDTO file = new BoardFileDTO();
				file.setOriginalName(originFileName);
				file.setSavedName(savedName);
				file.setSavedPath(filePath);
				file.setWriteNo(writeNo);
				
	
				parameters.setBoardFileList(file);
				
				try {
			    		
					noticeUpload.transferTo(new File(filePath + "\\" + file.getSavedName()));
			    	
			    }catch (Exception e) {
			    	e.printStackTrace();
						
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
		
		AdminBoardDTO boardDTO = adminBoardService.noticeModifyform(writeNo);
		
		BoardFileDTO boardFileDTO = adminBoardService.noticeFileLook(writeNo);
		
		System.out.println("여기는 수정페이지입니다." + boardDTO);
		
		mv.setViewName("admin/noticemodify");
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
				System.out.println("폴더생성 : " + directory.mkdirs());
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
				
				noticeUpdate.transferTo(new File(fileUploadDirectory + "\\" + savedName));
				
			}catch (IllegalStateException e) {
				
				e.printStackTrace();
				
			} catch (IOException e) {
				
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
