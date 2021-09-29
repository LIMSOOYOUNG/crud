package com.deft.crud.business.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.business.model.dto.BusinessActivityDTO;
import com.deft.crud.business.model.dto.BusinessChanceDTO;
import com.deft.crud.business.model.dto.BusinessChanceHistoryDTO;

@Mapper
public interface BusinessMapper {

	List<BusinessChanceDTO> selectBusinessChanceAll();

	List<BusinessActivityDTO> selectActivityListByNo(int customerNo);

	BusinessChanceDTO selectChanceInfoByNo(int businessChanceNo);

	List<BusinessChanceHistoryDTO> selectChanceHistoryByNo(int businessChanceNo);

	List<BusinessActivityDTO> selectActivityAll();

}
