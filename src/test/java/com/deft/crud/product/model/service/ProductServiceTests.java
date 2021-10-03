package com.deft.crud.product.model.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.deft.crud.configuration.CrudApplication;
import com.deft.crud.product.model.dto.ProductDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class})
public class ProductServiceTests {
	
	@Autowired
	private ProductService productService;
	
	/* 상품 전체 조회 테스트 코드 */
	@Test
	public void productList() {
		
		List<ProductDTO> allProductList = productService.allProductList();
		
		assertNotNull(allProductList);
		
		for(ProductDTO product : allProductList) {
			System.out.println(product);
		}
		
	}
	
	
}
