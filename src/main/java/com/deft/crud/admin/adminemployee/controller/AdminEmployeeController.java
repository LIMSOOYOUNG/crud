package com.deft.crud.admin.adminemployee.controller;

import java.io.File;
import java.util.List;
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
import com.deft.crud.admin.adminemployee.model.dto.JobDTO;
import com.deft.crud.admin.adminemployee.model.service.AdminEmployeeService;
import com.deft.crud.member.model.dto.MemberDTO;
import com.deft.crud.organization.model.service.OrganizationService;

@Controller
@RequestMapping("/admin")
public class AdminEmployeeController {

	private final AdminEmployeeService adminEmployeeService;
	private final OrganizationService organizationService;
	
	@Autowired
	public AdminEmployeeController(AdminEmployeeService adminEmployeeService, OrganizationService organizationService) {
		this.adminEmployeeService = adminEmployeeService;
		this.organizationService = organizationService;
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
	
	public ModelAndView redirectInsertMember(ModelAndView mv, RedirectAttributes rttr, MemberDTO member) {
		
		System.out.println(member);
		
		int result = adminEmployeeService.insertMember(member);
		
		 String message = "";
		  
		  if(result > 0) { 
			  message = "영업활동내용 수정완료";
		  } else { 
			  message = "영업활동내용 수정실패!";
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
			@RequestParam MultipartFile imageUpdate,
			HttpServletRequest request) {
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		String filePath = root + "\\employeeupload";
		
		File mkdir = new File(filePath);
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		String originFileName = imageUpdate.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;


		try {
			imageUpdate.transferTo(new File(filePath + "\\" + savedName));
			
		} catch (Exception e) {
			e.printStackTrace();
			/* 실패시 파일 삭제 */
			new File(filePath + "\\" + savedName).delete();
		} 

		
		int result = adminEmployeeService.employeeModify(parameters);
		
		if(result > 0) {
			
			mv.setViewName("redirect:/admin/employeedetail?employeeNo=" + parameters.getEmployeeNo());
			
		}
		
		mv.addObject("parameters", parameters);
		
		return mv;
	}
	
	
}
