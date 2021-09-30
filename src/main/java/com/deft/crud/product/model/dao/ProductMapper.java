package com.deft.crud.product.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.product.model.dto.AccountDTO;
import com.deft.crud.product.model.dto.ManufacturerDTO;
import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.product.model.dto.ProductDTO;

@Mapper
public interface ProductMapper {

	List<ProductDTO> allProductList();

	List<ProductCategoryDTO> allCategoryList();

	ProductDTO productDetail(int productNo);

	List<ProductCategoryDTO> findChildrenCategoryList(int categoryCode);

	List<ProductCategoryDTO> refCategoryList();

	List<ProductCategoryDTO> childrenCategoryList();

	List<ManufacturerDTO> manufacturerList();

	List<AccountDTO> accountList();

	int updateProduct(ProductDTO parameters);

}
