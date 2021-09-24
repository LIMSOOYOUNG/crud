package com.deft.crud.customer.model.service;

import com.deft.crud.configuration.CrudApplication;
import com.deft.crud.customer.model.dto.CustomerCompanyDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class})
public class CustomerServiceTests {

    @Autowired
    private CustomerService customerService;

//    private CustomerDTO testNewCustomer;

//    @BeforeEach
//    public void init() {
//        this.testNewCustomer = new CustomerDTO();
//        this.testNewCustomer.setCustomerNo(999);
//        this.testNewCustomer.setCustomerCompanyNo(999);
//        this.testNewCustomer.setEmpNo(300);
//        this.testNewCustomer.setCustomerName("테스트담당사원");
//        this.testNewCustomer.
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
    public void testSelectAllAnalysisCustomer() {

        List<CustomerCompanyDTO> customerList = customerService.selectAllAnalysisCustomer();

        assertNotNull(customerList);

        for(CustomerCompanyDTO customer : customerList) {
            System.out.println(customer);
        }
    }
}
