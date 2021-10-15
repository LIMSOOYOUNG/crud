package com.deft.crud.dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.dashboard.model.dao.DashboardMapper;
import com.deft.crud.dashboard.model.dto.PerformanceDTO;

@Service
public class DashBoardService {

	private DashboardMapper dashBoardMapper;
	
	@Autowired
	public DashBoardService(DashboardMapper dashBoardMapper) {
		this.dashBoardMapper = dashBoardMapper;
	}
	
	public List<PerformanceDTO> userPerformChart(int empNo, int collectBillYear) {
		
		return dashBoardMapper.userPerformChart(empNo, collectBillYear);
	}

}
