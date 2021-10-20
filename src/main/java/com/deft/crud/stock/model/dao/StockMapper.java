package com.deft.crud.stock.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.order.model.dto.OrderDTO;
import com.deft.crud.stock.model.dto.ProductStockInfoDTO;
import com.deft.crud.stock.model.dto.RequestStockDTO;
import com.deft.crud.stock.model.dto.StorageDTO;
import com.deft.crud.stock.model.dto.approval.ApprovalDocumentDTO;
import com.deft.crud.stock.model.dto.approval.ApprovalModifyDTO;
import com.deft.crud.stock.model.dto.approval.ReceivingReqDTO;
import com.deft.crud.stock.model.dto.approval.ReleaseReqDTO;

@Mapper
public interface StockMapper {


	List<StorageDTO> selectStorageAll();									// 창고보유(재고) 상품목록 조회 

	StorageDTO selectStockAll(int productNo);								// 창고에 보유중인 전체 상품 목록

	List<StorageDTO> selectSellableProductAll();							// 판매가능 상태인 모든 상품들

	ProductStockInfoDTO selectOneProductInfoByNo(int productNo);			// 선택한 상품의 재고관련 정보 조회

	int modifyStockCondition(RequestStockDTO parameters);					// 선택한 재고상품의 재고관련 정보 변경

	boolean insertStockApprovalDocument(ApprovalDocumentDTO approvalDocument); 	// 입고요청서 작성(입고 요청한 상품들의 정보 insert

	boolean insertReleaseApprovalDocument(ApprovalDocumentDTO approvalDocument); // 출고요청서 작성(출고요청 기본정보 insert)

	int insertApprovalDocument(List<RequestStockDTO> request, int empNo);	// 결재문서정보 생성

	boolean insertReceivingReq();											// 입고요청번호와 결재번호 

	boolean insertReleaseReq(String orderNo);								// 출고요청번호와 결재번호

	boolean insertStockReceivingProduct(List<RequestStockDTO> list);		// 재고 요청수량 추가

	boolean insertReceivingReqHistory();									// 입고요청번호에 대한 결재 이력생성

	boolean insertReleaseReqHistory();										// 출고요청번호에 대한 결재 이력생성

	List<ReceivingReqDTO> selectReceivingReqAll(UserImpl userInfo); 		// 요청목록 전체 조회(담당자 or 사원)
	
	ReceivingReqDTO selectReceivingReqByNo(int approvalNo);						// 선택한 요청문서 상세조회

	List<ReceivingReqDTO> selectReceivingReqProductByReqNo(int reqNo);		// 선택한 요청문서 상세정보중 요청상품 목록

	int modifyApprovalStatus(ApprovalModifyDTO parameters);					// 결재문서 결재상태 변경 후 결재 처리

	int insertReceivingReqHistoryParameter(ApprovalModifyDTO parameters);			// 결재이력 생성 

	List<ReceivingReqDTO> selectReceivingReqByStatus(String documentStatus  // 요청목록 결재상태 별 조회(담당자 or 사원)
													, UserImpl userInfo);

	List<OrderDTO> selectPurchaseOrderAll(int empNo);  						// 미완료 상태 주문서 목록조회

	ReleaseReqDTO selectorderNoByApprovalNo(int approvalNo);						// 결재문서 번호로 주문서 번호 찾기

	int selectReceivingNoByApprovalNo(int approvalNo);						// 결재문서 번호로 입고요청번호 찾기

	ReceivingReqDTO selectReleaseReqByNo(int approvalNo);







}
