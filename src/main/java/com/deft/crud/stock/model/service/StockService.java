package com.deft.crud.stock.model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.order.model.dao.OrderMapper;
import com.deft.crud.order.model.dto.OrderDTO;
import com.deft.crud.order.model.dto.OrderProductDTO;
import com.deft.crud.stock.model.dao.StockMapper;
import com.deft.crud.stock.model.dto.ProductStockInfoDTO;
import com.deft.crud.stock.model.dto.RequestReleaseDTO;
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
			if(!stockMapper.insertStockApprovalDocument(approvalDocument)) {
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
			if(!stockMapper.insertReceivingReqHistory()) {
				result = false;
			}
		}

		return result;
	}
	
	/* 출고요청서 등록 */
	public boolean insertReleaseOrder(List<RequestReleaseDTO> request, UserImpl userInfo) {
		ApprovalDocumentDTO approvalDocument = new ApprovalDocumentDTO();
		
		boolean result = true;
		
		int empNo = userInfo.getEmpNo();
		int managerNo = userInfo.getManagerNo();
		String orderNo = "";
		
		for(RequestReleaseDTO requestOrder : request) {
			approvalDocument.setApprovalDocumentName(requestOrder.getOrderTitle());
			approvalDocument.setReqReason(requestOrder.getOrderReason());
			orderNo = requestOrder.getOrderNo();
		}
		
		System.out.println("@@@주문서번호 확인 : " + orderNo);
		
		approvalDocument.setEmpNo(empNo);
		approvalDocument.setManagerNo(managerNo);
		
		if(true) {	
			//결재문서정보 생성
			if(!stockMapper.insertReleaseApprovalDocument(approvalDocument)) {
				result = false;
			}
			//출고요청번호와 결재번호 + 주문서 번호
			if(!stockMapper.insertReleaseReq(orderNo)) {
				result = false;
			}
			
			//출고요청번호에 대한 결재 이력생성
			if(!stockMapper.insertReleaseReqHistory()) {
				result = false;
			}
		}
		return false;
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
	
	/* 입고요청서 번호 조회 */
	public int findReceivingReqNo(int approvalNo) {
		int receivingNo = stockMapper.selectReceivingNoByApprovalNo(approvalNo);
		System.out.println("입고요청번호 : " + receivingNo);
		
		return receivingNo;
	}
	
	/* 선택한 입고요청서 상세조회 */
	@Transactional
	public ReceivingReqDTO selectReceivingReqByNo(int approvalNo) {

		/* 입고요청서 상세정보 조회 */
		ReceivingReqDTO receivingInfo = stockMapper.selectReceivingReqByNo(approvalNo);
		
		System.out.println("@@@@" + receivingInfo);
		
		return receivingInfo;
	}

	/* 선택한 요청문서 상세정보중 요청상품 목록 */
	@Transactional
	public List<ReceivingReqDTO> selectReceivingReqProductByReqNo(int reqNo) {

		List<ReceivingReqDTO> receivingReqProductList = stockMapper.selectReceivingReqProductByReqNo(reqNo);

		System.out.println(receivingReqProductList);
		
		return receivingReqProductList;
	}

	/* 입고요청서 결재상태 변경 후 결재 처리 and 결재 이력 생성 */
	@Transactional
	public boolean modifyApprovalStatus(ApprovalModifyDTO parameters) {

		LocalDateTime sysDateLocalDateTime = LocalDateTime.now();

		String sysDate= sysDateLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
		
		System.out.println("@@@" + sysDate);

		parameters.setDocumentProcessDate(sysDate);

		int result = 0;

		int modifyResult = stockMapper.modifyApprovalStatus(parameters);			// 결재상태 변경(결재 처리)

		System.out.println("결재상태 변경 성공 여부 : " + modifyResult);
		
		if(modifyResult > 0 ) {

			int hisResult = stockMapper.insertReceivingReqHistoryParameter(parameters);	//결재이력 INSERT 

			if(hisResult > 0) {

				result = 1;
			}
		}

		return result > 0? true: false;
	}
	
	/* 출고요청서 결재상태 변경 후 결재 처리 and 결재 이력 생성 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
					rollbackFor = {Exception.class})
	public boolean modifyReleaseStatus(ReceivingReqDTO parameters) {

		LocalDateTime sysDateLocalDateTime = LocalDateTime.now();

		String sysDate= sysDateLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

		parameters.getApprovalDocumentDTO().setDocumentProcessDate(sysDate);

		int result = 0;

		int modifyResult = stockMapper.modifyReleaseStatus(parameters);			// 결재상태 변경(결재 처리)
		
		String approvalStatus = parameters.getApprovalDocumentDTO().getDocumentStatus();
		
		RequestStockDTO requestStock = new RequestStockDTO();
		if(modifyResult > 0 && approvalStatus.equals("승인")) {

			for(int i = 0; i < parameters.getReceivingReqProductList().size(); i++) {
				requestStock.setStockProductNo(parameters.getReceivingReqProductList().get(i).getProductNo());
				requestStock.setProductStock(parameters.getReceivingReqProductList().get(i).getProductStock());
				requestStock.setOrderStockAmount(parameters.getReceivingReqProductList().get(i).getProductAmount());
				
				stockMapper.updateStorage(requestStock);		// 출고 요청수량 만큼 창고재고수량 감소
			}			
			
			int hisResult = stockMapper.insertReleaseReqHistoryParameter(parameters);	//결재이력 INSERT 

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

	/* 출고요청서 상세정보 조회 */
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
		
		String deliveryPlace =  order.getDeliveryPlace().replace("$", " ");
		
		order.setDeliveryPlace(deliveryPlace);
		order.setSumPrice(total);
		
		return order;
	}

	/* 출고요청서 기본 정보조회 */
	public ReceivingReqDTO selectReleaseInfo(int approvalNo) {
	
		ReceivingReqDTO releaseInfo = stockMapper.selectReleaseReqByNo(approvalNo);
		
		return releaseInfo;
	}

	/* 결재문서번호로 출고요청서 번호 조회 */
	public int selectReleaseNoByApprovalNo(int approvalNo) {

		int releaseNo = stockMapper.selectReleaseNoByApprovalNo(approvalNo);
		
		return releaseNo;
	}

	
	
	

}
