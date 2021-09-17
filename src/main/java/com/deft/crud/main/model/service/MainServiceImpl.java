package com.deft.crud.main.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.main.model.dao.MainMapper;

@Service
public class MainServiceImpl implements MainService{
	
	private MainMapper mainMapper;
	
	@Autowired
	public MainServiceImpl(MainMapper mainMapper) {
		this.mainMapper = mainMapper;
	}

	@Override
	public List<String> selectMenuList() {
		
		return mainMapper.selectMenuList();
	}

}
