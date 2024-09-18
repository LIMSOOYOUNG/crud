package com.deft.crud.dashboard.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.dashboard.model.dao.DashboardMapper;
import com.deft.crud.dashboard.model.dto.BusinessChanceDTO;
import com.deft.crud.dashboard.model.dto.EmpInfoDTO;
import com.deft.crud.dashboard.model.dto.PerformanceDTO;
import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;

@Service
public class DashBoardService {

	private DashboardMapper dashBoardMapper;
	
	@Autowired
	public DashBoardService(DashboardMapper dashBoardMapper) {
		this.dashBoardMapper = dashBoardMapper;
	}
	
	/* 사원 실적 그래프 */
	public List<Integer> userSalesChart(UserImpl loginInfo) {
		
		/* 현재 연도 정보를 LocalDate에서 가지고 온다.*/
		LocalDate collectBillDate = LocalDate.now();
		int collectBillYear = collectBillDate.getYear();
		
		/* null값도 담아주기 위해서 arrayList 생성 */
		List<Integer> salesList = new ArrayList<>();
		
		/* 1월부터 12월까지 실적 조회를 가지고 오기 위해 반복문을 돌리고 null값이 나오면 0 (기본값) 데이터가 있으면 그 값을 넣어준다. */
		for(int collectBillMonth = 1; collectBillMonth <= 12; collectBillMonth++) {
			
			Integer userSales = dashBoardMapper.userSalesChart(loginInfo, collectBillYear, collectBillMonth);
			
			if(userSales == null) {
				salesList.add(0);
			} else {
				salesList.add(userSales);
			}
			
		}
		
		return salesList;
	}
	
	/* 사원 목표 실적 그래프*/
	public List<Integer> userTargetSalesChart(UserImpl loginInfo) {
		
		/* 현재 연도 정보를 LocalDate에서 가지고 온다.*/
		LocalDate collectBillDate = LocalDate.now();
		int collectBillYear = collectBillDate.getYear();

		/* null값도 담아주기 위해서 arrayList 생성 */
		List<Integer> targetSalesList = new ArrayList<>();
		
		
		/* 1월부터 12월까지 실적 조회를 가지고 오기 위해 반복문을 돌리고 null값이 나오면 0 (기본값) 데이터가 있으면 그 값을 넣어준다. */
		for(int collectBillMonth = 1; collectBillMonth <= 12; collectBillMonth++) {
			
			Integer targetSales = dashBoardMapper.userTargetSalesChart(loginInfo, collectBillYear, collectBillMonth);
			
			if(targetSales == null) {
				targetSalesList.add(0);
			} else {
				targetSalesList.add(targetSales);
			}
			
		}
		
		return targetSalesList;
	}

	/* 부서 실적 그래프 */
	public List<Integer> deptSalesChart(UserImpl loginInfo) {
		
		/* 현재 연도 정보를 LocalDate에서 가지고 온다.*/
		LocalDate collectBillDate = LocalDate.now();
		int collectBillYear = collectBillDate.getYear();
		
		
		/* null값도 담아주기 위해서 arrayList 생성 */
		List<Integer> deptSalesList = new ArrayList<>();
		
		/* 1월부터 12월까지 실적 조회를 가지고 오기 위해 반복문을 돌리고 null값이 나오면 0 (기본값) 데이터가 있으면 그 값을 넣어준다. */
		for(int collectBillMonth = 1; collectBillMonth <= 12; collectBillMonth++) {
			
			Integer deptSales = dashBoardMapper.deptSalesChart(loginInfo, collectBillYear, collectBillMonth); 
			
			if(deptSales == null) {
				deptSalesList.add(0);
			} else {
				deptSalesList.add(deptSales);
			}
			
		}
		
		return deptSalesList;
	}

	/* 실패한 영업기회 차트 */
	public List<Integer> failedBusinessChanceChart(UserImpl loginInfo) {
		
		/* 현재 연도 정보를 LocalDate에서 가지고 온다.*/
		LocalDate failedBusinessChanceDate = LocalDate.now();
		int failedBusinessChanceYear = failedBusinessChanceDate.getYear();
		
		/* null값도 담아주기 위해서 arrayList 생성 */
		List<Integer> failedBusinessChanceList = new ArrayList<>();
		
		/* 1월부터 12월까지 실적 조회를 가지고 오기 위해 반복문을 돌리고 null값이 나오면 0 (기본값) 데이터가 있으면 그 값을 넣어준다. */
		for(int failedBusinessChanceMonth = 1; failedBusinessChanceMonth <= 12; failedBusinessChanceMonth++) {
			
			Integer failedBusinessChance = dashBoardMapper.failedBusinessChanceChart(failedBusinessChanceYear, failedBusinessChanceMonth, loginInfo);
			
			if(failedBusinessChance == null) {
				
				failedBusinessChanceList.add(0);
				
			} else {
				
				failedBusinessChanceList.add(failedBusinessChance);
				
			}
		}
		
		return failedBusinessChanceList;
	}

	public List<Integer> successBusinessChanceChart(UserImpl loginInfo) {
		
		/* 현재 연도 정보를 LocalDate에서 가지고 온다.*/
		LocalDate successBusinessChanceDate = LocalDate.now();
		int successBusinessChanceYear = successBusinessChanceDate.getYear();
		
		List<Integer> successBusinessChanceList = new ArrayList<>();
		
		for(int successBusinessChanceMonth = 1; successBusinessChanceMonth <= 12; successBusinessChanceMonth++) {
			
			Integer successBusinessChance = dashBoardMapper.successBusinessChanceChart(successBusinessChanceYear, successBusinessChanceMonth, loginInfo);
			
			if(successBusinessChance == null) {
				successBusinessChanceList.add(0);
			} else {
				successBusinessChanceList.add(successBusinessChance);
			}
			
		}
		
		return successBusinessChanceList;
	}

	

}
