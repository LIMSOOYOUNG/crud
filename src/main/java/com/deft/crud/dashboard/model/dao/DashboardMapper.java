package com.deft.crud.dashboard.model.dao;


import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.member.model.service.UserImpl;

@Mapper
public interface DashboardMapper {
	
	/* 사원 실적 그래프 */
	Integer userSalesChart(UserImpl loginInfo, int collectBillYear, int collectBillMonth);
	
	/* 사원 목표실적 그래프*/
	Integer userTargetSalesChart(UserImpl loginInfo, int collectBillYear, int collectBillMonth);

	/* 부서 실적 그래프 */
	Integer deptSalesChart(UserImpl loginInfo, int collectBillYear, int collectBillMonth);
	
	/* 실패한 영업기회 수 */
	Integer failedBusinessChanceChart(int failedBusinessChanceYear, int failedBusinessChanceMonth, UserImpl loginInfo);
	
	
}
