package com.deft.crud.stock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.stock.model.dto.StockDTO;
import com.deft.crud.stock.model.dto.StorageDTO;
import com.deft.crud.stock.model.service.StockService;
public class StockController {

	private final StockService stockService;
	private final StorageDTO storageDTO;
	private final StockDTO stockDTO;
	
	@Autowired
	public StockController(StockService stockService, StorageDTO storageDTO, StockDTO stockDTO) {
		this.stockService = stockService;
		this.storageDTO = storageDTO;
		this.stockDTO = stockDTO;
	}

	@GetMapping("/selectAll")
	public ModelAndView selectStockAll(ModelAndView mv) {
		
		/* 창고 내 보유 상품목록*/
		List<StorageDTO> storageList = new ArrayList<>();
		
		storageList = stockService.storageSelectAll();

		
		List<StockDTO> productList = new ArrayList<>();
		
		/* 창고에 보유중인 재고상품 정보 */
		List<StockDTO> stockList = new ArrayList<>();
		
		for(StorageDTO storage: storageList) {
			
			productList = stockService.prodcutSelectByNo(storage.getProductNo());
			
			for(StockDTO product: productList) {
				if (storage.getProductNo() == product.getProductNo()) {
					stockDTO.setStorageSection(storage.getStorageSection());
					stockDTO.setStorageSpace(storage.getStorageSpace());
					stockDTO.setProductStock(storage.getProductStock());
					
					stockDTO.setCategoryCode(product.getCategoryCode());
					stockDTO.setProductName(product.getProductName());
					stockDTO.setProductNo(product.getProductNo());
					stockDTO.setSellStatus(product.getSellStatus());
					stockDTO.setUnit(product.getUnit());
				}
				
				stockList.add(stockDTO);
			}
			
			stockDTO.setProductNo(storage.getProductNo());
		}
		
		
		
		mv.addObject("stockList", storageList);
		mv.setViewName("/stock/datatables");
		
		return mv;
	}
	
	
}
