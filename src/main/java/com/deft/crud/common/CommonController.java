package com.deft.crud.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@GetMapping("/authority/check")
	public String deniedPage() {
		
		return "/common/authorityCheck";
	}

}
