package com.deft.crud.business.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.business.model.dao.BusinessMapper;
import com.deft.crud.business.model.dto.BusinessDTO;

@Service
public class BusinessService {

	private final BusinessMapper mapper;
	
	@Autowired
	public BusinessService(BusinessMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<BusinessDTO> businessChanceSelectAll() {

		List<BusinessDTO> businessChanceList = mapper.businessChanceSelectAll();
		
		
		return businessChanceList;
	}

}
