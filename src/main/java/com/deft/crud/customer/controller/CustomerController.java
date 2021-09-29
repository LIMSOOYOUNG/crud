package com.deft.crud.customer.controller;

import com.deft.crud.customer.model.dto.*;
import com.deft.crud.customer.model.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/customer")
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

    /* 기존 고객 기본 정보 조회 */
    @GetMapping("/cusinfo")
    public ModelAndView selectCustomerInfo(ModelAndView mv, @RequestParam int customerNo) {

        CustomerCompanyDTO customerInfo = customerService.selectCustomerInfo(customerNo);

        List<BusinessActivityDTO> businessActivityList = customerService.selectBusinessActivity(customerNo);

        for(BusinessActivityDTO businessActivityDTO : businessActivityList) {
            int actNo = businessActivityDTO.getActivityNo();
            System.out.println(actNo);
        }


        mv.addObject("customerInfo", customerInfo);
        mv.addObject("businessActivityList", businessActivityList);
        mv.setViewName("customer/selectCustomerInfo");

        return mv;
    }

    /* 분석 고객 기본정보 조회 */
    @GetMapping("/anainfo")
    public ModelAndView selectAnalysisCustomerInfo(ModelAndView mv, @RequestParam int customerNo) {

        CustomerCompanyDTO customerInfo = customerService.selectAnalysisCustomerInfo(customerNo);

        List<BusinessActivityDTO> businessActivityList = customerService.selectBusinessActivity(customerNo);

        mv.addObject("businessActivityList", businessActivityList);
        mv.addObject("customerInfo", customerInfo);
        mv.setViewName("customer/selectAnalysisCustomerInfo");

        return mv;
    }

    /* 선택된 영업기회 조회 */
    @GetMapping("/activity/select")
    public ModelAndView selectOneActivity(ModelAndView mv, HttpServletResponse response,
                                          @RequestParam int activityNo) throws JsonProcessingException {
        response.setContentType("application/json; charser=UTF-8");

        System.out.println(activityNo);

        ObjectMapper mapper = new ObjectMapper();

        BusinessActivityDTO businessActivity = customerService.selectBusinessActivityByActivityNo(activityNo);

        mv.addObject("activityOne", mapper.writeValueAsString(businessActivity));
        mv.setViewName("jsonView");

        return mv;
    }

    /* 고객 기본 정보 수정 */
    @PostMapping("ext/info/basic/modify")
    public ModelAndView modifyBasicInfo(ModelAndView mv, @ModelAttribute ModifyBasicInfoDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyBasicInfo(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 분석 고객 기본 정보 수정 */
    @PostMapping("ana/info/basic/modify")
    public ModelAndView modifyBasicInfoForAnalysis(ModelAndView mv, @ModelAttribute ModifyBasicInfoDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyBasicInfo(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 기존 고객 상세 정보 수정 */
    @PostMapping("ext/info/detail/modify")
    public ModelAndView modifyDetailInfo(ModelAndView mv, @ModelAttribute ModifyDetailInfoForExtDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int customerResult = customerService.modifyDetailInfoToCustomer(parameters);
        int companyResult = customerService.modifyDetailInfoToCompany(parameters);

        if(customerResult > 0 && companyResult > 0) {
            mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 분석 고객 상세 정보 수정 */
    @PostMapping("ana/info/detail/modify")
    public ModelAndView modifyDetailInfoForAnalysis(ModelAndView mv, @ModelAttribute ModifyDetailInfoForExtDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int customerResult = customerService.modifyDetailInfoToCustomer(parameters);
        int companyResult = customerService.modifyDetailInfoToCompany(parameters);

        if(customerResult > 0 && companyResult > 0) {
            mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 기존, 해지 고객 상태 변경 */
    @PostMapping("ext/status/modify")
    public ModelAndView modifyExtCustomerStatus(ModelAndView mv, @RequestParam int customerNo) {

        int result = customerService.modifyExtCustomerStatus(customerNo);

        if(customerNo > 0) {
            mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        }

        return mv;
    }

}
