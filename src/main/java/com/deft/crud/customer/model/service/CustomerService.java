package com.deft.crud.customer.model.service;

import com.deft.crud.customer.model.dao.CustomerMapper;
import com.deft.crud.customer.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    /* 전체 고객 조회 */
    public List<CustomerCompanyDTO> selectAllCustomer() {

        return customerMapper.selectAllCustomer();
    }

    /* 분석 고객 조회 */
    public List<CustomerCompanyDTO> selectAllAnalysisCustomer() {

        return customerMapper.selectAllAnalysisCustomer();
    }

    /* 기존 고객 정보 조회 */
    public CustomerCompanyDTO selectCustomerInfo(int customerNo) {

        return customerMapper.selectCustomerInfo(customerNo);
    }

    /* 분석 고객 정보 조회 */
    public CustomerCompanyDTO selectAnalysisCustomerInfo(int customerNo) {
    	CustomerCompanyDTO customer = customerMapper.selectAnalysisCustomerInfo(customerNo);
    	System.out.println("고객상세정보 : " + customer);
        return customer;
    }

    /* 영업 활동 조회 */
    public List<BusinessActivityDTO> selectBusinessActivity(int customerNo) {

        return customerMapper.selectBusinessActivity(customerNo);
    }

    /* 영업 활동 선택 조회 */
    public BusinessActivityDTO selectBusinessActivityByActivityNo(int activityNo) {

        return customerMapper.selectBusinessActivityByActivityNo(activityNo);
    }

    /* 고객 기본 정보 수정 */
    @Transactional
    public int modifyBasicInfo(ModifyBasicInfoDTO parameters) {

        return customerMapper.modifyBasicInfo(parameters);
    }

    /* 고객 상세 정보 수정 */
    @Transactional
    public int modifyDetailInfoToCustomer(ModifyDetailInfoForExtDTO parameters) {

        return customerMapper.modifyDetailInfoToCustomer(parameters);
    }
    @Transactional
    public int modifyDetailInfoToCompany(ModifyDetailInfoForExtDTO parameters) {

        return customerMapper.modifyDetailInfoToCompany(parameters);
    }

    /* 기존, 해지 고객 상태 변경 */
    @Transactional
    public int modifyExtCustomerStatus(int customerNo) {

        return customerMapper.modifyExtCustomerStatus(customerNo);
    }

    /* 분석 고객 상태 변경 */
    @Transactional
    public int modifyAnaCustomerStatus(AnaCustomerDetailDTO parameters) {

        return customerMapper.modifyAnaCustomerStatus(parameters);
    }

    /* 분석 고객 고객화 수정 */
    @Transactional
    public int modifyAnaCustomization(AnaCustomerDetailDTO parameters) {

        return customerMapper.modifyAnaCustomization(parameters);
    }

    public List<EmpInfoDTO> selectEmpName(String term) {

        return customerMapper.selectEmpName(term);
    }

    /* 분석 고객 영업활동 등록 */
    @Transactional
    public int insertActivity(BusinessActivityDTO parameters) {

        return customerMapper.insertActivity(parameters);
    }
}
