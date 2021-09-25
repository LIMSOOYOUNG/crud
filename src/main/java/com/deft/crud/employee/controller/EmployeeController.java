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
      
      List<EmployeeDTO>employeeList = employeeService.selectEmployee();
      
      mv.setViewName("employee/selectemployee");
      mv.addObject("employeeList", employeeList);
      
      System.out.println(employeeList);
      
      return mv;
   }
      
}