package com.deft.crud.admin.adminemployee.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;



@Mapper
public interface AdminEmployeeMapper {

	List<AdminEmployeeDTO> detailselect(int employeeNo);


	

}
