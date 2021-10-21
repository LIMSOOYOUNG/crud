package com.deft.crud.product.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.product.model.dto.AccountDTO;
import com.deft.crud.product.model.dto.InsertProductDTO;
import com.deft.crud.product.model.dto.ManufacturerDTO;
import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.product.model.dto.ProductDTO;
import com.deft.crud.product.model.dto.ProductImageDTO;

@Mapper
public interface ProductMapper {

	List<ProductDTO> allProductList();

	List<ProductCategoryDTO> allCategoryList();

	ProductDTO productDetail(int productNo);

	ProductImageDTO selectProductImage(int productNo);

	List<ProductCategoryDTO> selectSmallCategoryList(int refCategoryCode);

	List<ProductCategoryDTO> refCategoryList();

	List<ProductCategoryDTO> categoryList();

	List<ManufacturerDTO> manufacturerList();

	List<AccountDTO> accountList();
	
	


	int insertRefCategory(ProductCategoryDTO parameter);

	int insertCategory(ProductCategoryDTO parameters);

	int updateRefCategory(ProductCategoryDTO parameters);
	
	
	/* 상품 등록 메소드 */
	int insertProduct(InsertProductDTO parameters);

	/* 상품번호 조회 */
	int selectProductSeq();

	/* 상품 이미지 등록 */
	int insertProductImage(ProductImageDTO productImage);
	
	/* 상품정보 텍스트로 입력한 정보 수정 */
	int modifyProductForText(ProductDTO parameters);
	
	/* 상품 이미지 정보 수정 결과 */
	int modifyProductImage(ProductImageDTO updateFile);
	
	
	ProductCategoryDTO selectOneCategory(int selectedCategory);
	
	/* 카테고리(소) 수정*/
	int updateCategory(ProductCategoryDTO parameters);

	
	

	


//	ProductCategoryDTO selectRefCategoryForUpdate(int refCategoryCode);

}
