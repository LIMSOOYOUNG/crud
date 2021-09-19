package com.deft.crud.stock.model.service;

import java.util.List;

import com.deft.crud.stock.model.dto.StockDTO;

public interface StockService {

	List<StockDTO> stockSelectAll();

}
