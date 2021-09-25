package com.deft.crud.organization.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.organization.model.dao.OrganizationMapper;
import com.deft.crud.organization.model.dto.OrganizationDTO;

@Service
public class OrganizationService {

	private OrganizationMapper organizationMapper;
	
	@Autowired
	public OrganizationService(OrganizationMapper organizationMapper) {
		this.organizationMapper = organizationMapper;
	}
	
	
	public List<OrganizationDTO> selectOrganization() {
		
		List<OrganizationDTO> organizationList = organizationMapper.selectOrganization();
		
		return organizationList;
	}

	
}
