package com.deft.crud.order.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
		
		/* 저장된 주소를 우편번호, 주소, 상세주소로 나눠서 전달 */
		String deliveryPlace = order.getDeliveryPlace();
		
		String zipCode = deliveryPlace.split("\\$")[0];
		String address = order.getDeliveryPlace().split("\\$")[1];
		String addressDetail = order.getDeliveryPlace().split("\\$")[2];
		
		order.setZipCode(zipCode);
		order.setAddress(address);
		order.setAddressDetail(addressDetail);
		
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

	public String selectOrderNo(String newOrderDate) {
		
		String newOrderSeq = orderMapper.selectLastOrderSeq();
		String newOrderNo = "O" + newOrderDate.replace("-", "") + "-" + newOrderSeq;
		
		return newOrderNo;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
			rollbackFor = {Exception.class})
	public int insertOrder(OrderDTO orderInfo) {
		
		/* 우편번호, 주소, 상세주소를 하나의 주소로 변환하여 전달 */
		String deliveryPlace = orderInfo.getZipCode()
							+ "$" + orderInfo.getAddress()
							+ "$" + orderInfo.getAddressDetail();
		
		orderInfo.setDeliveryPlace(deliveryPlace);
		
		int orderInfoResult = orderMapper.insertOrderInfo(orderInfo);
		
		List<OrderProductDTO> productList = orderInfo.getOrderProductList();
		
		int orderProductResult = 0;
		for(OrderProductDTO product : productList) {
			product.setOrderNo(orderInfo.getOrderNo());
			
			orderProductResult += orderMapper.insertOrderProduct(product);
		}
		
		int result = 0;
		
		if(orderInfoResult > 0 && orderProductResult == productList.size()) {
			result = 1;
		}
		
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
			rollbackFor = {Exception.class})
	public int modifyOrder(OrderDTO orderInfo) {
		
		String deliveryPlace = orderInfo.getZipCode()
							+ "$" + orderInfo.getAddress()
							+ "$" + orderInfo.getAddressDetail();
		
		orderInfo.setDeliveryPlace(deliveryPlace);
		
		int orderInfoResult = orderMapper.updateOrderInfo(orderInfo);
		
		String orderNo = orderInfo.getOrderNo();
		int deleteProductResult = orderMapper.deleteOrderProduct(orderNo);
		
		List<OrderProductDTO> productList = orderInfo.getOrderProductList();
		
		int updateProductResult = 0;
		for(OrderProductDTO product : productList) {
			product.setOrderNo(orderInfo.getOrderNo());
			
			updateProductResult = orderMapper.updateOrderProduct(product);
		}
		
		int result = 0;
		
		if(orderInfoResult > 0 || (deleteProductResult > 0 && updateProductResult == productList.size())) {
			result = 1;
		}
		
		return result;
	}
	
	public List<OrderDTO> selectApprovedOrderList(int empNo) {
		
		return orderMapper.selectApprovedOrderList(empNo);
	}
}
