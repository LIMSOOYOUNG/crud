package com.deft.crud.stock.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.stock.model.dto.ProductStockInfoDTO;
import com.deft.crud.stock.model.dto.RequestStockDTO;
import com.deft.crud.stock.model.dto.ResponseStockDTO;
import com.deft.crud.stock.model.dto.StorageDTO;
import com.deft.crud.stock.model.dto.approval.ApprovalDocumentDTO;
import com.deft.crud.stock.model.dto.approval.ReceivingReqDTO;
import com.deft.crud.stock.model.dto.approval.ReceivingReqProductDTO;

@Mapper
public interface StockMapper {


	List<StorageDTO> selectStorageAll();

	StorageDTO selectStockAll(int productNo);

	List<StorageDTO> selectSellAbleProductAll();

	ProductStockInfoDTO selectOneProductInfoByNo(int productNo);

	int modifyStockCondition(RequestStockDTO parameters);

	boolean insertStockReceivingProduct(List<RequestStockDTO> list);

	int insertApprovalDocument(List<RequestStockDTO> request, int empNo);

	void insertOrderProductInfo(int productNo, int orderAmount);

	boolean insertReceivingReq();

	boolean insertApprovalDocument(ApprovalDocumentDTO approvalDocument);

	boolean insertReleaseReqHistory();

	List<ReceivingReqDTO> selectReceivingReqAll();

	ReceivingReqDTO selectReceivingReqByNo(int reqNo);

	List<ReceivingReqDTO> receivingReqProductByReqNo(int reqNo);


}
