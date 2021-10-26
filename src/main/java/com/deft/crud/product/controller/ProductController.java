package com.deft.crud.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.deft.crud.product.model.dto.AccountDTO;
import com.deft.crud.product.model.dto.InsertProductDTO;
import com.deft.crud.product.model.dto.ManufacturerDTO;
import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.product.model.dto.ProductDTO;
import com.deft.crud.product.model.dto.ProductImageDTO;
import com.deft.crud.product.model.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.coobird.thumbnailator.Thumbnails;

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
		
		/* 리스트로 상품 목록을 전체 조회한다. */
		List<ProductDTO> allProductList = productService.allProductList();
		
		mv.addObject("allProductList", allProductList);
		mv.setViewName("product/productList");
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
	
	/* 상품 상세 조회 */
	@GetMapping("/select")
	public ModelAndView productDetail(ModelAndView mv, @RequestParam("productNo") int productNo) {
		
		/* 해당 상품 번호를 인자값으로 넘겨 하나의 상품 정보 조회 */
		ProductDTO productDetail = productService.productDetail(productNo);
		
		/* 상품 이미지 조회 */
		ProductImageDTO productImage = productService.selectProductImage(productNo);
		
		/* 상위 카테고리 리스트 조회 */
		List<ProductCategoryDTO> refCategoryList = productService.refCategoryList();
		
		/* 하위 카테고리 리스트 조회 */
		List<ProductCategoryDTO> categoryList = productService.categoryList();
		
		/* 제조사 정보 조회 */
		List<ManufacturerDTO> manufacturerList = productService.manufacturerList();
		
		/* 거래처 정보 조회*/
		List<AccountDTO> accountList = productService.accountList();
		
		mv.addObject("productDetail", productDetail);
		mv.addObject("productImage", productImage);
		mv.addObject("refCategoryList", refCategoryList);
		mv.addObject("categoryList", categoryList);
		mv.addObject("manufacturerList", manufacturerList);
		mv.addObject("accountList", accountList);

		mv.setViewName("product/productDetail");
		
		return mv;
	}
	
	/* 상품 등록 */
	@PostMapping("/insert")
	public ModelAndView insertProduct(ModelAndView mv, @ModelAttribute InsertProductDTO parameters,
			@RequestParam MultipartFile productThumbNail, HttpServletRequest request, RedirectAttributes rttr) {
		
		/* 절대경로를 변수에 초기화한다. */
		String root = request.getSession().getServletContext().getRealPath("\\");
		
		/* 이미지 원본파일과 썸네일을 저장할 경로를 설정해준다.*/
		String fileUploadDirectory = root + "\\upload\\productImage\\original";
		String thumbnailDirectory = root + "\\upload\\productImage\\thumbnail";
		
		/* 파일 객체 생성 */
		File directory = new File(fileUploadDirectory);
		File directory2 = new File(thumbnailDirectory);
		
		/* 이미지를 저장할 폴더가 없을시에 폴더를 생성해준다. */
		if(!directory.exists() || !directory2.exists()) {
			directory.mkdirs();										// 원본파일 폴더 생성
			directory2.mkdirs();									// 썸네일 폴더 생성
		}
		
		/* 이미지 원본파일 */
		String originFileName = productThumbNail.getOriginalFilename();
		
		/* 이미지 확장자 */
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		/* 이미지파일명이 중복되지 않도록 설정해준다. */
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		
		/* 맵에다 원본파일명과 저장경로와 저장된 파일명
		 * 썸네일 사이즈를 설정한 뒤 썸네일 경로까지 담아준다.*/
		Map<String, String> fileMap = new HashMap<>();
		fileMap.put("originFileName", originFileName);
		fileMap.put("savedName", savedName);
		fileMap.put("savedPath", fileUploadDirectory);
		
		/* 썸네일 너비 높이 설정 */
		int width = 250;
		int height = 250;
		
		/* 등록 결과 초기화 */
		int result = 0;
		
		try {
			
			/* 원본 이미지 저장 */
			productThumbNail.transferTo(new File(fileUploadDirectory + "\\" + savedName));
			
			/* 썸네일 저장 */
			Thumbnails.of(fileUploadDirectory + "\\" + savedName)
			.size(width, height)
			.toFile(thumbnailDirectory + "\\thumbnail_" + savedName);
			
			/* 썸네일 경로 멥에 담아준다. */
			fileMap.put("thumbnailPath", "/thumbnail_" + savedName);
			
			/* 상품 등록 결과 값이 1이 되면 등록 성공 아니면 실패 */
			result = productService.insertProduct(parameters, fileMap);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
			/* 어떠한 예외처리가 발생한다면 파일 삭제 */
			new File(fileUploadDirectory + "\\" + savedName).delete();
		}
		
		/* 성공 실패시 화면에 띄워줄 메세지를 설정해준다.*/
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
	
	/* 상품 업데이트 */
	@PostMapping("/modify") 
	public ModelAndView modifyProduct(ModelAndView mv, @ModelAttribute ProductDTO parameters, 
			HttpServletRequest request, RedirectAttributes rttr, @RequestParam MultipartFile productThumbNail) {
		
		/* 등록 결과 초기화 */
		int result = 0;
		
		/* 기본 파일 이름, 이미지 파일명, 원본파일 저장경로 ,상품번호, 썸네일로 저장한 파일명을 담아주는 DTO*/
		ProductImageDTO updateFile = new ProductImageDTO();
		
		/* 입력한 파일이 비어있지 않는다면 이미지 수정*/
		if(!productThumbNail.isEmpty()) {
		
			/* 절대경로를 변수에 초기화한다. */
			String root = request.getSession().getServletContext().getRealPath("\\");
			
			/* 이미지 원본파일과 썸네일을 저장할 경로를 설정해준다.*/
			String fileUploadDirectory = root + "\\upload\\productImage\\original";
			String thumbnailDirectory = root + "\\upload\\productImage\\thumbnail";
			
			/* 파일 객체 생성 */
			File directory = new File(fileUploadDirectory);
			File directory2 = new File(thumbnailDirectory);
			
//			/* 이미지를 저장할 폴더가 없을시에 폴더를 생성해준다. */
//			if(!directory.exists() || !directory2.exists()) {
//				System.out.println("폴더생성 : " + directory.mkdirs());
//				System.out.println("폴더생성 : " + directory2.mkdirs());
//			}
			
			/* 이미지 원본파일 */
			String originFileName = productThumbNail.getOriginalFilename();
			
			/* 이미지 확장자 */
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			
			/* 이미지파일명이 중복되지 않도록 설정해준다. */
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			/* 기본 파일 이름, 이미지 파일명, 원본파일 저장경로, 상품번호를 담아준다*/
			updateFile.setOriginalName(originFileName);
			updateFile.setSavedName(savedName);
			updateFile.setSavedPath(fileUploadDirectory);
			updateFile.setProductNo(parameters.getProductNo());
			
			/* 썸네일 너비 높이 설정 */
			int width = 250;
			int height = 250;
			
			try {
					
				/* 원본 이미지 저장 */
				productThumbNail.transferTo(new File(fileUploadDirectory + "\\" + savedName));
				
				/* 썸네일 경로 저장 */
				Thumbnails.of(fileUploadDirectory + "\\" + savedName)
				.size(width, height)
				.toFile(thumbnailDirectory + "\\thumbnail_" + savedName);
				
				/* html에서 이미지를 조회할 수 있도록 썸네일 경로를 dto에 담아준다.*/
				updateFile.setThumbnailPath("/thumbnail_" + savedName);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				/* 어떠한 예외처리가 발생한다면 파일 삭제 */
				new File(fileUploadDirectory + "\\" + savedName).delete();
				e.printStackTrace();
			}
			
			result = productService.modifyProductWithImg(parameters, updateFile);
			
		} else {
			
			result = productService.modifyProductForText(parameters);
			
		}
		
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
	
	/* 카테고리 조회 */
	@GetMapping("/category/selectAll")
	public ModelAndView categoryList(ModelAndView mv) {
		
		/* 카테고리 전체 조회*/
		List<ProductCategoryDTO> allCategoryList = productService.allCategoryList();
		
		/* 모달창에 하위카테고리 등록을 위해 조회*/
		List<ProductCategoryDTO> refCategoryList = productService.refCategoryList();
		
		/* 하위 카테고리 리스트 조회 */
		List<ProductCategoryDTO> categoryList = productService.categoryList();
		
		mv.addObject("refCategoryList", refCategoryList);
		mv.addObject("categoryList", categoryList);
		mv.addObject("allCategoryList", allCategoryList);
		mv.setViewName("product/categoryList");
		
		return mv;
		
	}
	
	/*상위 카테고리 등록*/
	@PostMapping("/category/medium/insert")
	public ModelAndView insertRefCategory(ModelAndView mv, @ModelAttribute ProductCategoryDTO parameter,  
			RedirectAttributes rttr) {
		
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
	
	/*하위 카테고리 등록*/
	@PostMapping("/category/small/insert")
	public ModelAndView insertChildCategory(ModelAndView mv, @ModelAttribute ProductCategoryDTO parameters,  
			RedirectAttributes rttr) {
		
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
	
	/* 상위 카테고리 수정*/
	@PostMapping("/category/medium/modify")
	public ModelAndView updateMediumCategory(ModelAndView mv, @ModelAttribute ProductCategoryDTO parameters,
			RedirectAttributes rttr) {
		
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
	
	/* 다중 셀렉트박스를 구현하기 위해 선택한 상위 카테고리의 번호를 인자값으로 넘겨 하위 카테고리 조회 */
	@GetMapping(value = "/selectCategoryCode", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductCategoryDTO>	selectCategoryList(@RequestParam("refCategoryCode") int refCategoryCode) {
		
		return productService.selectSmallCategoryList(refCategoryCode);
		
	}
	
	
	/* 하위 카테고리 수정하기 위한 메소드*/
	@GetMapping("/category/small/selectAll")
	public ModelAndView selectMediumCategory(ModelAndView mv, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		List<ProductCategoryDTO> selectCategoryForUdpate = productService.categoryList();
		
		mv.addObject("selectCategoryForUdpate", objectMapper.writeValueAsString(selectCategoryForUdpate));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 하위 카테고리 수정을 위해 상위카테고리 정보를 가지고 온다.*/
	@GetMapping("/editCategory")
	public ModelAndView editCategory(ModelAndView mv, HttpServletResponse response, @RequestParam int selectedCategory) throws JsonProcessingException {
		
		response.setContentType("application/json; 	charset=UTF-8");

		ProductCategoryDTO selectOneCategory = productService.selectOneCategory(selectedCategory);
		
		mv.addObject("selectOneCategory", selectOneCategory);
		mv.setViewName("jsonView");
		return mv;

	}
	
	/* 하위 카테고리 수정*/
	@PostMapping("/category/small/modify")
	public ModelAndView updateSmallCategory(ModelAndView mv, @ModelAttribute ProductCategoryDTO parameters,
			RedirectAttributes rttr) {
		
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
