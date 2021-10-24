package com.deft.crud.sales.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.sales.model.dto.CollectBillDTO;
import com.deft.crud.sales.model.dto.PerformanceDTO;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;

@Mapper
public interface SalesMapper {
	
	/* 한 명의 사원 목표 실적을 조회하기(현재 연도와 월 기준) */
	List<TargetPerfomDTO> empTargetPerformList(TargetPerfomDTO targetPerformDate);
	
	/* 목표실적 등록*/
	int insertTargetSales(TargetPerfomDTO parameters);
	
	/* 사원이 목표실적을 등록할 때 */
	List<TargetPerfomDTO> checkEmpTargetPerformListForInsert(int empNo);

	/* 사원의 월간 실적 조회 */
	List<PerformanceDTO> empPerformList(TargetPerfomDTO parameters);
	
	/* 뷰에서 선택한 연도의 목표실적 조회 */
	List<TargetPerfomDTO> checkedEmpTargetPerformList(TargetPerfomDTO parameters);

	/* 개인 실적 상세조회 */
	List<PerformanceDTO> selectUserPerformDetail(int empNo, CollectBillDTO parameters);

	/* 부서 실적 조회*/
	List<PerformanceDTO> selectDeptPerformList(CollectBillDTO collectBillDate);

	/* 부서별 사원 실적 전체 조회*/
	List<PerformanceDTO> selectDeptEachPerformList(CollectBillDTO parameters, String deptCode);

	/* 부서 실적 뷰에서 선택한 연도와 월에 맞게 실적을 조회하는 메소드 */
	List<PerformanceDTO> selectPerformForDate(CollectBillDTO parameters);

	/* 한 부서에 대한 사원들의 연도와 월에 맞게 목표실적을 조회한다. */
	List<TargetPerfomDTO> deptTargetPerformList(CollectBillDTO parameters);

	/* 전 달 사원 실적 조회*/
	PerformanceDTO selectEmpPeformLastMonth(UserImpl loginInfo, CollectBillDTO collectBillDate);

	/* 상품 실적 조회 */
	List<PerformanceDTO> selectProductPerformList(CollectBillDTO collectBillDate);

	/* 날짜별로 상품 실적 조회 */
	List<PerformanceDTO> selectProductPeformForDate(CollectBillDTO parameters);
	
	/* 카테고리 실적 조회 */
	List<PerformanceDTO> selectCategoryPerformList(CollectBillDTO collectBillDate);
	
	/* 상위 카테고리 조회 */
	List<ProductCategoryDTO> selectRefCategoryList();
	
	/* 날짜별로 카테고리 실적 조회 */
	List<PerformanceDTO> selectCategoryPerformForDate(CollectBillDTO parameters, int refCategoryCode);

	/* 카테고리에 자세한 실적 조회 */
	List<PerformanceDTO> selectCategoryPerformDetail(int categoryCode, CollectBillDTO parameters);

	/* 대시보드 화면에서 사원이 현재 가지고 있는 상품 정보*/
	List<PerformanceDTO> productSalesThisMonth(PerformanceDTO empSalesInfo);
	
}
