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
	
	/* 상품 정보 조회 */
	List<ProductDTO> allProductList();
	
	/* 모든 카테고리 조회 */
	List<ProductCategoryDTO> allCategoryList();
	
	/* 상품 상세 정보 */
	ProductDTO productDetail(int productNo);
	
	/* 상품 이미지 조회 */
	ProductImageDTO selectProductImage(int productNo);
	
	/* 다중 카테고리 선택 위해 상위카테고리 선택시 하위카테고리 조회  */
	List<ProductCategoryDTO> selectSmallCategoryList(int refCategoryCode);
	
	/* 상위 카테고리 조회 */
	List<ProductCategoryDTO> refCategoryList();
	
	/* 하위카테고리 조회 */
	List<ProductCategoryDTO> categoryList();
	
	/* 제조사 정보 조회 */
	List<ManufacturerDTO> manufacturerList();

	/* 거래처 정보 조회 */
	List<AccountDTO> accountList();
	

	/* 상위 카테고리 등록 */
	int insertRefCategory(ProductCategoryDTO parameter);
	
	/* 하위 카테고리 등록 */
	int insertCategory(ProductCategoryDTO parameters);
	
	/* 상위 카테고리 수정 */
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
	
	/* 하위 카테고리 수정할 떄 상위카테고리 번호로 하위 카테고리 정보 조회*/
	ProductCategoryDTO selectOneCategory(int selectedCategory);
	
	/* 하위 카테고리 수정*/
	int updateCategory(ProductCategoryDTO parameters);


}
