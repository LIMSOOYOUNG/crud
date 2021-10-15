package com.deft.crud.product.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.product.model.dto.AccountDTO;
import com.deft.crud.product.model.dto.InsertProductDTO;
import com.deft.crud.product.model.dto.ManufacturerDTO;
import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.product.model.dto.ProductDTO;

@Mapper
public interface ProductMapper {

	List<ProductDTO> allProductList();

	List<ProductCategoryDTO> allCategoryList();

	ProductDTO productDetail(int productNo);

	List<ProductCategoryDTO> selectSmallCategoryList(int refCategoryCode);

	List<ProductCategoryDTO> refCategoryList();

	List<ProductCategoryDTO> categoryList();

	List<ManufacturerDTO> manufacturerList();

	List<AccountDTO> accountList();

	int modifyProduct(ProductDTO parameters);

	int insertRefCategory(ProductCategoryDTO parameter);

	int insertCategory(ProductCategoryDTO parameters);

	int updateRefCategory(ProductCategoryDTO parameters);
	
	/* 상품 등록 메소드 */
	int insertProduct(InsertProductDTO parameters);

	ProductCategoryDTO selectOneCategory(int selectedCategory);

//	ProductCategoryDTO selectRefCategoryForUpdate(int refCategoryCode);

}
