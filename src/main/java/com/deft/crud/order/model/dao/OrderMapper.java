package com.deft.crud.order.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.order.model.dto.OrderDTO;
import com.deft.crud.order.model.dto.OrderProductDTO;

@Mapper
public interface OrderMapper {

	List<OrderDTO> selectOrderList(int empNo);

	OrderDTO selectOrderDetail(String orderNo);

	String selectLastOrderSeq();

	int insertOrderInfo(OrderDTO orderInfo);

	int insertOrderProduct(OrderProductDTO product);
}
