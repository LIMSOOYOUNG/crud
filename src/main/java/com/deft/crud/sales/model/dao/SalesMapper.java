package com.deft.crud.sales.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.sales.model.dto.CollectBillDTO;
import com.deft.crud.sales.model.dto.PerformanceDTO;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;

@Mapper
public interface SalesMapper {

	List<TargetPerfomDTO> empTargetPerformList(int empNo, TargetPerfomDTO targetPerformDate);

//	int insertTargetSales(TargetPerfomDTO parameters);

	List<PerformanceDTO> empPerformList(TargetPerfomDTO parameters);

	List<PerformanceDTO> selectUserPerformDetail(int empNo, CollectBillDTO collectBillDate);

	List<PerformanceDTO> selectDeptPerformList(CollectBillDTO collectBillDate);

	List<PerformanceDTO> selectDeptEachPerformList(CollectBillDTO parameters, String deptCode);

	List<PerformanceDTO> selectPerformForDate(CollectBillDTO parameters);

	List<TargetPerfomDTO> deptTargetPerformList(CollectBillDTO parameters);
	
}
