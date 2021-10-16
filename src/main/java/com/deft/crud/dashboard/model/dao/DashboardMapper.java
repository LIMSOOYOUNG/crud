package com.deft.crud.dashboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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


}
