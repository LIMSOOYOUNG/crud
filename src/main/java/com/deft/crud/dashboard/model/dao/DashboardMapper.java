package com.deft.crud.dashboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.dashboard.model.dto.BusinessChanceDTO;
import com.deft.crud.dashboard.model.dto.EmpInfoDTO;
import com.deft.crud.dashboard.model.dto.PerformanceDTO;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;

@Mapper
public interface DashboardMapper {
	
	/* 사원 실적 그래프 */
	List<PerformanceDTO> userPerformChart(int empNo, int collectBillYear);
	
	/* 사원 목표실적 그래프*/
	List<TargetPerfomDTO> userTargetPerformChart(int empNo, int collectBillYear);

	/* 부서 실적 그래프 */
	List<PerformanceDTO> deptPerformChart(EmpInfoDTO empInfo, int collectBillYear);

	/* 유도 경로별 그래프 */
	List<BusinessChanceDTO> businessChanceChart(EmpInfoDTO empInfo);

	/* 실패한 영업기회 수 */
	List<BusinessChanceDTO> failedChart(int empNo, int collectBillYear);

	
}
