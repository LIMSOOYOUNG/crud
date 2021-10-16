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

import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;
import com.deft.crud.admin.adminemployee.model.dto.DepartmentDTO;
import com.deft.crud.admin.adminemployee.model.dto.JobDTO;
import com.deft.crud.admin.adminemployee.model.service.AdminEmployeeService;
import com.deft.crud.member.model.dto.MemberDTO;

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
		
		mv.setViewName("/admin/insertMember");
		
		return mv;
	}
	
	/* 사원 등록 페이지에서 데이터 가져옴 */
	@PostMapping(value = "/emp/insert")
	
	public String redirectInsertMember(MemberDTO member) {
		
		System.out.println(member);
		
//		int result = adminService.insertMember();
		
		
		return "main/main";
	}
	
	
	@GetMapping(value = "/emp/checkUserId")
	@ResponseBody
	public boolean checkUserId(@RequestParam String empId) {
		
		return adminEmployeeService.checkUserId(empId);
	}
	 
	/* 사원상세 정보 */
	@GetMapping("employeedetail")
	public ModelAndView employeeDetailSelect(ModelAndView mv, @RequestParam int employeeNo)  {
		
		System.out.println("너의 정보는 ????" + employeeNo);

		AdminEmployeeDTO employeeDTO = adminEmployeeService.empDetail(employeeNo);
		
		System.out.println(employeeDTO);
		
		mv.setViewName("admin/employeedetail");
		mv.addObject("employeeDTO", employeeDTO);
		
		return mv;
	}

	/* 수정 페이지 */
	@GetMapping("employeeinfomodify")
	public ModelAndView employeeModifyForm(ModelAndView mv, @RequestParam int employeeNo) {
		
		AdminEmployeeDTO employeeDTO = adminEmployeeService.empInfoModify(employeeNo);
		
		List<JobDTO> jobList = adminEmployeeService.jobNameList();
		
		List<DepartmentDTO> deptList = adminEmployeeService.deptNameList();
		
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
		
		String filePath = root;
		
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
