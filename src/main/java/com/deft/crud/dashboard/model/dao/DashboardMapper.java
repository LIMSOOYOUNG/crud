package com.deft.crud.dashboard.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.dashboard.model.dto.PerformanceDTO;

@Mapper
public interface DashboardMapper {
	
	/* 사원 실적 그래프 */
	List<PerformanceDTO> userPerformChart(int empNo, int collectBillYear);

}
