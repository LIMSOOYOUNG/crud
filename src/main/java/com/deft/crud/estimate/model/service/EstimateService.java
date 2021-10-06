package com.deft.crud.estimate.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public List<EstimateDTO> selectEstimateListByStatus(String estimateStatus) {
		
		return estimateMapper.selectEstimateListByStatus(estimateStatus);
	}

	public String selectEstimateNo(String newEstimateDate) {
		
		String newEstimateSeq = estimateMapper.selectLastEstimateSeq();
		String newEstimateNo = "E" + newEstimateDate.replace("-", "") + "-" + newEstimateSeq;
		
		return newEstimateNo;
	}

	@Transactional
	public int insertEstimate(EstimateDTO estimateInfo) {
		
		return estimateMapper.insertEstimate(estimateInfo);
	}
}
