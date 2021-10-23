package com.deft.crud.charge.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.charge.model.dao.ChargeMapper;
import com.deft.crud.charge.model.dto.ChargeDTO;

@Service
public class ChargeService {
	
	private final ChargeMapper chargeMapper;
	
	@Autowired
	public ChargeService(ChargeMapper chargeMapper) {
		
		this.chargeMapper = chargeMapper;
	}
	
	public List<ChargeDTO> selectChargeList(int empNo) {
		
		return chargeMapper.selectChargeList(empNo);
	}
}
