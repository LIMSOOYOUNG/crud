package com.deft.crud.product.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.product.model.dto.AccountDTO;
import com.deft.crud.product.model.dto.InsertProductDTO;
import com.deft.crud.product.model.dto.ManufacturerDTO;
import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.product.model.dto.ProductDTO;
import com.deft.crud.product.model.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public ProductController(ProductService productService, ObjectMapper objectMapper) {
		this.productService = productService;
		this.objectMapper = objectMapper;
	}
	
	
	/* 상품 전체 조회 */
	@GetMapping("/selectAll")
	public ModelAndView productList(ModelAndView mv) {
		
		List<ProductDTO> allProductList = productService.allProductList();
		
		
		mv.addObject("allProductList", allProductList);
		mv.setViewName("product/productList");
		return mv;
	}
	
	
	/* 상품 상세 조회 */
	@GetMapping("/select")
	public ModelAndView productDetail(ModelAndView mv,
									  @RequestParam("productNo") int productNo) {
		
		/* 해당 상품 번호를 인자값으로 넘겨 하나의 상품 정보 조회 */
		ProductDTO productDetail = productService.productDetail(productNo);
		
		/* 카테고리(중) 리스트 조회 */
		List<ProductCategoryDTO> refCategoryList = productService.refCategoryList();
		
		/* 카테고리(소) 리스트 조회 */
		List<ProductCategoryDTO> categoryList = productService.categoryList();
		
		/* 제조사 정보 조회 */
		List<ManufacturerDTO> manufacturerList = productService.manufacturerList();
		
		/* 거래처 정보 조회*/
		List<AccountDTO> accountList = productService.accountList();
		
		mv.addObject("productDetail", productDetail);
		mv.addObject("refCategoryList", refCategoryList);
		mv.addObject("categoryList", categoryList);
		mv.addObject("manufacturerList", manufacturerList);
		mv.addObject("accountList", accountList);

		mv.setViewName("product/productDetail");
		
		return mv;
	}
	
	/* 다중 셀렉트박스를 구현하기 위해 선택한 상위 카테고리의 번호를 인자값으로 넘겨 카테고리(소) 조회 */
	@GetMapping(value = "/selectCategoryCode", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductCategoryDTO>	selectCategoryList(@RequestParam("refCategoryCode") int refCategoryCode) {
		
		return productService.selectSmallCategoryList(refCategoryCode);
		
	}
	
	
	/* 상품 업데이트 */
	@PostMapping("/modify") 
	public ModelAndView modifyProduct(ModelAndView mv, @ModelAttribute ProductDTO parameters, 
			RedirectAttributes rttr) {
		
		System.out.println("parameters : " + parameters);
		
		
		int result = productService.modifyProduct(parameters);
		
		String message = "";
		
		if(result > 0) {
			message = "상품수정에 성공하셨습니다!";
		} else {
			message = "상품수정에 실패하셨습니다!";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/product/selectAll");
		return mv;
	}
	
	
	/* 상품 등록을 위해 필요한 데이터를 가지고 와 조회를 한다. */
	@GetMapping("/insert")
	public ModelAndView insertProductPage(ModelAndView mv) {
		
		/* 카테고리(중) 리스트 조회*/
		List<ProductCategoryDTO> refCategoryList = productService.refCategoryList();
		

		/* 카테고리(소) 리스트 조회 */
		List<ProductCategoryDTO> categoryList = productService.categoryList();
		
		/* 제조사 정보 조회 */
		List<ManufacturerDTO> manufacturerList = productService.manufacturerList();
		
		/* 거래처 정보 조회*/
		List<AccountDTO> accountList = productService.accountList();
		
		mv.addObject("categoryList", categoryList);
		mv.addObject("refCategoryList", refCategoryList);
		mv.addObject("manufacturerList", manufacturerList);
		mv.addObject("accountList", accountList);
		mv.setViewName("product/insertProduct");
		
		return mv;
	}
	
	/* 상품 등록 */
	@PostMapping("/insert")
	public ModelAndView insertProduct(ModelAndView mv, InsertProductDTO parameters
			, RedirectAttributes rttr) {
		
		System.out.println("parameters : " + parameters );
		
		int result = productService.insertProduct(parameters);
		
		String message = "";
		
		if(result > 0) {
			message = "상품 등록에 성공하셨습니다.";
		} else {
			message = "상품 등록에 실패하셨습니다.";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/product/selectAll");
		return mv;
	}
	
	@PostMapping("/thumbnail/insert")
	public ModelAndView insertProductThumbNail(ModelAndView mv, @RequestParam MultipartFile productThumbNail,
			HttpServletRequest request, RedirectAttributes rttr) {
		
		
		System.out.println("$%$%$%$%$%$%$%");
		System.out.println("$%$%$%$%$%$%$%");
		System.out.println("$%$%$%$%$%$%$%");
		System.out.println("productThumbNail : " + productThumbNail);
		
		String root = request.getSession().getServletContext().getRealPath("");
		
		System.out.println("root : " + root);
		
//		String fileUploadDirectory = root + "\\upload\\original";
//		String thumbnailDirectory = root + "\\upload\\thumbnail";
//		
//		
//		File directory = new File(fileUploadDirectory);
//		File directory2 = new File(thumbnailDirectory);
//		
//		if(!directory.exists() || !directory2.exists()) {
//			
//			System.out.println("폴더생성 : " + directory.mkdirs());
//			System.out.println("폴더생성 : " + directory2.mkdirs());
			
//		}
		
		return null;
	}
	
	
	
	/* 카테고리 조회 */
	@GetMapping("/category/selectAll")
	public ModelAndView categoryList(ModelAndView mv) {
		
		/* 카테고리 전체 조회*/
		List<ProductCategoryDTO> allCategoryList = productService.allCategoryList();
		
		/* 모달창에 하위카테고리 등록을 위해 조회*/
		List<ProductCategoryDTO> refCategoryList = productService.refCategoryList();
		
		/* 카테고리(소) 리스트 조회 */
		List<ProductCategoryDTO> categoryList = productService.categoryList();
		
		System.out.println("allCategoryList : " + allCategoryList);
		
		mv.addObject("refCategoryList", refCategoryList);
		mv.addObject("categoryList", categoryList);
		mv.addObject("allCategoryList", allCategoryList);
		mv.setViewName("product/categoryList");
		
		return mv;
		
	}
	
	/*카테고리(중) 등록*/
	@PostMapping("/category/medium/insert")
	public ModelAndView insertRefCategory(ModelAndView mv, @ModelAttribute ProductCategoryDTO parameter,  
			RedirectAttributes rttr) {
		
		System.out.println("parameter : " + parameter);
		
		int result = productService.insertRefCategory(parameter);
		
		String message = "";
		
		if(result > 0) {
			message = "카테고리 등록에 성공하셨습니다.";
		} else {
			message = "카테고리 등록에 실패하셨습니다.";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/product/category/selectAll");
		return mv;
	}
	
	/*카테고리(소) 등록*/
	@PostMapping("/category/small/insert")
	public ModelAndView insertChildCategory(ModelAndView mv, @ModelAttribute ProductCategoryDTO parameters,  
			RedirectAttributes rttr) {
		
		System.out.println("parameters : " + parameters);
		
		int result = productService.insertCategory(parameters);
		
		String message = "";
		
		if(result > 0) {
			message = "카테고리 등록에 성공하셨습니다.";
		} else {
			message = "카테고리 등록에 실패하셨습니다.";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/product/category/selectAll");
		return mv;
	}
	
	/* 카테고리(중) 수정*/
	@PostMapping("/category/medium/modify")
	public ModelAndView updateMediumCategory(ModelAndView mv, @ModelAttribute ProductCategoryDTO parameters,
			RedirectAttributes rttr) {
		
		System.out.println("parameters : " + parameters);
		
		int result = productService.updateRefCategory(parameters);
		
		String message = "";
		
		if(result > 0) {
			message = "카테고리 수정에 성공하셨습니다.";
		} else {
			message = "카테고리 수정에 실패하셨습니다.";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/product/category/selectAll");
		return mv;
	}
	
	
	/* 카테고리(소) 수정하기 위한 메소드*/
	@GetMapping("/category/small/selectAll")
	public ModelAndView selectMediumCategory(ModelAndView mv, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		List<ProductCategoryDTO> selectCategoryForUdpate = productService.categoryList();
		
		System.out.println("selectCategoryForUdpate : " + selectCategoryForUdpate);
		
		mv.addObject("selectCategoryForUdpate", objectMapper.writeValueAsString(selectCategoryForUdpate));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 카테고리(소) 수정을 위해 상위카테고리 정보를 가지고 온다.*/
	@GetMapping("/editCategory")
	public ModelAndView editCategory(ModelAndView mv, HttpServletResponse response, @RequestParam int selectedCategory)throws JsonProcessingException {
		
		response.setContentType("application/json; 	charset=UTF-8");
		
		System.out.println(selectedCategory);
		System.out.println(selectedCategory);
		System.out.println(selectedCategory);
		System.out.println(selectedCategory);
		System.out.println(selectedCategory);

		ProductCategoryDTO selectOneCategory = productService.selectOneCategory(selectedCategory);
		
		mv.addObject("selectOneCategory", selectOneCategory);
		mv.setViewName("jsonView");
		return mv;
		

	}
	
	@PostMapping("/category/small/modify")
	public ModelAndView updateSmallCategory(ModelAndView mv, @ModelAttribute ProductCategoryDTO parameters,
			RedirectAttributes rttr) {
		
		System.out.println("@");
		System.out.println("@");
		System.out.println("@");
		System.out.println(parameters);
		
		System.out.println("parameters : " + parameters);
		
		int result = productService.updateCategory(parameters);
		
		String message = "";
		
		if(result > 0) {
			message = "카테고리 수정에 성공하셨습니다.";
		} else {
			message = "카테고리 수정에 실패하셨습니다.";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/product/category/selectAll");
		return mv;
		
	}
	
}
