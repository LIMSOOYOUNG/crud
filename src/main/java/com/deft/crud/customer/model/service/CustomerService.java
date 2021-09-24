package com.deft.crud.customer.model.service;

import com.deft.crud.customer.model.dao.CustomerMapper;
import com.deft.crud.customer.model.dto.CustomerCompanyDTO;
import com.deft.crud.customer.model.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
