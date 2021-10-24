package com.deft.crud.sales.model.service;

import java.time.LocalDate;
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
	
	/* 사원의 월간 실적 조회 */
	public List<PerformanceDTO> empPerformList(TargetPerfomDTO parameters) {

		return salesMapper.empPerformList(parameters);
	}
	
	/* 뷰에서 선택한 연도의 목표실적 조회 */
	public List<TargetPerfomDTO> checkedEmpTargetPerformList(TargetPerfomDTO parameters) {

		return salesMapper.checkedEmpTargetPerformList(parameters);
	}
	
	/* 전 달 사원 실적 조회*/
	public PerformanceDTO selectEmpPeformLastMonth(UserImpl loginInfo) {
		
		/* LocalDate로 현재 시간 기준으로 연도와 전 달을 가지고 온다 */
		LocalDate lastMonth = LocalDate.now().minusMonths(1);
		
		int collectBillYear = lastMonth.getYear();
		int collectBillMonth = lastMonth.getMonthValue();
		
		/* 현재 연도와 전 달에 대한 값을 dto에 담아준다. */
		CollectBillDTO collectBillDate = new CollectBillDTO();
		collectBillDate.setCollectBillYear(collectBillYear);
		collectBillDate.setCollectBillMonth(collectBillMonth);
		
		return salesMapper.selectEmpPeformLastMonth(loginInfo, collectBillDate);
	}
	
	/* 한 사원의 목표 실적을 조회하기 위해 전체 목표 실적을 가지고 온 후
	 * 해당 사원이 이미 현재 연도와 월에 입력한 목표 실적을 등록을 했다면 
	 * 해당 월에 중복으로 등록을 못하게 로직 처리를 한다.*/
	@Transactional
	public int insertTargetSales(TargetPerfomDTO parameters, UserImpl loginInfo) {
		
		/* 세션에 저장된 회원 번호를 변수에 초기화한다. */
		int empNo = loginInfo.getEmpNo();
		
		/* 목표실적에 등록할 사원번호를 DTO에 담아준다. */
		parameters.setEmpNo(empNo);
		
		/* 성공 실패 여부 */
		int result = 0;
		boolean isSaved = false; 
		
		/* 해당 사원이 등록했던 모든 실적 조회 */
		List<TargetPerfomDTO> checkEmpTargetPerformListForInsert = salesMapper.checkEmpTargetPerformListForInsert(empNo);
		
		/* 사원이 목표실적을 등록했을때 현재 연도와 월과 기존에 저장되어있던 연도와 월을 비교한다.
		 * 같은 연도와 월이 없으면 목표실적을 등록할 수 있고 아니면 등록할 수 없다. */
		for(int i = 0; i < checkEmpTargetPerformListForInsert.size(); i++) {
			if(parameters.getPerformYear().equals(checkEmpTargetPerformListForInsert.get(i).getPerformYear()) && parameters.getPerformMonth().equals(checkEmpTargetPerformListForInsert.get(i).getPerformMonth())) {
				isSaved = true;
			}
		}
		
		if(!isSaved) {
			result = salesMapper.insertTargetSales(parameters);
		}
		
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
	
	/* 한 부서에 대한 사원들의 연도와 월에 맞게 목표실적을 조회한다. */
	public List<TargetPerfomDTO> deptTargetPerformList(CollectBillDTO parameters) {

		return salesMapper.deptTargetPerformList(parameters);
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
	
	/* 폼에서 refCategoryCode가 0이면 카테고리 전체를 실적을 조회하게 설정했다. 
	 * refCategoryCode가 0이면 카테고리를 바꾸지 않고 연도와 날짜만 바꾼 상태이며
	 * refCategoryCode가 0이 아니라면 선택한 카테고리와 연도를 조회한다.*/
	public List<PerformanceDTO> selectCategoryPerformForDate(CollectBillDTO parameters, int refCategoryCode) {

		if(refCategoryCode == 0) {
			
			List<PerformanceDTO> selectCategoryPerformList = salesMapper.selectCategoryPerformList(parameters);

			return selectCategoryPerformList;
			
		} else {
			
			List<PerformanceDTO> selectCategoryPerformForDate = salesMapper.selectCategoryPerformForDate(parameters, refCategoryCode);
			
			return selectCategoryPerformForDate;
		}
		
	}
	
	/* 카테고리에 자세한 실적 조회 */
	public List<PerformanceDTO> selectCategoryPerformDetail(int categoryCode, CollectBillDTO parameters) {

		return salesMapper.selectCategoryPerformDetail(categoryCode, parameters);
	}
	
	/* 대시보드 화면에서 사원이 현재 가지고 있는 상품실적 정보*/
	public List<PerformanceDTO> productSalesThisMonth(UserImpl loginInfo) {

		/* 현재 연도 정보를 LocalDate에서 가지고 온다.*/
		LocalDate collectBillDate = LocalDate.now();
		int collectBillYear = collectBillDate.getYear();
		int collectBillMonth = collectBillDate.getMonthValue();
		int empNo = loginInfo.getEmpNo();
		
		/* PerformanceDTO 속성에 있는 CollectBillDTO타입의 속성을 담기 위해 DTO를 생성해준다. */ 
		CollectBillDTO collect = new CollectBillDTO();
		
		PerformanceDTO empSalesInfo = new PerformanceDTO();
		empSalesInfo.setCollect(collect);
		empSalesInfo.getCollect().setCollectBillYear(collectBillYear);
		empSalesInfo.getCollect().setCollectBillMonth(collectBillMonth);
		empSalesInfo.setEmpNo(empNo);
		
		return salesMapper.productSalesThisMonth(empSalesInfo);
	}


}
