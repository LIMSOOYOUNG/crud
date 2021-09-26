package com.deft.crud.product.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deft.crud.product.model.dao.ProductMapper;
import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.product.model.dto.ProductDTO;

@Service
public class ProductService {

	private ProductMapper productMapper;
	
	@Autowired
	public ProductService(ProductMapper productMapper) {
		
		this.productMapper = productMapper;
	}
	
	
	public List<ProductDTO> allProductList() {

		return productMapper.allProductList();
	}


	public List<ProductCategoryDTO> allCategoryList() {

		return productMapper.allCategoryList();
	}


	public List<ProductDTO> productDetail(int productNo) {

		return productMapper.productDetail(productNo);
	}

}
