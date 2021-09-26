package com.deft.crud.stock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.deft.crud.configuration.CrudApplication;
import com.deft.crud.configuration.MybatisConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class, MybatisConfiguration.class})
@SpringBootTest
public class StockControllerTests {

	@Autowired
	private StockController stockController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
	}
	
	/* 창고보유(재고)상품 목록 조회 테스트 */
	@Test
	@Disabled
	public void testSelectStockAll() throws Exception {
		
		mockMvc.perform(get("/stock/selectAll"))
		 .andExpect(status().isOk())
         .andExpect(forwardedUrl("stock/stockList"))
         .andDo(print());
		
	}
	
	
}























