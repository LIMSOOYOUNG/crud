package com.deft.crud.customer.model.dao;

import com.deft.crud.customer.model.dto.BusinessActivityDTO;
import com.deft.crud.customer.model.dto.CustomerCompanyDTO;
import com.deft.crud.customer.model.dto.CustomerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    List<CustomerCompanyDTO> selectAllCustomer();

    List<CustomerCompanyDTO> selectAllAnalysisCustomer();

    CustomerCompanyDTO selectCustomerInfo(int customerNo);

    CustomerCompanyDTO selectAnalysisCustomerInfo(int customerNo);

    List<BusinessActivityDTO> selectBusinessActivity(int customerNo);
}
