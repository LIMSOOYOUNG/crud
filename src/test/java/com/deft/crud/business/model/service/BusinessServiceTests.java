package com.deft.crud.business.model.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.deft.crud.business.model.service.BusinessService;
import com.deft.crud.configuration.CrudApplication;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class})
public class BusinessServiceTests {

	@Autowired
	private BusinessService busienssService;
	
//	/* 전체 영업기회 목록 조회 */
//	@Test
//	@Disabled
//	public void testSelectBusinessChanceAll() {
//
//		List<BusinessDTO> businessChanceList = busienssService.businessChanceSelectAll();
//
//		assertNotNull(businessChanceList);
//
//		for(BusinessDTO businessChance : businessChanceList) {
//			System.out.println(businessChance);
//		}
//
//	}
	
	
}
