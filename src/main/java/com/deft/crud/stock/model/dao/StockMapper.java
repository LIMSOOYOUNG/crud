package com.deft.crud.stock.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.stock.model.dto.ProductStockInfoDTO;
import com.deft.crud.stock.model.dto.RequestStockDTO;
import com.deft.crud.stock.model.dto.ResponseStockDTO;
import com.deft.crud.stock.model.dto.StorageDTO;
import com.deft.crud.stock.model.dto.approval.ApprovalDocumentDTO;

@Mapper
public interface StockMapper {


	List<StorageDTO> selectStorageAll();

	StorageDTO selectStockAll(int productNo);

	List<StorageDTO> selectSellableProductAll();

	ProductStockInfoDTO selectOneProductInfoByNo(int productNo);

	int modifyStockCondition(RequestStockDTO parameters);

	int insertStockReceivingProduct(List<RequestStockDTO> list);

	int insertApprovalDocument(List<RequestStockDTO> request, int empNo);

	void insertOrderProductInfo(int productNo, int orderAmount);

	void insertReceivingReq();

	void insertApprovalDocument(ApprovalDocumentDTO approvalDocument);


}
