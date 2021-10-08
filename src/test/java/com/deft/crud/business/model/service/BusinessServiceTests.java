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

import com.deft.crud.business.model.dto.BusinessActivityDTO;
import com.deft.crud.business.model.dto.BusinessChanceDTO;
import com.deft.crud.business.model.dto.BusinessChanceHistoryDTO;
import com.deft.crud.business.model.service.BusinessService;
import com.deft.crud.configuration.CrudApplication;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class})
public class BusinessServiceTests {

	@Autowired
	private BusinessService businessService;
	
	/* 전체 영업기회 목록 조회 */
	@Test
	@Disabled
	public void testSelectBusinessChanceAll() {

//		List<BusinessChanceDTO> businessChanceList = businessService.selectBusinessChanceAll();

//		assertNotNull(businessChanceList);
//
//		for(BusinessChanceDTO businessChance : businessChanceList) {
//			System.out.println(businessChance);
//		}

	}
	
	/* 영업기회 상태변경이력 + 선택한 영업기회 정보 + 현재영업기회 관련 고객에 대한 활동 내역*/
	@Test
	@Disabled
	public void testSelectBasicInfoByNo() {
		
		/* 선택한 영업기회의 내용 변경내역*/
		List<BusinessChanceHistoryDTO> chanceHistoryList = businessService.selectChanceHistoryByNo(1);
		assertNotNull(chanceHistoryList);
		
		/* 선택한 엽업기회의 정보 */
		BusinessChanceDTO businessChanceInfo = businessService.selectChanceInfoByNo(3);
		assertNotNull(businessChanceInfo);
		
		/* 선택한 영업기회의 활동이력*/
		List<BusinessActivityDTO> businessActivityList = businessService.selectActivityListByNo(3);
		assertNotNull(businessActivityList);
	}
	
	/* 전체사원 영업활동 목록조회 (담당자용)*/
	@Test
	@Disabled
	public void testSelectActivityAll() {
		
		List<BusinessActivityDTO> businessActivityList = businessService.selectActivityAll();
		assertNotNull(businessActivityList);
	}
}









