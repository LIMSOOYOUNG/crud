package com.deft.crud.sales.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.sales.model.dao.SalesMapper;
import com.deft.crud.sales.model.dto.CollectBillDTO;
import com.deft.crud.sales.model.dto.PerformanceDTO;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;

@Service
public class SalesService {
	
	private SalesMapper salesMapper;

	@Autowired
	public SalesService(SalesMapper salesMapper) {
		this.salesMapper = salesMapper;
	}
	
	public List<TargetPerfomDTO> empTargetPerformList(int empNo, TargetPerfomDTO targetPerformDate) {
		
		
		return salesMapper.empTargetPerformList(empNo, targetPerformDate);
	}

	public List<PerformanceDTO> empPerformList(TargetPerfomDTO parameters) {

		return salesMapper.empPerformList(parameters);
	}

//	@Transactional
//	public int insertTargetSales(TargetPerfomDTO parameters, UserImpl loginInfo) {
//		
//		
//		/* 한 사원의 목표 실적을 조회하기 위해 전체 목표 실적을 가지고 온 후
//		 * 해당 사원이 이미 현재 연도와 월에 입력한 목표 실적을 등록을 했다면 
//		 * 해당 월에 중복으로 등록을 못하게 로직 처리를 한다.*/
//		int empNo = loginInfo.getEmpNo();
//		
//		System.out.println("empNo : " + empNo);
//		
//		int result = 0;
//		boolean isSaved = false; 
//		
//		List<TargetPerfomDTO> empTargetPerformList = salesMapper.empTargetPerformList(empNo);
//		
//		for(int i = 0; i < empTargetPerformList.size(); i++) {
//			if(parameters.getPerformYear().equals(empTargetPerformList.get(i).getPerformYear()) && parameters.getPerformMonth().equals(empTargetPerformList.get(i).getPerformMonth())) {
//				isSaved = true;
//			}
//		}
//		
//		if(!isSaved) {
//			result = salesMapper.insertTargetSales(parameters);
//		}
//		
//		System.out.println("parameters : " + parameters);
//		return result;
//		
//	}

	/* 개인 실적 상세조회 */
	public List<PerformanceDTO> selectUserPerformDetail(int empNo, CollectBillDTO collectBillDate) {
		
		return salesMapper.selectUserPerformDetail(empNo, collectBillDate);
	}
	
	
	/* 부서 실적 조회*/
	public List<PerformanceDTO> selectDeptPerformList(CollectBillDTO collectBillDate) {

		return salesMapper.selectDeptPerformList(collectBillDate);
	}
	
	
	/* 부서별 사원 실적 전체 조회*/
	public List<PerformanceDTO> selectDeptEachPerformList(CollectBillDTO parameters, String deptCode) {
		
		return salesMapper.selectDeptEachPerformList(parameters, deptCode);
	}

	public List<PerformanceDTO> selectPerformForDate(CollectBillDTO parameters) {
		
		return salesMapper.selectPerformForDate(parameters);
	}

	public List<TargetPerfomDTO> deptTargetPerformList(CollectBillDTO parameters) {

		return salesMapper.deptTargetPerformList(parameters);
	}

	

}
