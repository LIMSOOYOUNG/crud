package com.deft.crud.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.product.model.dto.AccountDTO;
import com.deft.crud.product.model.dto.ManufacturerDTO;
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
	
	
	/* 상품 전체 조회 */
	@GetMapping("/productList")
	public ModelAndView productList(ModelAndView mv) {
		
		List<ProductDTO> allProductList = productService.allProductList();
		
		mv.addObject("allProductList", allProductList);
		mv.setViewName("product/productList");
		
		return mv;
	}
	
	/* 카테고리 전체 조회 */
	@GetMapping("/categoryList")
	public ModelAndView categoryList(ModelAndView mv) {
		
		List<ProductCategoryDTO> allCategoryList = productService.allCategoryList();
		
		mv.addObject("allCategoryList", allCategoryList);
		mv.setViewName("product/categoryList");
		
		return mv;
		
	}
	
	/* 상품 상세 조회 */
	@GetMapping("/productDetail")
	public ModelAndView productDetail(ModelAndView mv,
									  @RequestParam("productNo") int productNo) {
		
		/* 해당 상품 번호를 인자값으로 넘겨 하나의 상품 정보 조회 */
		ProductDTO productDetail = productService.productDetail(productNo);
		
		/* 카테고리(중) 리스트 조회 */
		List<ProductCategoryDTO> refCategoryList = productService.refCategoryList();
		
		/* 카테고리(소) 리스트 조회 */
		List<ProductCategoryDTO> childrenCategoryList = productService.childrenCategoryList();
		
		/* 제조사 정보 조회 */
		List<ManufacturerDTO> manufacturerList = productService.manufacturerList();
		
		/* 거래처 정보 조회*/
		List<AccountDTO> accountList = productService.accountList();
		
		mv.addObject("productDetail", productDetail);
		mv.addObject("refCategoryList", refCategoryList);
		mv.addObject("childrenCategoryList", childrenCategoryList);
		mv.addObject("manufacturerList", manufacturerList);
		mv.addObject("accountList", accountList);

		mv.setViewName("product/productDetail");
		
		return mv;
	}
	
	/* 다중 셀렉트박스를 구현하기 위해 선택한 상위 카테고리의 번호를 인자값으로 넘겨 카테고리(소) 조회 */
	@GetMapping(value = "/selectCategoryCode", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductCategoryDTO>	findCategoryList(@RequestParam("categoryCode") int categoryCode) {
		
		return productService.findChildrenCategoryList(categoryCode);
		
	}
	
	
	/* 상품 업데이트 */
	@PostMapping("/updateProduct") 
	public ModelAndView upadateProduct(ModelAndView mv, @ModelAttribute ProductDTO parameters, RedirectAttributes rttr) {
		
		System.out.println("parameters : " + parameters);
		
		
		int result = productService.updateProduct(parameters);
		
		String message = "";
		
		if(result > 0) {
			message = "상품 수정에 성공하셨습니다!";
		} else {
			message = "상품 수정에 실패하셨습니다!";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/product/productList");
		return mv;
	}
	
	
	
	
	@GetMapping("/insertProduct")
	public void insertProductPage() {}
	
}
