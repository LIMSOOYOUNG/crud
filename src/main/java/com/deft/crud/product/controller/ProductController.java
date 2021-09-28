package com.deft.crud.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		
		List<ProductDTO> allProductList = productService.allProductList();
		
		ProductDTO productDetail = productService.productDetail(productNo);
		
		System.out.println("productDetail : " + productDetail);
		
		mv.addObject("productDetail", productDetail);
		mv.addObject("allProductList", allProductList);
		mv.setViewName("product/productDetail");
		
		return mv;
	}
	
	@PostMapping("/updateProduct") 
	public ModelAndView upadateProduct(ModelAndView mv,
									 	  @RequestParam("refCategoryName") String refCategoryName
									 	, @RequestParam("categoryName")	String categoryName
										, @RequestParam("companyName") String companyName
										, @RequestParam("productNo") int productNo 
										, @RequestParam("productName") String productName
										, @RequestParam("purchasePrice") int purchasePrice
										, @RequestParam("sellingPrice") int sellingPrice
										, @RequestParam("sellStatus") String sellStatus) {
		
		System.out.println("=======");
		System.out.println("=======");
		System.out.println("=======");
		System.out.println("=======");
		
		System.out.println(refCategoryName);
		System.out.println(categoryName);
		System.out.println(productNo);
		System.out.println(productName);
		System.out.println(purchasePrice);
		System.out.println(sellingPrice);
		System.out.println(sellStatus);
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		return null;
	}
	
	
	@GetMapping("/insertProduct")
	public void insertProductPage() {}
	
}
