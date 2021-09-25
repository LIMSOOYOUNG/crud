package com.deft.crud.product.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.product.model.dto.ProductDTO;

@Mapper
public interface ProductMapper {

	List<ProductDTO> allProductList();

}
