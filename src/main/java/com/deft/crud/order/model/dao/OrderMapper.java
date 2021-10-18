package com.deft.crud.order.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.order.model.dto.OrderDTO;

@Mapper
public interface OrderMapper {

	List<OrderDTO> selectOrderList(int empNo);

}
