package com.deft.crud.business.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.business.model.dto.BusinessActivityDTO;
import com.deft.crud.business.model.dto.BusinessChanceDTO;

@Mapper
public interface BusinessMapper {

	List<BusinessChanceDTO> businessChanceSelectAll();

	List<BusinessActivityDTO> selectActivityInfoByNo(int customerNo);

}
