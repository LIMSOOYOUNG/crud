package com.deft.crud.business.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.business.model.dto.BusinessDTO;

@Mapper
public interface BusinessMapper {

	List<BusinessDTO> businessChanceSelectAll();

}
