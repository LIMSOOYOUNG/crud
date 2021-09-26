package com.deft.crud.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.product.model.dto.ProductDTO;
import com.deft.crud.product.model.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	
	@GetMapping("/productList")
	public ModelAndView productList(ModelAndView mv) {
		
		List<ProductDTO> allProductList = productService.allProductList();
		
		System.out.println(allProductList);
		
		mv.addObject("allProductList", allProductList);
		mv.setViewName("product/productList");
		
		return mv;
	}
	
	@GetMapping("/categoryList")
	public ModelAndView categoryList(ModelAndView mv) {
		
		List<ProductCategoryDTO> allCategoryList = productService.allCategoryList();
		
		System.out.println("allCategoryList : " + allCategoryList);
		
		mv.addObject("allCategoryList", allCategoryList);
		mv.setViewName("product/categoryList");
		
		return mv;
		
	}
	
	@GetMapping("/productDetail")
	public ModelAndView productDetail(ModelAndView mv,
									  @RequestParam("productNo") int productNo) {
		
		System.out.println("productNo : " + productNo);
		
		List<ProductDTO> productDetail = productService.productDetail(productNo);
		
		System.out.println("productDetail : " + productDetail);
		
		return mv;
	}
	
	
	@GetMapping("/insertProduct")
	public void insertProductPage() {}
	
}
