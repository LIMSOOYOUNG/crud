package com.deft.crud.sales.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.product.model.dto.ProductCategoryDTO;
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
	
	/* 한 명의 사원 목표 실적을 조회하기 위한 컨트롤러(현재 연도와 월 기준) */
	public List<TargetPerfomDTO> empTargetPerformList(TargetPerfomDTO targetPerformDate) {
		
		return salesMapper.empTargetPerformList(targetPerformDate);
	}

	public List<PerformanceDTO> empPerformList(TargetPerfomDTO parameters) {

		return salesMapper.empPerformList(parameters);
	}

	public List<TargetPerfomDTO> checkedEmpTargetPerformList(TargetPerfomDTO parameters) {

		return salesMapper.checkedEmpTargetPerformList(parameters);
	}
	
	/* 전 달 부서 평균 실적 조회 */
	public PerformanceDTO selectDeptAvgPeform(String deptCode, CollectBillDTO collectBillDate) {

		return salesMapper.selectDeptAvgPeform(deptCode, collectBillDate);
	}

	
	/* 목표실적 등록하는 메소드*/
	@Transactional
	public int insertTargetSales(TargetPerfomDTO parameters, UserImpl loginInfo) {
		
		
		/* 한 사원의 목표 실적을 조회하기 위해 전체 목표 실적을 가지고 온 후
		 * 해당 사원이 이미 현재 연도와 월에 입력한 목표 실적을 등록을 했다면 
		 * 해당 월에 중복으로 등록을 못하게 로직 처리를 한다.*/
		int empNo = loginInfo.getEmpNo();
		
		System.out.println("empNo : " + empNo);
		
		int result = 0;
		boolean isSaved = false; 
		
		List<TargetPerfomDTO> checkEmpTargetPerformListForInsert = salesMapper.checkEmpTargetPerformListForInsert(empNo);
		
		for(int i = 0; i < checkEmpTargetPerformListForInsert.size(); i++) {
			if(parameters.getPerformYear().equals(checkEmpTargetPerformListForInsert.get(i).getPerformYear()) && parameters.getPerformMonth().equals(checkEmpTargetPerformListForInsert.get(i).getPerformMonth())) {
				isSaved = true;
			}
		}
		
		if(!isSaved) {
			result = salesMapper.insertTargetSales(parameters);
		}
		
		System.out.println("parameters : " + parameters);
		return result;
		
	}

	/* 개인 실적 상세조회 */
	public List<PerformanceDTO> selectUserPerformDetail(int empNo, CollectBillDTO parameters) {
		
		return salesMapper.selectUserPerformDetail(empNo, parameters);
	}
	
	
	/* 부서 실적 조회*/
	public List<PerformanceDTO> selectDeptPerformList(CollectBillDTO collectBillDate) {

		return salesMapper.selectDeptPerformList(collectBillDate);
	}
	
	
	/* 부서별 사원 실적 전체 조회*/
	public List<PerformanceDTO> selectDeptEachPerformList(CollectBillDTO parameters, String deptCode) {
		
		return salesMapper.selectDeptEachPerformList(parameters, deptCode);
	}

	/* 부서 실적 뷰에서 선택한 연도와 월에 맞게 실적을 조회하는 메소드*/
	public List<PerformanceDTO> selectPerformForDate(CollectBillDTO parameters) {
		
		return salesMapper.selectPerformForDate(parameters);
	}
	
	public List<TargetPerfomDTO> deptTargetPerformList(CollectBillDTO parameters) {

		return salesMapper.deptTargetPerformList(parameters);
	}
	
	
	public PerformanceDTO selectEmpPeformLastMonth(int empNo, CollectBillDTO collectBillDate) {

		return salesMapper.selectEmpPeformLastMonth(empNo, collectBillDate);
	}
	
	/* 상품 실적 조회 */
	public List<PerformanceDTO> selectProductPerformList(CollectBillDTO collectBillDate) {

		return salesMapper.selectProductPerformList(collectBillDate);
	}
	
	/* 날짜별로 상품 실적 조회 */
	public List<PerformanceDTO> selectProductPeformForDate(CollectBillDTO parameters) {

		return salesMapper.selectProductPeformForDate(parameters);
	}
	
	/* 카테고리 실적 조회 */
	public List<PerformanceDTO> selectCategoryPerformList(CollectBillDTO collectBillDate) {

		return salesMapper.selectCategoryPerformList(collectBillDate);
	}
	
	/* 상위 카테고리 조회 */
	public List<ProductCategoryDTO> selectRefCategoryList() {

		return salesMapper.selectRefCategoryList();
	}

	public List<PerformanceDTO> selectCategoryPerformForDate(CollectBillDTO parameters, int refCategoryCode) {

		if(refCategoryCode == 0) {
			
			List<PerformanceDTO> selectCategoryPerformList = salesMapper.selectCategoryPerformList(parameters);
			
			return selectCategoryPerformList;
		 
		} else {
			
			List<PerformanceDTO> selectCategoryPerformForDate = salesMapper.selectCategoryPerformForDate(parameters, refCategoryCode);
			
			return selectCategoryPerformForDate;
		}
		
	}


}
