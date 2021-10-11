package com.deft.crud.customer.model.dao;

import com.deft.crud.customer.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    /* 기존 고객 조회 */
    List<CustomerCompanyDTO> selectAllCustomer();
    
    /* 기존 고객 조회 (해지 고객 제외) */
    List<ExtCustomerDTO> selectExtCustomerList();

    /* 분석 고객 조회 */
    List<CustomerCompanyDTO> selectAllAnalysisCustomer();

    /* 기존 고객 정보 조회 */
    CustomerCompanyDTO selectCustomerInfo(int customerNo);

    /* 분석 고객 정보 조회 */
    CustomerCompanyDTO selectAnalysisCustomerInfo(int customerNo);

    /* 영업 활동 조회 */
    List<BusinessActivityDTO> selectBusinessActivity(int customerNo);

    /* 영업 활동 선택 조회 */
    BusinessActivityDTO selectBusinessActivityByActivityNo(int activityNo);

    /* 고객 기본 정보 수정 */
    int modifyBasicInfo(ModifyBasicInfoDTO parameters);

    /* 고객 상세 정보 수정 */
    int modifyDetailInfoToCustomer(ModifyDetailInfoForExtDTO parameters);
    int modifyDetailInfoToCompany(ModifyDetailInfoForExtDTO parameters);

    /* 기존, 해지 고객 상태 변경 */
    int modifyExtCustomerStatus(int customerNo);

    /* 분석 고객 상태 변경 */
    int modifyAnaCustomerStatus(AnaCustomerDetailDTO parameters);

    /* 분석 고객 고객화 수정 */
    int modifyAnaCustomization(AnaCustomerDetailDTO parameters);

    List<EmpInfoDTO> selectEmpName(String term);

    /* 분석 고객 영업활동 등록 */
    int insertActivity(BusinessActivityDTO parameters);

    /* 사원 정보 조회 */
    List<EmpInfoDTO> selectEmpInfo();

    /* 담당 사원 변경 */
    int modifyManager(CustomerDTO parameters);

    /* 영업 활동 수정 */
    int modifyActivity(BusinessActivityDTO parameters);

    /* 영업 활동 삭제 */
    int deleteActivity(BusinessActivityDTO parameters);

    /* 고객사 전체 조회 */
    List<CustomerCompanyDTO> selectAllCustomerCompany();

    /* 고객사 상세 조회 */
    CustomerCompanyDTO selectCustomerCompanyInfo(int companyNo);

    /* 고객사 정보 수정 */
    int modifyDetailInfoToCustomerCompany(CustomerCompanyDTO parameters);

    /* 고객사 삭제 */
    int deleteCustomerCompany(CustomerCompanyDTO parameters);

    /* 고객사 등록 */
    int insertCustomerCompany(CustomerCompanyDTO parameters);

    /* 상품 조회 */
    List<ProductDTO> selectProduct();

    /* 분석고객 기본정보 등록 */
    int insertCustomer(InsertCustomerDTO parameters);

    /* 분석고객 상태 등록 */
    int insertDetail(InsertCustomerDTO parameters);

    /* 분석고객 관심상품 등록 */
    int insertProduct(List<CustomerProductDTO> products);

    /* 분석고객 -> 기존고객 변경 */
    int insertExtCustomer(AnaCustomerDetailDTO parameters);

    /* 고객화 변경 이력 조회 */
    List<AnaCustomerDetailHisDTO> selectCustomizationHistory(int customerNo);

    /* 고객화 변경 이력 등록 */
    int insertAnaCustomizationHistory(AnaCustomerDetailDTO parameters);

    /* 상품 구매 내역 조회 */
    List<OrderChargeDTO> selectChargeByCustomerNo(int customerNo);

    /* 고객 정렬 조회 */
    List<CustomerCompanyDTO> selectCustomerByStatus(String customerStatus);
}
