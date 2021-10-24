package com.deft.crud.charge.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deft.crud.charge.model.dao.ChargeMapper;
import com.deft.crud.charge.model.dto.ChargeDTO;
import com.deft.crud.order.model.dto.OrderDTO;
import com.deft.crud.order.model.dto.OrderProductDTO;

@Service
public class ChargeService {
	
	private final ChargeMapper chargeMapper;
	
	@Autowired
	public ChargeService(ChargeMapper chargeMapper) {
		
		this.chargeMapper = chargeMapper;
	}
	
	public List<ChargeDTO> selectChargeList(int empNo) {
		
		return chargeMapper.selectChargeList(empNo);
	}

	public ChargeDTO selectChargeDetail(String chargeNo) {
		
		ChargeDTO charge = chargeMapper.selectChargeDetail(chargeNo);
		
		/* 할인 적용 여부 확인 */
		int discountRate = charge.getOrder().getDiscountRate();
		
		if(discountRate > 0) {
			charge.getOrder().setDiscountStatus("Y");
		} else {
			charge.getOrder().setDiscountStatus("N");			
		}
		
		/* 할인 적용한 합계 금액 계산 */
		int total = 0;
		
		for(OrderProductDTO orderProduct : charge.getOrder().getOrderProductList()) {
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
		
		charge.getOrder().setSumPrice(total);

		return charge;
	}

	public String selectChargeNo(String newChargeDate) {
		
		String newChargeSeq = chargeMapper.selectLastChargeSeq();
		String newChargeNo = "C" + newChargeDate.replace("-", "") + "-" + newChargeSeq;
		
		return newChargeNo;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
			rollbackFor = {Exception.class})
	public int insertCharge(ChargeDTO chargeInfo) {
		
		int result = chargeMapper.insertCharge(chargeInfo);
		
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
			rollbackFor = {Exception.class})
	public int modifyCharge(ChargeDTO chargeInfo) {
		
		int result = chargeMapper.updateCharge(chargeInfo);
		
		return result;
	}
}
