package com.deft.crud.organization.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.organization.model.dto.OrganizationDTO;

@Mapper
public interface OrganizationMapper {

	List<OrganizationDTO> selectOrganization();

}
