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
import java.util.ArrayList;
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
        List<ProductDTO> productList = customerService.selectProduct();
        List<EmpInfoDTO> empInfoList = customerService.selectEmpInfo();
        List<CustomerCompanyDTO> companyList = customerService.selectAllCustomerCompany();

        mv.addObject("customerCompanyList", customerList);
        mv.addObject("productList", productList);
        mv.addObject("empInfoList", empInfoList);
        mv.addObject("companyList", companyList);
        mv.setViewName("customer/selectAllAnalysisCustomer");

        return mv;
    }

    /* 기존 고객 기본 정보 조회 */
    @GetMapping("/cusinfo")
    public ModelAndView selectCustomerInfo(ModelAndView mv, @RequestParam int customerNo) {

        CustomerCompanyDTO customerInfo = customerService.selectCustomerInfo(customerNo);
        List<BusinessActivityDTO> businessActivityList = customerService.selectBusinessActivity(customerNo);
        List<EmpInfoDTO> empInfoList = customerService.selectEmpInfo();
        List<OrderChargeDTO> chargeList = customerService.selectChargeByCustomerNo(customerNo);

        mv.addObject("customerInfo", customerInfo);
        mv.addObject("businessActivityList", businessActivityList);
        mv.addObject("empInfoList", empInfoList);
        mv.addObject("chargeList", chargeList);
        mv.setViewName("customer/selectCustomerInfo");

        return mv;
    }

    /* 분석 고객 기본정보 조회 */
    @GetMapping("/anainfo")
    public ModelAndView selectAnalysisCustomerInfo(ModelAndView mv, @RequestParam int customerNo) {

        CustomerCompanyDTO customerInfo = customerService.selectAnalysisCustomerInfo(customerNo);
        List<BusinessActivityDTO> businessActivityList = customerService.selectBusinessActivity(customerNo);
        List<EmpInfoDTO> empInfoList = customerService.selectEmpInfo();
        List<AnaCustomerDetailHisDTO> customizationHisList = customerService.selectCustomizationHistory(customerNo);

        mv.addObject("businessActivityList", businessActivityList);
        mv.addObject("customerInfo", customerInfo);
        mv.addObject("empInfoList", empInfoList);
        mv.addObject("hisList", customizationHisList);
        mv.setViewName("customer/selectAnalysisCustomerInfo");

        return mv;
    }

    /* 선택된 영업기회 조회 */
    @GetMapping("/activity/select")
    public ModelAndView selectOneActivity(ModelAndView mv, HttpServletResponse response,
                                          @RequestParam int activityNo) throws JsonProcessingException {
        response.setContentType("application/json; charser=UTF-8");

        ObjectMapper mapper = new ObjectMapper();

        BusinessActivityDTO businessActivity = customerService.selectBusinessActivityByActivityNo(activityNo);

        mv.addObject("activityOne", mapper.writeValueAsString(businessActivity));

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

        if(result > 0) {
            mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 분석 고객 상태 변경 */
    @PostMapping("ana/status/modify")
    public ModelAndView modifyAnaCustomerStatus(ModelAndView mv, @ModelAttribute AnaCustomerDetailDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyAnaCustomerStatus(parameters);

        if(parameters.getCustomerStatus().equals("성공고객")) {
            int insertResult = customerService.insertExtCustomer(parameters);
        }

        if(result > 0) {
            mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 분석 고객 고객화 변경 */
    @PostMapping("/ana/customization/modify")
    public ModelAndView modifyAnaCustomization(ModelAndView mv, @ModelAttribute AnaCustomerDetailDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int modifyResult = customerService.modifyAnaCustomization(parameters);
        int insertResult = customerService.insertAnaCustomizationHistory(parameters);

        if(modifyResult > 0 && insertResult > 0) {
            mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 분석 고객 영업활동 등록 */
    @PostMapping("/ana/activity/insert")
    public ModelAndView insertAnaActivity(ModelAndView mv, @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.insertActivity(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 기존 고객 영업활동 등록 */
    @PostMapping("/ext/activity/insert")
    public ModelAndView insertExtActivity(ModelAndView mv, @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.insertActivity(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 기존 고객 담당사원 변경 */
    @PostMapping("/ext/manager/modify")
    public ModelAndView modifyExtManager(ModelAndView mv, @ModelAttribute CustomerDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyManager(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 분석 고객 담당사원 변경 */
    @PostMapping("/ana/manager/modify")
    public ModelAndView modifyAnaManager(ModelAndView mv, @ModelAttribute CustomerDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyManager(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 영업 활동 상세 조회 */
    @GetMapping("/activity/detail")
    public ModelAndView selectDetailActivity(ModelAndView mv, @RequestParam int activityNo) {

        BusinessActivityDTO businessActivity = customerService.selectBusinessActivityByActivityNo(activityNo);

        mv.addObject("activity", businessActivity);
        mv.setViewName("customer/activityModal");

        return mv;
    }

    /* 영업 활동 수정 처리 메소드 */
    @PostMapping("/activity/modify")
    public ModelAndView modifyDetailActivity(ModelAndView mv, @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyActivity(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 영업 활동 삭제 */
    @PostMapping("activity/delete")
    public ModelAndView deleteDetailActivity(ModelAndView mv, @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.deleteActivity(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 분석 고객 영업 활동 수정 처리 */
    @PostMapping("/ana/activity/modify")
    public ModelAndView modifyAnaDetailActivity(ModelAndView mv, @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyActivity(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 분석 고객 영업 활동 삭제 */
    @PostMapping("ana/activity/delete")
    public ModelAndView deleteAnaDetailActivity(ModelAndView mv, @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.deleteActivity(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        }

        return mv;
    }

    /* 고객사 전체 조회 */
    @GetMapping("/company")
    public ModelAndView selectAllCustomerCompany(ModelAndView mv) {

        List<CustomerCompanyDTO> customerCompanyList = customerService.selectAllCustomerCompany();

        mv.addObject("companyList", customerCompanyList);
        mv.setViewName("/customer/selectAllCustomerCompany");

        return mv;
    }

    /* 고객사 상세 조회 */
    @GetMapping("/company/detail")
    public ModelAndView selectDetailCustomerCompany(ModelAndView mv, @RequestParam int companyNo) {

        CustomerCompanyDTO customerCompany = customerService.selectCustomerCompanyInfo(companyNo);

        mv.addObject("company", customerCompany);
        mv.setViewName("/customer/customerCompanyInfoModal");

        return mv;
    }

    /* 고객사 정보 수정 */
    @PostMapping("/company/modify")
    public ModelAndView modifyDetailInfoToCustomerCompany(ModelAndView mv, @ModelAttribute CustomerCompanyDTO parameters) {

        int result = customerService.modifyDetailInfoToCustomerCompany(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/company");
        }

        return mv;
    }

    /* 고객사 정보 삭제 */
    @PostMapping("/company/delete")
    public ModelAndView deleteCustomerCompany(ModelAndView mv, @ModelAttribute CustomerCompanyDTO parameters) {

        int result = customerService.deleteCustomerCompany(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/company");
        }

        return mv;
    }

    /* 고객사 등록 */
    @PostMapping("/company/insert")
    public ModelAndView insertCustomerCompany(ModelAndView mv, @ModelAttribute CustomerCompanyDTO parameters) {

        int result = customerService.insertCustomerCompany(parameters);

        if(result > 0) {
            mv.setViewName("redirect:/customer/company");
        }

        return mv;
    }

    /* 고객 등록 */
    @PostMapping("/ana/insert")
    public ModelAndView insertCustomer(ModelAndView mv, @ModelAttribute InsertCustomerDTO parameters, @RequestParam List<Integer> productNo) {

        int customerResult = customerService.insertCustomer(parameters);

        List<CustomerProductDTO> products = new ArrayList<>();

        for (int integer : productNo) {
            products.add(new CustomerProductDTO(parameters.getCustomerNo(), integer, null));
        }

        int detailResult = customerService.insertDetail(parameters);
        int productResult = customerService.insertProduct(products);

        if(customerResult > 0 && detailResult > 0 && productResult > 0) {
            mv.setViewName("redirect:/customer/ana");
        }

        return mv;
    }
}
