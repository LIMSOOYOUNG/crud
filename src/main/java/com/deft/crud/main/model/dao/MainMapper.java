package com.deft.crud.main.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {

	List<String> selectMenuList();

}
