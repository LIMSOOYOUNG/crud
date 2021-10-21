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
	
	/* 모든 상품 정보 조회 */
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
	
	/* 상품 등록 메소드*/
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
			rollbackFor = {Exception.class})
	public int insertProduct(InsertProductDTO parameters, Map<String, String> fileMap) {
		
		int result = 0;
		
		/* 상품 정보 등록할 떄 텍스트로 입력한 정보 저장 결과*/
		int productTextResult = productMapper.insertProduct(parameters);
		
		/* 상품 등록하면서 시퀀스번호 조회*/
		int productNo = productMapper.selectProductSeq();
		
		
		/* 맵으로 전달 받은 등록할 파일에 대한 정보를 dto에 담아준다. */
		ProductImageDTO productImage = new ProductImageDTO();
		productImage.setOriginalName(fileMap.get("originFileName"));
		productImage.setSavedName(fileMap.get("savedName"));
		productImage.setSavedPath(fileMap.get("savedPath"));
		productImage.setThumbnailPath(fileMap.get("thumbnailPath"));
		productImage.setProductNo(productNo);
		
		System.out.println("productImage : " + productImage);
		
		/* 이미지 저장 결과 */
		int productImageResult = productMapper.insertProductImage(productImage);
		
		/* 텍스트와 이미지 등록 결과값이 모두 1일 떄 result는 1을 반환한다.*/
		if(productTextResult > 0 && productImageResult > 0) {
			result = 1;
		}
		
		return result; 
	}
	
	/* 싱픔 이미지와 상품 정보 수정하는 메소드*/
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
			rollbackFor = {Exception.class})
	public int modifyProductWithImg(ProductDTO parameters, ProductImageDTO updateFile) {
		
		int result = 0;
		
		/* 상품 정보 수정할 떄 텍스트로 입력한 정보 수정 결과 */
		int modifyProductForText = productMapper.modifyProductForText(parameters);
		
		/* 상품 정보 수정할 떄 이미지 정보 수정 결과 */
		int modifyProductImage = productMapper.modifyProductImage(updateFile);
		
		if(modifyProductForText > 0 && modifyProductImage > 0) {
		
			result = 1;
		}
		
		return result;
	}
	
	/* 상품 정보 수정할 떄 텍스트로 입력한 정보 저장 결과 */
	@Transactional
	public int modifyProductForText(ProductDTO parameters) {

		return productMapper.modifyProductForText(parameters);
	}
	
	/* 카테고리(중) 등록 */
	@Transactional
	public int insertRefCategory(ProductCategoryDTO parameter) {

		return productMapper.insertRefCategory(parameter);
	}
	
	/* 카테고리(소) 등록 */
	@Transactional
	public int insertCategory(ProductCategoryDTO parameters) {

		return productMapper.insertCategory(parameters);
	}
	
	/* 카테고리(중) 업데이트 */
	@Transactional
	public int updateRefCategory(ProductCategoryDTO parameters) {

		return productMapper.updateRefCategory(parameters);
	}
	
	
	public ProductCategoryDTO selectOneCategory(int selectedCategory) {

		
		return productMapper.selectOneCategory(selectedCategory);
	}

	/* 카테고리(소) 업데이트*/
	public int updateCategory(ProductCategoryDTO parameters) {
		
		return productMapper.updateCategory(parameters);
	}




}
