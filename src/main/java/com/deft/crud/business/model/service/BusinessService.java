package com.deft.crud.business.model.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.business.model.dao.BusinessMapper;
import com.deft.crud.business.model.dto.BusinessActivityDTO;
import com.deft.crud.business.model.dto.BusinessChanceDTO;
import com.deft.crud.business.model.dto.BusinessChanceHistoryDTO;
import com.deft.crud.customer.model.dao.CustomerMapper;
import com.deft.crud.customer.model.dto.CustomerDTO;
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
	
	/* 고객번호에 대한 영업활동기록 목록조회 */
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
	
	/* 담당중인 고객 리스트 */
	public List<CustomerDTO> selectMyCustomerList(UserImpl userInfo) {
		
		List<CustomerDTO> customerList = businessMapper.selectMyCustomerList(userInfo);
		
		return customerList;
	}

	/* 영업활동 등록 */
	public boolean insertActivity(BusinessActivityDTO parameters) {

		int result = 0;
		int insertResult = businessMapper.insertActivity(parameters);
		
		if (insertResult > 0) {
			result = 1;
		}
		
		return result > 0? true: false;
	}

	/* 영업활동 수정 */
	public boolean modifyActivity(BusinessActivityDTO parameters) {
		
		int result = 0;
		int insertResult = businessMapper.modifyActivity(parameters);
		
		if (insertResult > 0) {
			result = 1;
		}
		
		return result > 0? true: false;
	}

	/* 영업활동 삭제 */
	public boolean deleteActivity(int activityNo) {
		
		int result = 0;
		int insertResult = businessMapper.deleteActivity(activityNo);
		
		if (insertResult > 0) {
			result = 1;
		}
		
		return result > 0? true: false;
	}

	/* 고객 기본정보 조회 */
	public BusinessChanceDTO selectCustomerBasicInfo(int customerNo) {
		
		BusinessChanceDTO customerInfo = businessMapper.selectCustomerBasicInfo(customerNo);
		
		return customerInfo;
	}

	/* 영업기회 등록 */
	public Boolean insertBusinessChance(BusinessChanceDTO parameters) {
		
		int result = 0;
		int insertResult = businessMapper.insertBusinessChance(parameters);
		
		if (insertResult > 0) {
			result = 1;
		}
		
		return result > 0? true: false;
	}

	/* 영업기회 내용 수정 */
	public Boolean modifyBusinessChance(BusinessChanceDTO parameters) {
		
		int result = 0;
		
		int businessChanceNo = parameters.getBusinessChanceNo();
		
		/* 변경 전 영업기회 내용 조회 */
		BusinessChanceDTO businessChanceInfo = businessMapper.selectChanceInfoByNo(businessChanceNo);
		
		/* 영업기회 내용 변경 */
		int modifyResult = businessMapper.modifyBusinessChance(parameters);	          
		
		if (modifyResult > 0) {
			
			if(businessChanceInfo.getBusinessTitle().equals(parameters.getBusinessTitle())) {
				parameters.setBusinessTitle("");
			}

			if(businessChanceInfo.getProgressStatus().equals(parameters.getProgressStatus())) {
				parameters.setProgressStatus("");
			}
			
			if(businessChanceInfo.getSalesLevel().equals(parameters.getSalesLevel())) {
				parameters.setSalesLevel("");
			}
			
			if(businessChanceInfo.getProgressStatus().equals(parameters.getProgressStatus())) {
				parameters.setProgressStatus("");
			}
			
			if(businessChanceInfo.getSuccessPosibillity() == (parameters.getSuccessPosibillity())) {
				parameters.setSuccessPosibillity(999);
			}

			if(businessChanceInfo.getDueDate().equals(parameters.getDueDate())) {
				parameters.setDueDate(null);
			}
			
		/* 영업기회 변경이력 생성 */
			
		LocalDate newEstimateLocalDate = LocalDate.now();
			
		int insertHistoryResult = businessMapper.insertChanceHistory(parameters);	
			
			if(insertHistoryResult > 0) {
				
				result = 1;
			}
		}
		
		return result > 0? true: false;
	}
	
	

	

	

}
