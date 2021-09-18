package com.deft.crud.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.deft.crud.main.model.service.MainService;

@Controller
public class MainController {
	
	private MainService mainService;
	
	@Autowired
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}
	
	@GetMapping(value = {"/", "/main"})
	public String main() {
		return "main/main";
	}
	
	@PostMapping(value = "/")
	public String redirectMain() {
		
//		List<String> menuList = mainService.selectMenuList();
//		
//		for(String menu : menuList) {
//			System.out.println(menu);
//		}
		
		return "main/main";
	}
}
