package com.deft.crud.business.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.configuration.CrudApplication;
import com.deft.crud.configuration.MybatisConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class, MybatisConfiguration.class})
public class BusinessControllerTests {

	@Autowired
	private BusinessController businessController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(businessController).build();
	}
	
	/* 전체 영업기회 목록 조회 */
	@Test
	@Disabled
	public void testSelectBusinessChanceAll() throws Exception {
		
		mockMvc.perform(get("/business/chance/selectAll"))
			   .andExpect(status().isOk())
			   .andExpect(forwardedUrl("business/businessChanceList"))
			   .andDo(print());
	}
	
	/* 영업기회 상태변경이력 + 선택한 영업기회 정보 + 현재영업기회 관련 고객에 대한 활동 내역*/
	@Test
	@Disabled
	public void testSelectBasicInfoByNo() throws Exception {
		
		mockMvc.perform(get("/business/chance/selectBasicInfo")
				.param("customerNo", "1")
				.param("businessChanceNo", "1"))
		   .andExpect(status().isOk())
		   .andExpect(forwardedUrl("business/businessChanceInfo"))
		   .andDo(print());
	}
	
	/* 전체사원 영업활동 목록조회 (담당자용)*/
	@Test
	public void testSelectActivityAll() throws Exception {
		
		mockMvc.perform(get("/business//activity/selectAll"))
		   .andExpect(status().isOk())
		   .andExpect(forwardedUrl("business/businessActivityList"))
		   .andDo(print());
	}
	
}
















