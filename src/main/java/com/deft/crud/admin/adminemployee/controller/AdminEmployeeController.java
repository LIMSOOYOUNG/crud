package com.deft.crud.admin.adminemployee.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;
import com.deft.crud.admin.adminemployee.model.dto.DepartmentDTO;
import com.deft.crud.admin.adminemployee.model.dto.EmployeeImageDTO;
import com.deft.crud.admin.adminemployee.model.dto.JobDTO;
import com.deft.crud.admin.adminemployee.model.service.AdminEmployeeService;
import com.deft.crud.member.model.dto.MemberDTO;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/admin")
public class AdminEmployeeController {

	private final AdminEmployeeService adminEmployeeService;
	
	@Autowired
	public AdminEmployeeController(AdminEmployeeService adminEmployeeService) {
		this.adminEmployeeService = adminEmployeeService;
	}
	
	/* 사원등록 페이지로 이동 */
	@GetMapping(value = {"/emp/insert"})
	public ModelAndView insertMember(ModelAndView mv) {
		
		/* 부서목록 가져오기 */
		List<MemberDTO> jobList = adminEmployeeService.selectJobList();
		
		/* 직급목록 가져오기 */
		List<MemberDTO> deptList = adminEmployeeService.selectDeptList();
		
		/* 권한목록 가져오기 */
//		List<MemberDTO> authorityList = adminEmployeeService.selectAuthorityList();
		
		mv.addObject("jobList", jobList);
		mv.addObject("deptList", deptList);
		
		mv.setViewName("/admin/insertMember");
		
		return mv;
	}
	
	/* 선택한 부서의 담당자 목록 불러오기 */
	@GetMapping("manager/find")
	@ResponseBody
	public List<MemberDTO> selectManagerList(@RequestParam String deptCode) {
		
		System.out.println("선택한 부서코드 확인 : " + deptCode);
		
		List<MemberDTO> managerList = adminEmployeeService.selectManagerList(deptCode);
		
		return managerList;
	}
	
	/* 사원 등록 페이지에서 데이터 가져옴 */
	@PostMapping(value = "/emp/insert")
	
	public ModelAndView redirectInsertMember(ModelAndView mv, RedirectAttributes rttr,
											@ModelAttribute MemberDTO member,
											HttpServletRequest request,
											@RequestParam MultipartFile profileThumbNail) {
		
		System.out.println(member);
		
		int result = adminEmployeeService.insertMember(request, member, profileThumbNail);
		
		 String message = "";
		  
		  if(result > 0) { 
			  message = "사원등록 완료";
		  } else { 
			  message = "사원등록 실패!";
		  }
		  
		  rttr.addFlashAttribute("message", message);
		  mv.setViewName("redirect:/employee/selectemployee");
		
		return mv;
	}
	
	/* ID 중복여부 체크  */
	@GetMapping(value = "/emp/checkUserId")
    @ResponseBody
    public Boolean checkUserId(@RequestParam String empId) {
      
       int result = adminEmployeeService.checkUserId(empId);
       
       Boolean idCheckResult = false;
       
       if(result == 0) {
          idCheckResult = true;
       } 
       
       return idCheckResult;
    }
	 
	/* 사원상세 정보 */
	@GetMapping("employeedetail")
	public ModelAndView employeeDetailSelect(ModelAndView mv, @RequestParam int employeeNo)  {

		System.out.println("@@@ 사원번호 : " + employeeNo);
		
		/* employeeDTO를 employeeNo를 담아서 서비스에 전달한다.  */
		AdminEmployeeDTO employeeDTO = adminEmployeeService.empDetail(employeeNo);
		
		
		/* 페이지 이동값을 admin/employeedetail 지정한다.*/
		mv.setViewName("admin/employeedetail");
		
		mv.addObject("employeeDTO", employeeDTO);
		
		return mv;
	}

	/* 수정 페이지 */
	@GetMapping("employeeinfomodify")
	public ModelAndView employeeModifyForm(ModelAndView mv, @RequestParam int employeeNo) {
		
		/* AdminEmployeeDTO를 emplyoeeNo값을 담아서 서비스에 전단한다. */
		AdminEmployeeDTO employeeDTO = adminEmployeeService.empInfoModify(employeeNo);
		
		/* JobDTO를 리스트로 서비스에 전달한다. */
		List<JobDTO> jobList = adminEmployeeService.jobNameList();
		
		/* DepartmentDTO를 리스트로 서비스에 전달한다. */
		List<DepartmentDTO> deptList = adminEmployeeService.deptNameList();
		
		/* AdminEmployeeDTO를 리스트로 서비스에 전달한다. */
		List<AdminEmployeeDTO> managerList = adminEmployeeService.managerList();
		
		mv.setViewName("admin/employeeinfomodify");
		mv.addObject("employeeDTO", employeeDTO);
		mv.addObject("managerList", managerList);
		mv.addObject("jobList", jobList);
		mv.addObject("deptList", deptList);
		
		
		return mv;
	}
	
	@PostMapping("employeeinfomodify")
	public ModelAndView employeeModify(ModelAndView mv, @ModelAttribute AdminEmployeeDTO parameters, 
			@RequestParam MultipartFile profileThumbNail,
			HttpServletRequest request) {
		
		int result = 0;
		EmployeeImageDTO employeeImageDTO = new EmployeeImageDTO();
		if(!profileThumbNail.isEmpty()) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		String fileUploadDirectory = root + "\\upload\\profileImage\\original";
		String thumbnailDirectory = root + "\\upload\\profileImage\\thumbnail";
		
		File directory = new File(fileUploadDirectory);
		File directory2 = new File(thumbnailDirectory);
		
		/* 이미지를 저장할 폴더가 없을시에 폴더를 생성해준다. */
		if(!directory.exists() || !directory2.exists()) {
			System.out.println("폴더생성 : " + directory.mkdirs());
			System.out.println("폴더생성 : " + directory2.mkdirs());
		}
		
		/* 이미지 원본파일 */
		String originFileName = profileThumbNail.getOriginalFilename();
		
		/* 이미지 확장자 */
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		/* 이미지파일명이 중복되지 않도록 설정해준다. */
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

		employeeImageDTO.setOriginalName(originFileName);
		employeeImageDTO.setSavedName(savedName);
		employeeImageDTO.setSavedPath(fileUploadDirectory);
		employeeImageDTO.setEmpNo(parameters.getEmployeeNo());
		
		/* 썸네일 너비 높이 설정 */
		int width = 200;
		int height = 250;

		try {
			/* 원본 이미지 저장 */
			profileThumbNail.transferTo(new File(fileUploadDirectory + "\\" + savedName));
			
			/* 썸네일 경로 저장 */
			Thumbnails.of(fileUploadDirectory + "\\" + savedName)
			.size(width, height)
			.toFile(thumbnailDirectory + "\\thumbnail_" + savedName);
			
			/* html에서 이미지를 조회할 수 있도록 썸네일 경로를 dto에 담아준다.*/
			employeeImageDTO.setThumbnailPath("/thumbnail_" + savedName);
		
		}catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			new File(fileUploadDirectory + "\\" + savedName).delete();
			e.printStackTrace();
		}
		
		result = adminEmployeeService.modifyEmployeeImg(parameters, employeeImageDTO);
			
		}else {
			
		result = adminEmployeeService.employeeModify(parameters);
			
		}
		
		
		String message = "";
		
		if(result > 0) {
			message = "상품수정에 성공하셨습니다!";
		} else {
			message = "상품수정에 실패하셨습니다!";
		}
		
		mv.addObject("parameters", parameters);
		mv.setViewName("redirect:/admin/employeedetail?employeeNo=" + parameters.getEmployeeNo());
		
		return mv;
	}
	
	
}
