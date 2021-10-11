package com.deft.crud.estimate.controller;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
	@Disabled
	public void testSelectEstimateList() throws Exception {
		
		mockMvc.perform(get("/estimate/selectAll"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("estimate/selectAllEstimate"))
				.andDo(print());
	}
	
	@Test
	public void testSelectEstimateListByStatus() throws Exception {
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("estimateStatus", "progress");
		params.add("userImpl", "empNo");
		
		mockMvc.perform(get("/estimate/selectAll/status").params(params))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("jsonView"))
				.andDo(print());
	}
}
