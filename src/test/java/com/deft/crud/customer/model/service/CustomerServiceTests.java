package com.deft.crud.customer.model.service;

import com.deft.crud.configuration.CrudApplication;
import com.deft.crud.customer.model.dto.BusinessActivityDTO;
import com.deft.crud.customer.model.dto.CustomerCompanyDTO;
import com.deft.crud.customer.model.dto.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class})
public class CustomerServiceTests {

    @Autowired
    private CustomerService customerService;

    private CustomerDTO testNewCustomer;

    //    @BeforeEach
//    public void init() {
//    }

    /* 기존 고객 조회용 테스트 코드 */
    @Test
    @Disabled
    public void testSelectAllCustomer() {

        List<CustomerCompanyDTO> customerList = customerService.selectAllCustomer();

        assertNotNull(customerList);

        for(CustomerCompanyDTO customer : customerList) {
            System.out.println(customer);
        }
    }

    /* 분석 고객 조회용 테스트 코드 */
    @Test
    @Disabled
    public void testSelectAllAnalysisCustomer() {

        List<CustomerCompanyDTO> customerList = customerService.selectAllAnalysisCustomer();

        assertNotNull(customerList);

        for(CustomerCompanyDTO customer : customerList) {
            System.out.println(customer);
        }
    }

    /* 기존 고객 기본정보 조회용 테스트 코드 */
    @Test
    @Disabled
    public void testSelectCustomerInfo() {

        int customerNo = 1;
        CustomerCompanyDTO customerInfo = customerService.selectCustomerInfo(customerNo);

        assertNotNull(customerInfo);

        System.out.println(customerInfo);
    }

    /* 분석 고객 기본정보 조회용 테스트 코드 */
    @Test
    @Disabled
    public void testSelectAnalysisCustomerInfo() {

        int customerNo = 3;
        CustomerCompanyDTO customerInfo = customerService.selectAnalysisCustomerInfo(customerNo);

        assertNotNull(customerInfo);

        System.out.println(customerInfo);
    }

    /* 영업 활동 조회용 테스트 코드 */
    @Test
    @Disabled
    public void testSelectBusinessActivity() {

        int customerNo = 1;
        List<BusinessActivityDTO> businessActivity = customerService.selectBusinessActivity(customerNo);

        assertNotNull(businessActivity);

        System.out.println(businessActivity);
    }

    @Test
//    @Disabled
    public void testSelectBusinessActivityByActivityNo() {

        int activityNo = 1;
        BusinessActivityDTO businessActivity = customerService.selectBusinessActivityByActivityNo(activityNo);

        assertNotNull(businessActivity);

        System.out.println(businessActivity);
    }
}
