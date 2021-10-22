package com.deft.crud.customer.controller;

import com.deft.crud.customer.model.dto.*;
import com.deft.crud.customer.model.service.CustomerService;
import com.deft.crud.member.model.service.UserImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerController(CustomerService customerService, ObjectMapper objectMapper) {

        this.customerService = customerService;
        this.objectMapper = objectMapper;
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

    /* 기존 고객 기본, 상세 정보 조회 */
    @GetMapping("/cusinfo")
    public ModelAndView selectCustomerInfo(ModelAndView mv, @RequestParam int customerNo) {

        /* 차례대로 고객 정보, 고객 영업활동, 담당사원 정보, 구매내역 조회 */
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

    /* 분석 고객 기본, 상세 정보 조회 */
    @GetMapping("/anainfo")
    public ModelAndView selectAnalysisCustomerInfo(ModelAndView mv, @RequestParam int customerNo) {

        /* 차례대로 고객 정보, 고객 영업활동, 담당사원 정보, 고객화 이력 조회 */
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

        BusinessActivityDTO businessActivity = customerService.selectBusinessActivityByActivityNo(activityNo);

        mv.addObject("activityOne", objectMapper.writeValueAsString(businessActivity));

        return mv;
    }

    /* 고객 기본 정보 수정 */
    @PostMapping("ext/info/basic/modify")
    public ModelAndView modifyBasicInfo(ModelAndView mv,
                                        RedirectAttributes rttr,
                                        @ModelAttribute ModifyBasicInfoDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyBasicInfo(parameters);

        String message = "";

        if(result > 0) {
            message = "고객의 기본 정보 변경을 성공했습니다!";
        } else {
            message = "고객의 기본 정보 변경을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        return mv;
    }

    /* 분석 고객 기본 정보 수정 */
    @PostMapping("ana/info/basic/modify")
    public ModelAndView modifyBasicInfoForAnalysis(ModelAndView mv,
                                                   RedirectAttributes rttr,
                                                   @ModelAttribute ModifyBasicInfoDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyBasicInfo(parameters);

        String message = "";

        if(result > 0) {
            message = "고객의 기본 정보 변경을 성공했습니다!";
        } else {
            message = "고객의 기본 정보 변경을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        return mv;
    }

    /* 기존 고객 상세 정보 수정 */
    @PostMapping("ext/info/detail/modify")
    public ModelAndView modifyDetailInfo(ModelAndView mv,
                                         RedirectAttributes rttr,
                                         @ModelAttribute ModifyDetailInfoForExtDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        /* 고객의 정보와 고객사의 정보를 모두 수정해야 하므로 Business Logic 두 번 수행
        *  차례대로 고객 정보 수정, 고객사 정보 수정 */
        int customerResult = customerService.modifyDetailInfoToCustomer(parameters);
        int companyResult = customerService.modifyDetailInfoToCompany(parameters);

        String message = "";

        if(customerResult > 0 && companyResult > 0) {
            message = "고객의 상세정보 변경을 성공했습니다!";
        } else {
            message = "고객의 상세정보 변경을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        return mv;
    }

    /* 분석 고객 상세 정보 수정 */
    @PostMapping("ana/info/detail/modify")
    public ModelAndView modifyDetailInfoForAnalysis(ModelAndView mv,
                                                    RedirectAttributes rttr,
                                                    @ModelAttribute ModifyDetailInfoForExtDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int customerResult = customerService.modifyDetailInfoToCustomer(parameters);
        int companyResult = customerService.modifyDetailInfoToCompany(parameters);

        String message = "";

        if(customerResult > 0 && companyResult > 0) {
            message = "고객의 상세정보 변경을 성공했습니다!";
        } else {
            message = "고객의 상세정보 변경을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);

        return mv;
    }

    /* 기존, 해지 고객 상태 변경 */
    @PostMapping("ext/status/modify")
    public ModelAndView modifyExtCustomerStatus(ModelAndView mv,
                                                RedirectAttributes rttr,
                                                @RequestParam int customerNo) {

        int result = customerService.modifyExtCustomerStatus(customerNo);

        String message = "";

        if(result > 0) {
            message = "고객의 상태 변경을 성공했습니다!";
        } else {
            message = "고객의 상태 변경을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        return mv;
    }

    /* 분석 고객 상태 변경 */
    @PostMapping("ana/status/modify")
    public ModelAndView modifyAnaCustomerStatus(ModelAndView mv,
                                                RedirectAttributes rttr,
                                                @ModelAttribute AnaCustomerDetailDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyAnaCustomerStatus(parameters);

        /* 분석 고객의 상태가 성공고객일 경우 기존 고객으로 등록 */
        if(parameters.getCustomerStatus().equals("성공고객")) {
            int insertResult = customerService.insertExtCustomer(parameters);
        }

        String message = "";

        if(result > 0) {
            message = "고객의 상태 변경을 성공했습니다!";
        } else {
            message = "고객의 상태 변경을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        return mv;
    }

    /* 분석 고객 고객화 변경 */
    @PostMapping("/ana/customization/modify")
    public ModelAndView modifyAnaCustomization(ModelAndView mv,
                                               RedirectAttributes rttr,
                                               @ModelAttribute AnaCustomerDetailDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int modifyResult = customerService.modifyAnaCustomization(parameters);
        int insertResult = customerService.insertAnaCustomizationHistory(parameters);

        String message = "";

        if(modifyResult > 0 && insertResult > 0) {
            message = "고객화 변경을 성공했습니다!";
        } else {
            message = "고객화 변경을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        return mv;
    }

    /* 분석 고객 영업활동 등록 */
    @PostMapping("/ana/activity/insert")
    public ModelAndView insertAnaActivity(ModelAndView mv,
                                          RedirectAttributes rttr,
                                          @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.insertActivity(parameters);

        String message = "";

        if(result > 0) {
            message = "영업 활동 등록을 성공했습니다!";
        } else {
            message = "영업 활동 등록을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        return mv;
    }

    /* 기존 고객 영업활동 등록 */
    @PostMapping("/ext/activity/insert")
    public ModelAndView insertExtActivity(ModelAndView mv,
                                          RedirectAttributes rttr,
                                          @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.insertActivity(parameters);

        String message = "";

        if(result > 0) {
            message = "영업 활동 등록을 성공했습니다!";
        } else {
            message = "영업 활동 등록을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        return mv;
    }

    /* 기존 고객 담당사원 변경 */
    @PostMapping("/ext/manager/modify")
    public ModelAndView modifyExtManager(ModelAndView mv,
                                         RedirectAttributes rttr,
                                         @ModelAttribute CustomerDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyManager(parameters);

        String message = "";

        if(result > 0) {
            message = "담당 사원 변경을 성공했습니다!";
        } else {
            message = "담당 사원 변경을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        return mv;
    }

    /* 분석 고객 담당사원 변경 */
    @PostMapping("/ana/manager/modify")
    public ModelAndView modifyAnaManager(ModelAndView mv,
                                         RedirectAttributes rttr,
                                         @ModelAttribute CustomerDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyManager(parameters);

        String message = "";

        if(result > 0) {
            message = "담당 사원 변경을 성공했습니다!";
        } else {
            message = "담당 사원 변경을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
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

    /* 기존 고객 영업 활동 수정 */
    @PostMapping("/activity/modify")
    public ModelAndView modifyDetailActivity(ModelAndView mv,
                                             RedirectAttributes rttr,
                                             @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyActivity(parameters);

        String message = "";

        if(result > 0) {
            message = "영업 활동 삭제를 성공했습니다!";
        } else {
            message = "영업 활동 삭제를 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        return mv;
    }

    /* 기존 고객 영업 활동 삭제 */
    @PostMapping("activity/delete")
    public ModelAndView deleteDetailActivity(ModelAndView mv,
                                             RedirectAttributes rttr,
                                             @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.deleteActivity(parameters);

        String message = "";

        if(result > 0) {
            message = "영업 활동 삭제를 성공했습니다!";
        } else {
            message = "영업 활동 삭제를 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/cusinfo?customerNo=" + customerNo);
        return mv;
    }

    /* 분석 고객 영업 활동 수정 */
    @PostMapping("/ana/activity/modify")
    public ModelAndView modifyAnaDetailActivity(ModelAndView mv,
                                                RedirectAttributes rttr,
                                                @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.modifyActivity(parameters);

        String message = "";

        if(result > 0) {
            message = "영업 활동 수정을 성공했습니다!";
        } else {
            message = "영업 활동 수정을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
        return mv;
    }

    /* 분석 고객 영업 활동 삭제 */
    @PostMapping("ana/activity/delete")
    public ModelAndView deleteAnaDetailActivity(ModelAndView mv,
                                                RedirectAttributes rttr,
                                                @ModelAttribute BusinessActivityDTO parameters) {

        int customerNo = parameters.getCustomerNo();

        int result = customerService.deleteActivity(parameters);

        String message = "";

        if(result > 0) {
            message = "영업 활동 삭제를 성공했습니다!";
        } else {
            message = "영업 활동 삭제를 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/anainfo?customerNo=" + customerNo);
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
    public ModelAndView modifyDetailInfoToCustomerCompany(ModelAndView mv,
                                                          RedirectAttributes rttr,
                                                          @ModelAttribute CustomerCompanyDTO parameters) {

        int result = customerService.modifyDetailInfoToCustomerCompany(parameters);

        String message = "";

        if(result > 0) {
            message = "고객사 정보 수정을 성공했습니다!";
        } else {
            message = "고객사 정보 수정을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/company");

        return mv;
    }

    /* 고객사 정보 삭제 */
    @PostMapping("/company/delete")
    public ModelAndView deleteCustomerCompany(ModelAndView mv,
                                              RedirectAttributes rttr,
                                              @ModelAttribute CustomerCompanyDTO parameters) {

        int result = customerService.deleteCustomerCompany(parameters);

        String message = "";

        if(result > 0) {
            message = "고객사 삭제를 성공했습니다!";
        } else {
            message = "고객사 삭제를 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/company");

        return mv;
    }

    /* 고객사 등록 */
    @PostMapping("/company/insert")
    public ModelAndView insertCustomerCompany(ModelAndView mv,
                                              RedirectAttributes rttr,
                                              @ModelAttribute CustomerCompanyDTO parameters) {

        int result = customerService.insertCustomerCompany(parameters);

        String message = "";

        if(result > 0) {
            message = "고객사 등록을 성공했습니다!";
        } else {
            message = "고객사 등록을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/company");

        return mv;
    }

    /* 분석 고객 등록 */
    @PostMapping("/ana/insert")
    public ModelAndView insertCustomer(ModelAndView mv,
                                       RedirectAttributes rttr,
                                       @ModelAttribute InsertCustomerDTO parameters,
                                       @RequestParam List<Integer> productNo) {

        int customerResult = customerService.insertCustomer(parameters);

        List<CustomerProductDTO> products = new ArrayList<>();

        for (int integer : productNo) {
            products.add(new CustomerProductDTO(parameters.getCustomerNo(), integer, null));
        }

        int detailResult = customerService.insertDetail(parameters);
        int productResult = customerService.insertProduct(products);

        String message = "";

        if(customerResult > 0 && detailResult > 0 && productResult > 0) {
            message = "분석고객 등록을 성공했습니다!";
        } else {
            message = "분석고객 등록을 실패했습니다.";
        }

        rttr.addFlashAttribute("message", message);
        mv.setViewName("redirect:/customer/ana");
        return mv;
    }

    /* 고객 상태별 정렬 조회 */
    @GetMapping("/selectExt")
    public ModelAndView selectCustomerByStatus(ModelAndView mv,
                                               HttpServletResponse response,
                                               @RequestParam String customerStatus,
                                               @AuthenticationPrincipal UserImpl userInfo) throws JsonProcessingException {

        response.setContentType("application/json; charset=UTF-8");

        List<CustomerCompanyDTO> customerCompanyList = customerService.selectCustomerByStatus(customerStatus);

        mv.addObject("customerCompanyList", objectMapper.writeValueAsString(customerCompanyList));
        mv.setViewName("jsonView");

        return mv;
    }

    /* 분석고객 상태별 정렬 조회 */
    @GetMapping("/selectAna")
    public ModelAndView selectAnaCustomerByStatus(ModelAndView mv,
                                                  HttpServletResponse response,
                                                  @RequestParam String customerStatus,
                                                  @AuthenticationPrincipal UserImpl userInfo) throws JsonProcessingException {

        response.setContentType("application/json; charset=UTF-8");

        List<CustomerCompanyDTO> customerCompanyList = customerService.selectAnaCustomerByStatus(customerStatus);

        mv.addObject("customerCompanyList", objectMapper.writeValueAsString(customerCompanyList));
        mv.setViewName("jsonView");

        return mv;
    }
}
