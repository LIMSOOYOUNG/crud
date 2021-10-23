package com.deft.crud.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.employee.model.dto.EmployeeDTO;
import com.deft.crud.employee.model.service.EmployeeService;
import com.deft.crud.member.model.service.UserImpl;

@Controller
@RequestMapping("/employee/*")
public class EmployeeController {

   private EmployeeService employeeService;
   
   @Autowired
   public EmployeeController(EmployeeService employeeService) {
      this.employeeService = employeeService;
   }
   
   /* 사원 조회 */
   @GetMapping("/selectemployee")
   public ModelAndView selectEmployee(ModelAndView mv) {
      
	  /* EmployeeDTO를 리스트로 서비스에 값을 전달한다. */
      List<EmployeeDTO> employeeList = employeeService.selectEmployee();
      
      /* 페이지 이동값을 employee/selectemployee 지정한다.*/
      mv.setViewName("employee/selectemployee");
      
      /* key값과 value값을 지정한다.*/
      mv.addObject("employeeList", employeeList);
      
      return mv;
   }
   
	/* 입력한 비밀번호 일치 여부 확인 */
	@GetMapping("/pwd/check")
	@ResponseBody
	public Boolean pwdChange(@AuthenticationPrincipal UserImpl userInfo, @RequestParam String userPwd) {
		
		EmployeeDTO empInfo = new EmployeeDTO();
		
		String empPwd = userInfo.getEmpPwd();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Boolean pwdCheckResult = false;

		if(encoder.matches(userPwd, empPwd)) {
			
			 pwdCheckResult = true;
			
		} else {
		
		}
		
		return pwdCheckResult;
	}
	
	/* 비밀번호 변경 */
	@PostMapping("/pwd/modify")
	public ModelAndView modifyPwd(ModelAndView mv, RedirectAttributes rttr,
								 @AuthenticationPrincipal UserImpl userInfo, @RequestParam String pwdCheck1) {
		
		String message = "";
		
		int result = employeeService.modifyPwd(userInfo, pwdCheck1);
		
		  if(result > 0) { 
			  message = "비밀번호 변경 완료";
		  } else { 
			  message = "비밀번호 변경 실패!";
		  }
		  
		rttr.addFlashAttribute("message", message);
		
		mv.setViewName("redirect:/");
		  
		return mv;
	}
}




















