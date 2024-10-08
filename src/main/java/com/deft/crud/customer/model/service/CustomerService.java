package com.deft.crud.customer.model.service;

import com.deft.crud.customer.model.dao.CustomerMapper;
import com.deft.crud.customer.model.dto.*;
import com.deft.crud.member.model.service.UserImpl;
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
    public List<CustomerCompanyDTO> selectAllCustomer(UserImpl userInfo) {

        return customerMapper.selectAllCustomer(userInfo);
    }

    /* 기존 고객 조회 (해지 고객 제외) */
    public List<ExtCustomerDTO> selectExtCustomerList(UserImpl userInfo) {

    	return customerMapper.selectExtCustomerList(userInfo);
    }

    /* 분석 고객 조회 */
    public List<CustomerCompanyDTO> selectAllAnalysisCustomer(UserImpl userInfo) {

        return customerMapper.selectAllAnalysisCustomer(userInfo);
    }

    /* 상품 조회 */
    public List<ProductDTO> selectProduct() {

        return customerMapper.selectProduct();
    }

    /* 담당사원 정보 조회 */
    public List<EmpInfoDTO> selectEmpInfo() {

        return customerMapper.selectEmpInfo();
    }

    /* 고객사 전체 조회 */
    public List<CustomerCompanyDTO> selectAllCustomerCompany() {

        return customerMapper.selectAllCustomerCompany();
    }

    /* 기존 고객 정보 조회 */
    public CustomerCompanyDTO selectCustomerInfo(int customerNo) {

        return customerMapper.selectCustomerInfo(customerNo);
    }

    /* 분석 고객 정보 조회 */
    public CustomerCompanyDTO selectAnalysisCustomerInfo(int customerNo) {

    	return customerMapper.selectAnalysisCustomerInfo(customerNo);
    }

    /* 영업 활동 조회 */
    public List<BusinessActivityDTO> selectBusinessActivity(int customerNo) {

        return customerMapper.selectBusinessActivity(customerNo);
    }

    /* 고객화 변경 이력 조회 */
    public List<AnaCustomerDetailHisDTO> selectCustomizationHistory(int customerNo) {

        return customerMapper.selectCustomizationHistory(customerNo);
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

    /* 고객 상세 정보 수정(고객 정보) */
    @Transactional
    public int modifyDetailInfoToCustomer(ModifyDetailInfoForExtDTO parameters) {

        return customerMapper.modifyDetailInfoToCustomer(parameters);
    }

    /* 고객 상세 정보 수정(고객사 정보) */
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

    /* 분석고객 -> 기존고객 변경 */
    @Transactional
    public int insertExtCustomer(AnaCustomerDetailDTO parameters) {

        return customerMapper.insertExtCustomer(parameters);
    }

    /* 분석 고객 고객화 수정 */
    @Transactional
    public int modifyAnaCustomization(AnaCustomerDetailDTO parameters) {

        return customerMapper.modifyAnaCustomization(parameters);
    }

    /* 고객화 변경 이력 등록 */
    @Transactional
    public int insertAnaCustomizationHistory(AnaCustomerDetailDTO parameters) {

        return customerMapper.insertAnaCustomizationHistory(parameters);
    }

    /* 분석 고객 영업활동 등록 */
    @Transactional
    public int insertActivity(BusinessActivityDTO parameters) {

        return customerMapper.insertActivity(parameters);
    }

    /* 담당사원 변경 */
    @Transactional
    public int modifyManager(CustomerDTO parameters) {

        return customerMapper.modifyManager(parameters);
    }

    /* 영업 활동 수정 */
    @Transactional
    public int modifyActivity(BusinessActivityDTO parameters) {

        return customerMapper.modifyActivity(parameters);
    }

    /* 영업 활동 삭제 */
    @Transactional
    public int deleteActivity(BusinessActivityDTO parameters) {

        return customerMapper.deleteActivity(parameters);
    }

    /* 고객사 상세 조회 */
    public CustomerCompanyDTO selectCustomerCompanyInfo(int companyNo) {

        return customerMapper.selectCustomerCompanyInfo(companyNo);
    }

    /* 고객사 정보 수정 */
    @Transactional
    public int modifyDetailInfoToCustomerCompany(CustomerCompanyDTO parameters) {

        return customerMapper.modifyDetailInfoToCustomerCompany(parameters);
    }

    /* 고객사 삭제 */
    @Transactional
    public int deleteCustomerCompany(CustomerCompanyDTO parameters) {

        return customerMapper.deleteCustomerCompany(parameters);
    }

    /* 고객사 등록 */
    @Transactional
    public int insertCustomerCompany(CustomerCompanyDTO parameters) {

        return customerMapper.insertCustomerCompany(parameters);
    }

    /* 분석 고객 기본정보 등록 */
    @Transactional
    public int insertCustomer(InsertCustomerDTO parameters) {

        return customerMapper.insertCustomer(parameters);
    }

    /* 분석고객 상태 등록 */
    @Transactional
    public int insertDetail(InsertCustomerDTO parameters) {

        return customerMapper.insertDetail(parameters);
    }

    /* 분석고객 관심상품 등록 */
    @Transactional
    public int insertProduct(List<CustomerProductDTO> products) {

        return customerMapper.insertProduct(products);
    }

    /* 상품 구매 내역 조회 */
    public List<OrderChargeDTO> selectChargeByCustomerNo(int customerNo) {

        return customerMapper.selectChargeByCustomerNo(customerNo);
    }

    /* 고객 정렬 조회 */
    public List<CustomerCompanyDTO> selectCustomerByStatus(String customerStatus, UserImpl userInfo) {

        return customerMapper.selectCustomerByStatus(customerStatus, userInfo);
    }

    /* 분석 고객 정렬 조회 */
    public List<CustomerCompanyDTO> selectAnaCustomerByStatus(String customerStatus, UserImpl userInfo) {

        return customerMapper.selectAnaCustomerByStatus(customerStatus, userInfo);
    }
}
