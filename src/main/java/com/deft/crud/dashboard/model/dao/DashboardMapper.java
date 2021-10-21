package com.deft.crud.dashboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.dashboard.model.dto.BusinessChanceDTO;
import com.deft.crud.dashboard.model.dto.EmpInfoDTO;
import com.deft.crud.member.model.service.UserImpl;

@Mapper
public interface DashboardMapper {
	
	/* 사원 실적 그래프 */
	Integer userSalesChart(int empNo, int collectBillYear, int collectBillMonth);
	
	/* 사원 목표실적 그래프*/
	Integer userTargetSalesChart(int empNo, int collectBillYear, int collectBillMonth);

	/* 부서 실적 그래프 */
	Integer deptSalesChart(UserImpl empInfo, int collectBillYear, int collectBillMonth);

	/* 유도 경로별 그래프 */
	List<BusinessChanceDTO> businessChanceChart(EmpInfoDTO empInfo);

	/* 실패한 영업기회 수 */
	List<BusinessChanceDTO> failedChart(int empNo, int businessChanceFailedYear);


	
}
