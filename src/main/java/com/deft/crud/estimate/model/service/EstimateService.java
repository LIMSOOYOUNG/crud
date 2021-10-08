package com.deft.crud.estimate.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deft.crud.estimate.model.dao.EstimateMapper;
import com.deft.crud.estimate.model.dto.EstimateDTO;
import com.deft.crud.estimate.model.dto.EstimateProductDTO;

@Service
public class EstimateService {

	private final EstimateMapper estimateMapper;
	
	@Autowired
	public EstimateService(EstimateMapper estimateMapper) {
		
		this.estimateMapper = estimateMapper;
	}
	
	public List<EstimateDTO> selectEstimateList() {
		
		return estimateMapper.selectEstimateList();
	}

	public List<EstimateDTO> selectEstimateListByStatus(String estimateStatus) {
		
		return estimateMapper.selectEstimateListByStatus(estimateStatus);
	}
	
	public EstimateDTO selectEstimateDetail(String estimateNo) {
		
		EstimateDTO estimate = estimateMapper.selectEstimateDetail(estimateNo);
		
		int discountRate = estimate.getDiscountRate();
		int total = 0;
		
		/* 할인 적용 여부 확인 */
		if(discountRate > 0) {
			estimate.setDiscountStatus("Y");
		} else {
			estimate.setDiscountStatus("N");			
		}
		
		/* 할인 적용한 합계 금액 계산 */
		for(EstimateProductDTO estimateProduct : estimate.getEstimateProductList()) {
			int sellingPrice = estimateProduct.getProduct().getSellingPrice();
			int amount = estimateProduct.getProductAmount();
			
			int discountedPrice = sellingPrice * (1 - (discountRate / 100));
			int subtotal = discountedPrice * amount;
			int tax = subtotal / 10;
			
			estimateProduct.getProduct().setDiscountedPrice(discountedPrice);
			estimateProduct.getProduct().setSubtotal(subtotal);
			estimateProduct.getProduct().setTax(tax);
			
			total += subtotal + tax;
		}
		
		estimate.setTotal(total);
		
		return estimate;
	}

	public String selectEstimateNo(String newEstimateDate) {
		
		String newEstimateSeq = estimateMapper.selectLastEstimateSeq();
		String newEstimateNo = "E" + newEstimateDate.replace("-", "") + "-" + newEstimateSeq;
		
		return newEstimateNo;
	}

	@Transactional
	public int insertEstimate(EstimateDTO estimateInfo) {
		
		return estimateMapper.insertEstimate(estimateInfo);
	}
}
