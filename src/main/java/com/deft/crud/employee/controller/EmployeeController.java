package com.deft.crud.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.employee.model.dto.EmployeeDTO;
import com.deft.crud.employee.model.service.EmployeeService;

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
      
}