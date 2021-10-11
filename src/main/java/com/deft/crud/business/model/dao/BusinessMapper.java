package com.deft.crud.business.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.business.model.dto.BusinessActivityDTO;
import com.deft.crud.business.model.dto.BusinessChanceDTO;
import com.deft.crud.business.model.dto.BusinessChanceHistoryDTO;
import com.deft.crud.customer.model.dto.CustomerDTO;
import com.deft.crud.member.model.service.UserImpl;

@Mapper
public interface BusinessMapper {

	/* 영업기회 전체조회(담당자 or 사원) */
	List<BusinessChanceDTO> selectBusinessChanceAll(UserImpl userInfo);
	
	/* 고객번호에 대한 영업활동기록 목록조회 */
	List<BusinessActivityDTO> selectActivityListByNo(int customerNo);
	
	/* 선택한 영업기회의 기본정보 */
	BusinessChanceDTO selectChanceInfoByNo(int businessChanceNo);

	/* 선택한 영업기회 내용 변경 이력*/
	List<BusinessChanceHistoryDTO> selectChanceHistoryByNo(int businessChanceNo);

	/* 영업활동 전체 조회 */
	List<BusinessActivityDTO> selectActivityAll(UserImpl userInfo);

	/* 영업기회 진행상태별 조회(담당자 or 사원) */
	List<BusinessChanceDTO> selectBusinessChanceByStatus(String businessChanceStatus, UserImpl userInfo);

	/* 선택한 영업활동의 상세정보 조회 */
	BusinessActivityDTO selectActivityDetailInfoByNo(int activityNo);
	
	/* 담당중인 고객 리스트 */
	List<CustomerDTO> selectMyCustomerList(UserImpl userInfo);

	/* 영업활동 등록 */
	int insertActivity(BusinessActivityDTO parameters);

	/* 영업활동 내용수정 */
	int modifyActivity(BusinessActivityDTO parameters);




}
