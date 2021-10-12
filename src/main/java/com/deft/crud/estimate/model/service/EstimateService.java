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
	
	public List<EstimateDTO> selectEstimateList(int empNo) {
		
		return estimateMapper.selectEstimateList(empNo);
	}

	public List<EstimateDTO> selectEstimateListByStatus(String estimateStatus, int empNo) {
		
		return estimateMapper.selectEstimateListByStatus(estimateStatus, empNo);
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
			
			int discountedPrice = (int) (sellingPrice * (1 - (discountRate * 0.01)));
			int subtotal = discountedPrice * amount;
			int tax = (int) (subtotal * 0.1);
			
			estimateProduct.getProduct().setDiscountedPrice(discountedPrice);
			estimateProduct.getProduct().setSubtotal(subtotal);
			estimateProduct.getProduct().setTax(tax);
			
			total += subtotal + tax;
		}
		
		estimate.setEstimateTotal(total);
		
		return estimate;
	}

	public String selectEstimateNo(String newEstimateDate) {
		
		String newEstimateSeq = estimateMapper.selectLastEstimateSeq();
		String newEstimateNo = "E" + newEstimateDate.replace("-", "") + "-" + newEstimateSeq;
		
		return newEstimateNo;
	}

	@Transactional
	public boolean insertEstimate(EstimateDTO estimateInfo) {
		
		int result1 = estimateMapper.insertEstimate(estimateInfo);
		int result2 = 0;
		
		List<EstimateProductDTO> productList = estimateInfo.getEstimateProductList();
		
		for(EstimateProductDTO product : productList) {
			product.setEstimateNo(estimateInfo.getEstimateNo());
			
			result2 = estimateMapper.insertEstimateProduct(product);
		}
		
		boolean result = false; 
		
		if(result1 + result2 > 0) {
			result = true;
		}
		
		return result;
	}
}
