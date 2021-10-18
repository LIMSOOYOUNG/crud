package com.deft.crud.order.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.order.model.dao.OrderMapper;
import com.deft.crud.order.model.dto.OrderDTO;

@Service
public class OrderService {
	
	private final OrderMapper orderMapper;
	
	@Autowired
	public OrderService(OrderMapper orderMapper) {
		
		this.orderMapper = orderMapper;
	}
	
	public List<OrderDTO> selectOrderList(int empNo) {
		
		return orderMapper.selectOrderList(empNo);
	}
}
