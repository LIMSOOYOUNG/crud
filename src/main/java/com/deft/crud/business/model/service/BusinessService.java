package com.deft.crud.business.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.business.model.dao.BusinessMapper;
import com.deft.crud.business.model.dto.BusinessActivityDTO;
import com.deft.crud.business.model.dto.BusinessChanceDTO;
import com.deft.crud.business.model.dto.BusinessChanceHistoryDTO;
import com.deft.crud.customer.model.dao.CustomerMapper;
import com.deft.crud.member.model.service.UserImpl;

@Service
public class BusinessService {

	private final BusinessMapper businessMapper;
	private CustomerMapper customerMapper;
	
	@Autowired
	public BusinessService(BusinessMapper businessMapper) {
		this.businessMapper = businessMapper;
	}
	
	/* 영업기회 전체조회(담당자 or 사원) */
	public List<BusinessChanceDTO> selectBusinessChanceAll(UserImpl userInfo) {
		
		List<BusinessChanceDTO> businessChanceList = businessMapper.selectBusinessChanceAll(userInfo);
		
		return businessChanceList;
	}

	/* 영업기회 진행상태별 조회(담당자 or 사원) */
	public List<BusinessChanceDTO> selectBusinessChanceByStatus(String businessChanceStatus, UserImpl userInfo) {
		
		List<BusinessChanceDTO> businessChanceList = businessMapper.selectBusinessChanceByStatus(businessChanceStatus, userInfo);
		
		return businessChanceList;
	}
	
	/* 선택한 영업기회 내용 변경 이력*/
	public List<BusinessChanceHistoryDTO> selectChanceHistoryByNo(int businessChanceNo) {
		
		List<BusinessChanceHistoryDTO> chanceHistoryList = businessMapper.selectChanceHistoryByNo(businessChanceNo);
		
		
		return chanceHistoryList;
	}
	
	/* 선택한 영업기회의 기본정보 */
	public BusinessChanceDTO selectChanceInfoByNo(int businessChanceNo) {
		
		BusinessChanceDTO businessChanceInfo = businessMapper.selectChanceInfoByNo(businessChanceNo);
		
		return businessChanceInfo;
	}
	
	/* 고객번호에 대한 영업활동기록 목록조회*/
	public List<BusinessActivityDTO> selectActivityListByNo(int customerNo) {
		
		List<BusinessActivityDTO> businessActivityList = businessMapper.selectActivityListByNo(customerNo);
		
		return businessActivityList;
	}
 
	/* 전체사원 영업활동 목록조회 (담당자용)*/
	public List<BusinessActivityDTO> selectActivityAll(UserImpl userInfo) {
		
		List<BusinessActivityDTO> businessActivityList = businessMapper.selectActivityAll(userInfo);
		
		return businessActivityList;
	}

	public BusinessActivityDTO selectActivityDetailInfoByNo(int activityNo) {

		BusinessActivityDTO activityInfo = businessMapper.selectActivityDetailInfoByNo(activityNo);
		
		return activityInfo;
	}

	

	

}
