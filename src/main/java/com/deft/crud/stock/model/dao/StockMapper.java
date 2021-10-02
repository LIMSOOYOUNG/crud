package com.deft.crud.stock.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.stock.model.dto.ProductStockInfoDTO;
import com.deft.crud.stock.model.dto.StockDTO;
import com.deft.crud.stock.model.dto.StorageDTO;

@Mapper
public interface StockMapper {


	List<StorageDTO> selectStorageAll();

	StockDTO selectStockAll(int productNo);

	List<StockDTO> selectSellAbleProductAll();

	ProductStockInfoDTO selectOneProductInfoByNo(int productNo);


}
