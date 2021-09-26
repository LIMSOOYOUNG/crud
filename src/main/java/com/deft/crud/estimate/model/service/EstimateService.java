package com.deft.crud.estimate.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.estimate.model.dao.EstimateMapper;
import com.deft.crud.estimate.model.dto.EstimateDTO;

@Service
public class EstimateService {

	private final EstimateMapper estimateMapper;
	
	@Autowired
	public EstimateService(EstimateMapper estimateMapper) {
		
		this.estimateMapper = estimateMapper;
	}
	
	public List<EstimateDTO> selectEstimateList() {
		
		return estimateMapper.selectEstimateList();
	}
}
