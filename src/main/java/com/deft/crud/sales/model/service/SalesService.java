package com.deft.crud.sales.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deft.crud.sales.model.dao.SalesMapper;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;

@Service
public class SalesService {
	
	private SalesMapper salesMapper;

	@Autowired
	public SalesService(SalesMapper salesMapper) {
		this.salesMapper = salesMapper;
	}
	
	public List<TargetPerfomDTO> empPerformList(int loginEmpNo) {

		return salesMapper.empPerformList(loginEmpNo);
	}

	@Transactional
	public int insertTargetSales(TargetPerfomDTO parameters) {

		return salesMapper.insertTargetSales(parameters);
	}
	

}
