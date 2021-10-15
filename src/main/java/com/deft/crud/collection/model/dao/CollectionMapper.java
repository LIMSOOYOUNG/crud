package com.deft.crud.collection.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.collection.model.dto.ChargeDTO;

@Mapper
public interface CollectionMapper {
	
	/* 수금 현황 조회 */
	List<ChargeDTO> selectCollectionAll(ChargeDTO chargeDate);
	
	/* 날짜별 수금현황 조회*/
	List<ChargeDTO> selectCollectionForDate(ChargeDTO charge);

}
