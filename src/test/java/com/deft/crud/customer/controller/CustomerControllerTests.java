package com.deft.crud.customer.controller;

import com.deft.crud.configuration.CrudApplication;
import com.deft.crud.configuration.MybatisConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class, MybatisConfiguration.class})
@SpringBootTest
public class CustomerControllerTests {

    @Autowired
    private CustomerController customerController;

    private final int customerNo = 1;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    /* 기존 고객 조회용 테스트 코드 */
    @Test
    @Disabled
    public void testSelectAllCustomer() throws Exception {

        mockMvc.perform(get("/customer/all"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("customer/selectAllCustomer"))
                .andDo(print());
    }

    /* 분석 고객 조회용 테스트 코드 */
    @Test
    @Disabled
    public void testSelectAllAnalysisCustomer() throws Exception {

        mockMvc.perform(get("/customer/ana"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("customer/selectAllAnalysisCustomer"))
                .andDo(print());
    }

    /* 기존 고객 기본정보 조회용 테스트 코드 */
    @Test
    @Disabled
    public void testSelectCustomerInfo(int customerNo) throws Exception {

        mockMvc.perform(get("/customer/cusinfo"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("customer/selectCustomerInfo"))
                .andDo(print());
    }

    /* 분석 고객 기본정보 조회용 테스트 코드 */
    @Test
    public void testSelectAnalysisCustomerInfo() throws Exception {

        mockMvc.perform(get("/customer/anainfo"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("customer/selectAnalysisCustomerInfo"))
                .andDo(print());
    }
}
