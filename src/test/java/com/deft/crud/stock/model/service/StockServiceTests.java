package com.deft.crud.stock.model.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.deft.crud.configuration.CrudApplication;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class})
public class StockServiceTests {

	@Autowired
	private StockService stockService;
	
	/* 창고보유(재고)상품 목록 조회 테스트 */
	@Test
	@Disabled
	public void testSelectStockAll() {
		
//		List<StockDTO> stockList = stockService.selectStockAll();
//		
//		assertNotNull(stockList);
//			
//		for(StockDTO stock : stockList) {
//			System.out.println(stock);
//		}
	}
	
	
}


