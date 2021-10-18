package com.deft.crud.dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.dashboard.model.dao.DashboardMapper;
import com.deft.crud.dashboard.model.dto.BusinessChanceDTO;
import com.deft.crud.dashboard.model.dto.EmpInfoDTO;
import com.deft.crud.dashboard.model.dto.PerformanceDTO;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;

@Service
public class DashBoardService {

	private DashboardMapper dashBoardMapper;
	
	@Autowired
	public DashBoardService(DashboardMapper dashBoardMapper) {
		this.dashBoardMapper = dashBoardMapper;
	}
	
	/* 사원 실적 그래프 */
	public List<PerformanceDTO> userPerformChart(int empNo, int collectBillYear) {
		
		return dashBoardMapper.userPerformChart(empNo, collectBillYear);
	}
	
	/* 사원 목표 실적 그래프*/
	public List<TargetPerfomDTO> userTargetPerformChart(int empNo, int collectBillYear) {
		
		return dashBoardMapper.userTargetPerformChart(empNo, collectBillYear);
	}

	/* 부서 실적 그래프 */
	public List<PerformanceDTO> deptPerformChart(EmpInfoDTO empInfo, int collectBillYear) {
		
		return dashBoardMapper.deptPerformChart(empInfo, collectBillYear);
	}

	public List<BusinessChanceDTO> failedChart(int empNo, int collectBillYear) {
		
		return dashBoardMapper.failedChart(empNo, collectBillYear);
	}



}
