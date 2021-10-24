package com.deft.crud.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.member.model.dto.MemberDTO;
import com.deft.crud.member.model.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		
		this.memberService = memberService;
	}
	
	@GetMapping("/login")
	public void memberLogin() {}
	
	@GetMapping("/findId")
	public void memberFindId() {}
	
	@PostMapping("/findId")
	public ModelAndView memberFindId(ModelAndView mv, @ModelAttribute MemberDTO userInfo) {
		
		String empPhone = userInfo.getEmpPhone().replaceAll("[^0-9]", "");
		userInfo.setEmpPhone(empPhone);
		
		String empName = userInfo.getEmpName();
		String empId = memberService.selectEmpId(userInfo);
		
		mv.addObject("empName", empName);
		mv.addObject("empId", empId);
		mv.setViewName("member/resultFindId");
				
		return mv;
	}
	
	@GetMapping("/findPwd")
	public void memberFindPwd() {}
}
