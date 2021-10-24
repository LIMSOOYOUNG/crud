package com.deft.crud.charge.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.charge.model.dto.ChargeDTO;

@Mapper
public interface ChargeMapper {

	List<ChargeDTO> selectChargeList(int empNo);

	ChargeDTO selectChargeDetail(String chargeNo);

	String selectLastChargeSeq();

	int insertCharge(ChargeDTO chargeInfo);

	int updateCharge(ChargeDTO chargeInfo);
}
