package com.deft.crud.sales.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.sales.model.dto.PerformanceDTO;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;


@Mapper
public interface SalesMapper {

	List<TargetPerfomDTO> empTargetPerformList(int empNo);

	int insertTargetSales(TargetPerfomDTO parameters);

	List<PerformanceDTO> empPerformList(int empNo);
	
}
