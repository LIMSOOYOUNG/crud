package com.deft.crud.stock.model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.order.model.dao.OrderMapper;
import com.deft.crud.order.model.dto.OrderDTO;
import com.deft.crud.order.model.dto.OrderProductDTO;
import com.deft.crud.stock.model.dao.StockMapper;
import com.deft.crud.stock.model.dto.ProductStockInfoDTO;
import com.deft.crud.stock.model.dto.RequestStockDTO;
import com.deft.crud.stock.model.dto.StorageDTO;
import com.deft.crud.stock.model.dto.approval.ApprovalDocumentDTO;
import com.deft.crud.stock.model.dto.approval.ApprovalModifyDTO;
import com.deft.crud.stock.model.dto.approval.ReceivingReqDTO;

@Service
public class StockService {

	private StockMapper stockMapper;
	private final OrderMapper orderMapper;
	
	@Autowired
	public StockService(StockMapper stockMapper, OrderMapper orderMapper) {
		this.stockMapper = stockMapper;
		this.orderMapper = orderMapper;
	}

	/* 창고에 보유중인 전체 상품 목록*/
	@Transactional
	public List<StorageDTO> selectStockAll() {

		List<StorageDTO> storageList = stockMapper.selectStorageAll();

		StorageDTO stockDTO = new StorageDTO();	
		List<StorageDTO> stockList = new ArrayList<>();

		for(StorageDTO storage: storageList) {
			stockDTO = stockMapper.selectStockAll(storage.getProductNo());	//창고에 보유중인 상품의 상품번호로 재고상품 정보 조회

			stockList.add(stockDTO);									//ModelAndView에 담아 view페이지로 넘겨줄 재고상품정보 리스트에 위에서 조회한 상품들을 for문을 사용해 넣어줌 
		}

		return stockList;
	}

	/* 판매가능 상태인 모든 상품들*/
	@Transactional
	public List<StorageDTO> selectSellableProductAll() {

		List<StorageDTO> sellableProductList = stockMapper.selectSellableProductAll();

		return sellableProductList;
	}

	/* 선택한 상품의 재고 정보 조회*/
	@Transactional
	public ProductStockInfoDTO selectOneProductInfoByNo(int productNo) {

		ProductStockInfoDTO productStockInfo = stockMapper.selectOneProductInfoByNo(productNo);

		return productStockInfo;
	}

	/* 재고관리 상품 재고상태 변경 요청*/
	@Transactional
	public boolean modifyStockCondition(RequestStockDTO parameters) {

		int modifyResult = stockMapper.modifyStockCondition(parameters);

		return modifyResult > 0? true: false;
	}

	/* 요청 상품 목록 DB에 insert */
	@Transactional
	public boolean insertStockReceivingProduct(List<RequestStockDTO> request, UserImpl userInfo) {
		ApprovalDocumentDTO approvalDocument = new ApprovalDocumentDTO();

		boolean result = true;

		int empNo = userInfo.getEmpNo();
		int managerNo = userInfo.getManagerNo();

		for(RequestStockDTO requestOrder : request) {
			approvalDocument.setApprovalDocumentName(requestOrder.getOrderTitle());
			approvalDocument.setReqReason(requestOrder.getOrderContent());
		}
		approvalDocument.setEmpNo(empNo);
		approvalDocument.setManagerNo(managerNo);

		if(true) {	
			//결재문서정보 생성
			if(!stockMapper.insertApprovalDocument(approvalDocument)) {
				result = false;
			}
			//입고요청번호와 결재번호 
			if(!stockMapper.insertReceivingReq()) {
				result = false;
			}
			//재고 요청수량 추가
			if(!stockMapper.insertStockReceivingProduct(request)) {
				result = false;
			}
			//입고요청번호에 대한 결재 이력생성
			if(!stockMapper.insertReleaseReqHistory()) {
				result = false;
			}
		}

		return result;
	}

	/* 요청목록 전체 조회 */
	@Transactional
	public List<ReceivingReqDTO> selectReceivingReqAll(UserImpl userInfo) {

		List<ReceivingReqDTO> receivingReqList = stockMapper.selectReceivingReqAll(userInfo);

		return receivingReqList;
	}

	/* 요청서 목록 결재상태 별 조회 */
	public List<ReceivingReqDTO> selectReceivingReqByStatus(String documentStatus, UserImpl userInfo) {

		List<ReceivingReqDTO> receivingReqList = stockMapper.selectReceivingReqByStatus(documentStatus, userInfo);
		
		return receivingReqList;
	}
	
	
	/* 선택한 요청문서 상세조회 */
	@Transactional
	public ReceivingReqDTO selectReceivingReqByNo(int reqNo) {

		ReceivingReqDTO receivingReqInfo = stockMapper.selectReceivingReqByNo(reqNo);

		return receivingReqInfo;
	}


	/* 선택한 요청문서 상세정보중 요청상품 목록 */
	@Transactional
	public List<ReceivingReqDTO> selectReceivingReqProductByReqNo(int reqNo) {

		List<ReceivingReqDTO> receivingReqProductList = stockMapper.selectReceivingReqProductByReqNo(reqNo);

		return receivingReqProductList;
	}

	/* 결재문서 결재상태 변경 후 결재 처리 and 결재 이력 생성 */
	@Transactional
	public boolean modifyApprovalStatus(ApprovalModifyDTO parameters) {

		LocalDateTime sysDateLocalDateTime = LocalDateTime.now();

		String sysDate= sysDateLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

		parameters.setDocumentProcessDate(sysDate);

		int result = 0;

		int modifyResult = stockMapper.modifyApprovalStatus(parameters);			// 결재상태 변경(결재 처리)

		if(modifyResult > 0 ) {

			int hisResult = stockMapper.insertReceivingReqHistory(parameters);	//결재이력 INSERT 

			if(hisResult > 0) {

				result = 1;
			}
		}

		return result > 0? true: false;
	}

	/* 미완료 상태 주문서 목록 조회 */
	public List<OrderDTO> selectPurchaseOrderAll(int empNo) {

		List<OrderDTO> purchaseOrderList = orderMapper.selectOrderList(empNo);
		
		return purchaseOrderList;
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
