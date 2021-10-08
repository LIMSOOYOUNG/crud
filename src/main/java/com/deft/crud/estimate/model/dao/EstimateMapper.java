package com.deft.crud.estimate.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.estimate.model.dto.EstimateDTO;

@Mapper
public interface EstimateMapper {

	List<EstimateDTO> selectEstimateList();

	List<EstimateDTO> selectEstimateListByStatus(String estimateStatus);

	EstimateDTO selectEstimateDetail(String estimateNo);
	
	String selectLastEstimateSeq();

	int insertEstimate(EstimateDTO estimateInfo);
}
