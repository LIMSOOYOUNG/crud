package com.deft.crud.sales.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.sales.model.dto.TargetPerfomDTO;


@Mapper
public interface SalesMapper {

	List<TargetPerfomDTO> empPerformList(int loginEmpNo);

	int insertTargetSales(TargetPerfomDTO parameters);
	
}
