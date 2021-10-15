package com.deft.crud.collection.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.collection.model.dao.CollectionMapper;
import com.deft.crud.collection.model.dto.ChargeDTO;

@Service
public class CollectionService {

	private CollectionMapper collectionMapper;
	
	@Autowired
	public CollectionService(CollectionMapper collectionMapper) {
		this.collectionMapper = collectionMapper;
	}
	
	/* 수금 현황 조회*/
	public List<ChargeDTO> selectCollectionAll(ChargeDTO chargeDate) {

		return collectionMapper.selectCollectionAll(chargeDate);
	}
	
	/* 날짜별 수금 현황 조회*/
	public List<ChargeDTO> selectCollectionForDate(ChargeDTO charge) {
		
		return collectionMapper.selectCollectionForDate(charge);
	}
}
