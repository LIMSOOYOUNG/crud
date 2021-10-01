package com.deft.crud.product.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deft.crud.product.model.dao.ProductMapper;
import com.deft.crud.product.model.dto.AccountDTO;
import com.deft.crud.product.model.dto.ManufacturerDTO;
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

	public List<ProductCategoryDTO> refCategoryList() {
		
		return productMapper.refCategoryList();
	} 

	public List<ProductCategoryDTO> childrenCategoryList() {
		
		return productMapper.childrenCategoryList();
	}

	public List<ManufacturerDTO> manufacturerList() {

		return productMapper.manufacturerList();
	}

	public ProductDTO productDetail(int productNo) {

		return productMapper.productDetail(productNo);
	}


	public List<ProductCategoryDTO> findChildrenCategoryList(int categoryCode) {
		
		return productMapper.findChildrenCategoryList(categoryCode);
	}


	public List<AccountDTO> accountList() {

		return productMapper.accountList();
	}

	@Transactional
	public int updateProduct(ProductDTO parameters) {

		return productMapper.updateProduct(parameters);
	}

	
	@Transactional
	public int insertRefCategory(ProductCategoryDTO parameter) {

		return productMapper.insertRefCategory(parameter);
	}


	public int insertCategory(ProductCategoryDTO parameters) {

		return productMapper.insertCategory(parameters);
	}








}
