package com.deft.crud.customer.controller;

import com.deft.crud.customer.model.dto.CustomerCompanyDTO;
import com.deft.crud.customer.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /* 전체 고객 조회 */
    @GetMapping("/all")
    public ModelAndView selectAllCustomer(ModelAndView mv) {

        List<CustomerCompanyDTO> customerList = customerService.selectAllCustomer();

        mv.addObject("customerCompanyList", customerList);
        mv.setViewName("customer/selectAllCustomer");

        return mv;
    }

    /* 분석 고객 조회 */
    @GetMapping("/ana")
    public ModelAndView selectAllAnalysisCustomer(ModelAndView mv) {

        List<CustomerCompanyDTO> customerList = customerService.selectAllAnalysisCustomer();

        mv.addObject("customerCompanyList", customerList);
        mv.setViewName("customer/selectAllAnalysisCustomer");

        return mv;
    }

    @GetMapping("/cusinfo")
    public ModelAndView selectCustomerInfo(ModelAndView mv, @RequestParam int customerNo) {

        CustomerCompanyDTO customerInfo = customerService.selectCustomerInfo(customerNo);

        mv.addObject("customerInfo", customerInfo);
        mv.setViewName("customer/selectCustomerInfo");

        return mv;
    }

    @GetMapping("/anainfo")
    public ModelAndView selectAnalysisCustomerInfo(ModelAndView mv, @RequestParam int customerNo) {

        CustomerCompanyDTO customerInfo = customerService.selectAnalysisCustomerInfo(customerNo);

        mv.addObject("customerInfo", customerInfo);
        mv.setViewName("customer/selectAnalysisCustomerInfo");

        return mv;
    }

}
