package com.deft.crud.estimate.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.estimate.model.dao.EstimateMapper;
import com.deft.crud.estimate.model.dto.EstimateDTO;

@Service
public class EstimateServiceImpl implements EstimateService {
	
	private final EstimateMapper estimateMapper;
	
	@Autowired
	public EstimateServiceImpl(EstimateMapper estimateMapper) {
		this.estimateMapper = estimateMapper;
	}

	@Override
	public List<EstimateDTO> estimateSelectAll() {
		
		return estimateMapper.estimateSelectAll();
	}
}
