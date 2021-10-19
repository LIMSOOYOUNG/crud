package com.deft.crud.stock.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.admin.adminemployee.model.service.AdminEmployeeService;
import com.deft.crud.member.model.dto.MemberDTO;
import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.order.model.dto.OrderDTO;
import com.deft.crud.stock.model.dto.ProductStockInfoDTO;
import com.deft.crud.stock.model.dto.RequestStockDTO;
import com.deft.crud.stock.model.dto.StorageDTO;
import com.deft.crud.stock.model.dto.approval.ApprovalModifyDTO;
import com.deft.crud.stock.model.dto.approval.PurchaseOrderDTO;
import com.deft.crud.stock.model.dto.approval.ReceivingReqDTO;
import com.deft.crud.stock.model.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/stock/*")
public class StockController {

	private final StockService stockService;
	private final AdminEmployeeService adminEmployeeService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public StockController(StockService stockService, ObjectMapper objectMapper, AdminEmployeeService adminEmployeeService) {
		this.stockService = stockService;
		this.objectMapper = objectMapper;
		this.adminEmployeeService = adminEmployeeService;
	}

	/* 창고보유(재고) 상품목록 전체 조회 */
	@GetMapping("/selectAll")
	public ModelAndView selectStockAll(ModelAndView mv, @AuthenticationPrincipal UserImpl userInfo) {
		
		/* 창고에 보유중인 전체 상품 목록 */
		List<StorageDTO> stockList = stockService.selectStockAll();
		
		/* 판매가능 상태인 모든 상품들 */
		List<StorageDTO> sellableProductList = stockService.selectSellableProductAll();
		
		/* 미완료 상태 주문서 목록 */
		int empNo = userInfo.getEmpNo();
		
		List<OrderDTO> orderList = stockService.selectPurchaseOrderAll(empNo);
		
		/* 결재 담당자 목록 */
		String deptCode = userInfo.getDeptCode();
		List<MemberDTO> managerList = adminEmployeeService.selectManagerList(deptCode);

		System.out.println(managerList);
		
		mv.addObject("managerList", managerList);
		mv.addObject("orderList", orderList);
		mv.addObject("stockList", stockList);
		mv.addObject("sellableProductList", sellableProductList);
		mv.setViewName("stock/stockList");
		
		return mv;
	}
	
	/* 선택한 상품의 재고관련 정보 조회 */
	@GetMapping("/productInfo/selectOne")
	public ModelAndView selectProductInfoByNo(ModelAndView mv, HttpServletResponse response
											, @RequestParam("productNo") int productNo) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		ProductStockInfoDTO productStockInfo = stockService.selectOneProductInfoByNo(productNo);
		
		mv.addObject("productStockInfo", objectMapper.writeValueAsString(productStockInfo));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 선택한 재고상품의 재고관련 정보 변경 */
	@PostMapping("/stockCondition/modify")
	public ModelAndView modifyStockCondition(ModelAndView mv, RedirectAttributes rttr
											, @ModelAttribute RequestStockDTO parameters) {
		
		boolean result = stockService.modifyStockCondition(parameters);
		
		String message = "";
		
		if(result) {
			message = "상품 재고상태를 변경하였습니다.";
		} else {
			message = "재고상태 변경 실패!";
		}
		
		rttr.addFlashAttribute("message", message);
		
		mv.setViewName("redirect:/stock/selectAll");
		return mv;
	}
	
	/* 입고요청서 작성(입고 요청한 상품들의 정보 insert ) */
	@PostMapping("receivingProduct/insert")
	public ModelAndView insertStockReceivingProduct(ModelAndView mv, HttpServletResponse response
													, @AuthenticationPrincipal UserImpl userInfo
													, @RequestBody List<RequestStockDTO> request) {
		
		response.setContentType("application/json; charset=UTF-8");
		
		boolean result = stockService.insertStockReceivingProduct(request, userInfo);
		
		if (!result) {
			result = false;
		}
		
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@GetMapping("orderInfo/select")
	public ModelAndView selectOrderDetail(ModelAndView mv, HttpServletResponse response,
										  @RequestParam String orderNo) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		OrderDTO orderInfo = stockService.selectOrderDetail(orderNo);
		
		mv.addObject("orderInfo", objectMapper.writeValueAsString(orderInfo));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	
	
// ----------------------------------요청 목록------------------------------------------------------------------------	
	
	/* 요청서 목록 전체 조회(담당자 or 사원) */
	@GetMapping("request/selectAll")
	public ModelAndView selectRequestAll(ModelAndView mv, @AuthenticationPrincipal UserImpl userInfo) {
		
		List<ReceivingReqDTO> receivingReqList = new ArrayList<>();
		
		receivingReqList = stockService.selectReceivingReqAll(userInfo);	
		
		mv.addObject("receivingReqList", receivingReqList);
		mv.setViewName("stock/requestList");
		
		return mv;
	}
	
	/* 요청서 목록 결재상태 별 조회 */
	@GetMapping("request/select")
	public ModelAndView selectRequestByStatus(ModelAndView mv, HttpServletResponse response
											, @RequestParam String documentStatus
											, @AuthenticationPrincipal UserImpl userInfo) throws JsonProcessingException {
	
		response.setContentType("application/json; charset=UTF-8");
		
		System.out.println("@@@@@@@@ documentStatus : " + documentStatus);
		
		List<ReceivingReqDTO> receivingReqList = new ArrayList<>();
		
		receivingReqList = stockService.selectReceivingReqByStatus(documentStatus, userInfo);	
		
		System.out.println("@@@@@ receivingReqList : " + receivingReqList);
		
		mv.addObject("receivingReqList", objectMapper.writeValueAsString(receivingReqList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	
	/* 선택한 요청서 상세 정보*/
	@GetMapping("request/selectOne")
	public ModelAndView selectReceivingReqByNo(ModelAndView mv
												, HttpServletResponse response
												, @AuthenticationPrincipal UserImpl userInfo
												, @RequestParam("reqNo") int reqNo) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		ReceivingReqDTO receivingReqInfo = stockService.selectReceivingReqByNo(reqNo);
		
		List<ReceivingReqDTO> receivingReqProductList = stockService.selectReceivingReqProductByReqNo(reqNo);
		
		String authority = userInfo.getAuthority();
		
		mv.addObject("receivingReqInfo", objectMapper.writeValueAsString(receivingReqInfo));				//요청서 내용
		mv.addObject("receivingReqProductList", objectMapper.writeValueAsString(receivingReqProductList));	//요청서 내용중 입고요청 상품목록 
		mv.addObject("authority", objectMapper.writeValueAsString(authority));								//접속중인 사용자의 정보
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 선택한 요청서 결재 처리*/
	@PostMapping("ApprovalStatus/modify")
	public ModelAndView modifyApprovalStatus(ModelAndView mv, RedirectAttributes rttr
											, @ModelAttribute ApprovalModifyDTO parameters) {
		
		boolean result = stockService.modifyApprovalStatus(parameters);
	
		String message = "";
		
		if(result) {
			message = "결재 처리완료";
		} else {
			message = "결재 처리실패!";
		}
		
		rttr.addFlashAttribute("message", message);
		
		mv.setViewName("redirect:/stock/request/selectAll");
		return mv;
	}
	
}







