package com.deft.crud.order.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.order.model.dao.OrderMapper;
import com.deft.crud.order.model.dto.OrderDTO;
import com.deft.crud.order.model.dto.OrderProductDTO;

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

	public OrderDTO selectOrderDetail(String orderNo) {
		
		OrderDTO order = orderMapper.selectOrderDetail(orderNo);
		
		/* 할인 적용 여부 확인 */
		int discountRate = order.getDiscountRate();
		
		if(discountRate > 0) {
			order.setDiscountStatus("Y");
		} else {
			order.setDiscountStatus("N");			
		}
		
		/* 할인 적용한 합계 금액 계산 */
		int total = 0;
		
		for(OrderProductDTO orderProduct : order.getOrderProductList()) {
			int sellingPrice = orderProduct.getProduct().getSellingPrice();
			int amount = orderProduct.getProductAmount();
			
			int discountedPrice = (int) (sellingPrice * (1 - (discountRate * 0.01)));
			int subtotal = discountedPrice * amount;
			int tax = (int) (subtotal * 0.1);
			
			orderProduct.getProduct().setDiscountedPrice(discountedPrice);
			orderProduct.getProduct().setSubtotal(subtotal);
			orderProduct.getProduct().setTax(tax);
			
			total += subtotal + tax;
		}
		
		order.setSumPrice(total);
		
		return order;
	}
}
