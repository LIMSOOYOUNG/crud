package com.deft.crud.estimate.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
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

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class, MybatisConfiguration.class})
public class EstimateControllerTests {
	
	@Autowired
	private EstimateController estimateController;
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(estimateController).build();
	}
	
	@Test
	public void testSelectAllEstimate() throws Exception {
		
		mockMvc.perform(get("/estimate/selectAll"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("estimate/selectAll"))
				.andDo(print());
	}
}
