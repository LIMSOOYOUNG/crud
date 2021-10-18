package com.deft.crud.estimate.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.estimate.model.dto.EstimateDTO;
import com.deft.crud.estimate.model.dto.EstimateProductDTO;

@Mapper
public interface EstimateMapper {

	List<EstimateDTO> selectEstimateList(int empNo);

	List<EstimateDTO> selectEstimateListByStatus(String estimateStatus, int empNo);

	EstimateDTO selectEstimateDetail(String estimateNo);
	
	String selectLastEstimateSeq();

	int insertEstimateInfo(EstimateDTO estimateInfo);

	int insertEstimateProduct(EstimateProductDTO product);

	int updateEstimateInfo(EstimateDTO estimateInfo);

	int deleteEstimateProduct(String estimateNo);

	int updateEstimateProduct(EstimateProductDTO product);
}
