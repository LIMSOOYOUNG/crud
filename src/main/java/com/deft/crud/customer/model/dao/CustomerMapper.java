package com.deft.crud.customer.model.dao;

import com.deft.crud.customer.model.dto.BusinessActivityDTO;
import com.deft.crud.customer.model.dto.CustomerCompanyDTO;
import com.deft.crud.customer.model.dto.CustomerDTO;
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
}
