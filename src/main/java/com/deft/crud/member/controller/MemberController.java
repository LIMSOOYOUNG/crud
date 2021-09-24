package com.deft.crud.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping("/login")
	public void memberLogin() {}
	
	@GetMapping("/findId")
	public void memberFindId() {}
	
	@GetMapping("/findPwd")
	public void memberFindPwd() {}
}
