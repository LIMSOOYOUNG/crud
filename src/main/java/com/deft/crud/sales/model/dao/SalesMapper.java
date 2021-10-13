package com.deft.crud.sales.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.sales.model.dto.CollectBillDTO;
import com.deft.crud.sales.model.dto.PerformanceDTO;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;

@Mapper
public interface SalesMapper {

	List<TargetPerfomDTO> empTargetPerformList(TargetPerfomDTO targetPerformDate);

	int insertTargetSales(TargetPerfomDTO parameters);

	List<PerformanceDTO> empPerformList(TargetPerfomDTO parameters);

	List<TargetPerfomDTO> checkedEmpTargetPerformList(TargetPerfomDTO parameters);

	List<PerformanceDTO> selectUserPerformDetail(int empNo, CollectBillDTO parameters);

	List<PerformanceDTO> selectDeptPerformList(CollectBillDTO collectBillDate);

	List<PerformanceDTO> selectDeptEachPerformList(CollectBillDTO parameters, String deptCode);

	List<PerformanceDTO> selectPerformForDate(CollectBillDTO parameters);

	List<TargetPerfomDTO> deptTargetPerformList(CollectBillDTO parameters);
	
	List<TargetPerfomDTO> checkEmpTargetPerformListForInsert(int empNo);

	PerformanceDTO selectDeptAvgPeform(String deptCode, CollectBillDTO collectBillDate);

	PerformanceDTO selectEmpPeformLastMonth(int empNo, CollectBillDTO collectBillDate);

	List<PerformanceDTO> selectProductPerformList(CollectBillDTO collectBillDate);

	List<PerformanceDTO> selectProductPeformForDate(CollectBillDTO parameters);

	List<PerformanceDTO> selectCategoryPerformList(CollectBillDTO collectBillDate);

	List<ProductCategoryDTO> selectRefCategoryList();

//	List<PerformanceDTO> selectCategoryPerformForDate(CollectBillDTO parameters, int refCategoryCode);
	
}
