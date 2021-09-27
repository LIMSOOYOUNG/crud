package com.deft.crud.business.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.business.model.dao.BusinessMapper;
import com.deft.crud.business.model.dto.BusinessActivityDTO;
import com.deft.crud.business.model.dto.BusinessChanceDTO;
import com.deft.crud.customer.model.dao.CustomerMapper;

@Service
public class BusinessService {

	private final BusinessMapper businessMapper;
	private CustomerMapper customerMapper;
	
	@Autowired
	public BusinessService(BusinessMapper businessMapper) {
		this.businessMapper = businessMapper;
	}
	
	public List<BusinessChanceDTO> businessChanceSelectAll() {

		List<BusinessChanceDTO> businessChanceList = businessMapper.businessChanceSelectAll();
		
		
		return businessChanceList;
	}

	/* 선택한 영업기회 내용 변경 이력*/
	
	
	/* 선택한 엽업기회의 기본정보 */
	
	
	/* 영업활동기록 목록조회*/
	public List<BusinessActivityDTO> selectActivityInfoByNo(int customerNo) {
		List<BusinessActivityDTO> businessActivityList = businessMapper.selectActivityInfoByNo(customerNo);
		
		return businessActivityList;
	}

}
