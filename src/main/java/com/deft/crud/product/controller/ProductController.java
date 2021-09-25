package com.deft.crud.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	
}
