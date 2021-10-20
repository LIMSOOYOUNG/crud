package com.deft.crud.product.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.deft.crud.product.model.dao.ProductMapper;
import com.deft.crud.product.model.dto.AccountDTO;
import com.deft.crud.product.model.dto.InsertProductDTO;
import com.deft.crud.product.model.dto.ManufacturerDTO;
import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.product.model.dto.ProductDTO;
import com.deft.crud.product.model.dto.ProductImageDTO;

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

	public List<ProductCategoryDTO> categoryList() {
		
		return productMapper.categoryList();
	}

	public List<ManufacturerDTO> manufacturerList() {

		return productMapper.manufacturerList();
	}
	
	public ProductDTO productDetail(int productNo) {

		return productMapper.productDetail(productNo);
	}

	/* 상품이미지 조회 */
	public ProductImageDTO selectProductImage(int productNo) {

		return productMapper.selectProductImage(productNo);
	}

	public List<ProductCategoryDTO> selectSmallCategoryList(int refCategoryCode) {
		
		return productMapper.selectSmallCategoryList(refCategoryCode);
	}


	public List<AccountDTO> accountList() {

		return productMapper.accountList();
	}

	@Transactional
	public int modifyProduct(ProductDTO parameters) {

		return productMapper.modifyProduct(parameters);
	}
	
	@Transactional
	public int insertRefCategory(ProductCategoryDTO parameter) {

		return productMapper.insertRefCategory(parameter);
	}

	@Transactional
	public int insertCategory(ProductCategoryDTO parameters) {

		return productMapper.insertCategory(parameters);
	}
	
	@Transactional
	public int updateRefCategory(ProductCategoryDTO parameters) {

		return productMapper.updateRefCategory(parameters);
	}
	
	/* 상품 등록 메소드*/
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
			rollbackFor = {Exception.class})
	public int insertProduct(InsertProductDTO parameters, Map<String, String> fileMap) {
		
		int result = 0;
		
		int productTextResult = productMapper.insertProduct(parameters);
		
		/* 상품 등록하면서 시퀀스번호 조회*/
		int productNo = productMapper.selectProductSeq();
		
		
		ProductImageDTO productImage = new ProductImageDTO();
		productImage.setOriginalName(fileMap.get("originFileName"));
		productImage.setSavedName(fileMap.get("savedName"));
		productImage.setSavedPath(fileMap.get("savedPath"));
		productImage.setThumbnailPath(fileMap.get("thumbnailPath"));
		productImage.setProductNo(productNo);
		
		System.out.println("productImage : " + productImage);
		
		int productImageResult = productMapper.insertProductImage(productImage);
		
		if(productTextResult > 0 && productImageResult > 0) {
			result = 1;
		}
		
		return result; 
	}

	public ProductCategoryDTO selectOneCategory(int selectedCategory) {

		
		return productMapper.selectOneCategory(selectedCategory);
	}

	public int updateCategory(ProductCategoryDTO parameters) {
		
		return productMapper.updateCategory(parameters);
	}



}
