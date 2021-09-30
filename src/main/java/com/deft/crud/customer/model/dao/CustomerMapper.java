package com.deft.crud.customer.model.dao;

import com.deft.crud.customer.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    /* 기존 고객 조회 */
    List<CustomerCompanyDTO> selectAllCustomer();

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
}
