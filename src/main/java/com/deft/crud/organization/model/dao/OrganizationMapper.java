package com.deft.crud.organization.model.dao;

import java.util.List;

import com.deft.crud.organization.model.dto.OrganizationDTO;

public interface OrganizationMapper {

	List<OrganizationDTO> selectOrganization();

}
