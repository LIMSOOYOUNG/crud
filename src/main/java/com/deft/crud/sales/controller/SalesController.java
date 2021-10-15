package com.deft.crud.sales.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deft.crud.member.model.service.UserImpl;
import com.deft.crud.product.model.dto.ProductCategoryDTO;
import com.deft.crud.sales.model.dao.SalesMapper;
import com.deft.crud.sales.model.dto.CollectBillDTO;
import com.deft.crud.sales.model.dto.PerformanceDTO;
import com.deft.crud.sales.model.dto.TargetPerfomDTO;
import com.deft.crud.sales.model.service.SalesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/sales")
public class SalesController {
	
	private final SalesService salesService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public SalesController(SalesService salesService, ObjectMapper objectMapper) {
		this.salesService = salesService;
		this.objectMapper = objectMapper;
	}
	
	/* 한 명의 사원 목표 실적을 조회하기 위한 컨트롤러(현재 연도와 월 기준) */
	@GetMapping("/selectAll")
	public ModelAndView selectUserSalesAll(ModelAndView mv, @AuthenticationPrincipal UserImpl loginInfo) {
		
		int empNo = loginInfo.getEmpNo();

		/* 사원 실적은 최신순으로 가지고 오기 위해
		 * 폼에다 실적을 등록하기 위한 연과 월을 확인해주기 위해 LocalDate 생성 후 현재 연도와 월의 객체를 뷰에 전달*/
		LocalDate insertTargetDate = LocalDate.now();
		
		String performYear = Integer.toString(insertTargetDate.getYear());				// 디비에 값 저장을 위해 String 타입으로 형변환(연도)
		String performMonth = Integer.toString(insertTargetDate.getMonthValue());       // 위와 내용 같음 (월)
		
		TargetPerfomDTO targetPerformDate = new TargetPerfomDTO();
		targetPerformDate.setPerformYear(performYear);
		targetPerformDate.setPerformMonth(performMonth);
		targetPerformDate.setEmpNo(empNo);
		
		/* 로그인 된 사원의 목표 실적 조회*/
		List<TargetPerfomDTO> empTargetPerformList = salesService.empTargetPerformList(targetPerformDate);
		
		mv.addObject("performYear", performYear);
		mv.addObject("performMonth", performMonth);
		mv.addObject("empTargetPerformList", empTargetPerformList);
		mv.setViewName("sales/selectEmpSales");
		 
		return mv;
	}
	
	/* 사원의 실제 개인실적 조회를 ajax 통신으로 조회한 후 목표실적과 비교한다. */
	@PostMapping("/user/perform/selectAll")
	public ModelAndView selectUserPerformList(ModelAndView mv, HttpServletResponse response, 
			@AuthenticationPrincipal UserImpl loginInfo, @RequestParam("performYear") String performYear) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		/* 세션에 저장된 사원 정보를 가지고 온다. */
		int empNo = loginInfo.getEmpNo();
		
		/* 사원 정보와 ajax 통신 할 때 받은 날짜 데이터와 세션에서 가져왔던 사원번호를 값을 담아준다. */
		TargetPerfomDTO parameters = new TargetPerfomDTO();
		parameters.setEmpNo(empNo);
		parameters.setPerformYear(performYear);
		
		/* 사원 실제 월간 실적 조회 */
		List <PerformanceDTO> empPerformList = salesService.empPerformList(parameters);
		System.out.println("empPerfomList : " + empPerformList);
		
		mv.addObject("empPerformList", objectMapper.writeValueAsString(empPerformList));
		mv.setViewName("jsonView");
		return mv;
	}

	/* 사원의 실제 개인실적 조회를 ajax 통신으로 조회한 후 목표실적과 비교한다. */
	@PostMapping("/user/perform/checked/selectAll")
	public ModelAndView selectUserPerformForYear(ModelAndView mv, HttpServletResponse response, 
			@AuthenticationPrincipal UserImpl loginInfo, @RequestParam("performYear") String performYear) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int empNo = loginInfo.getEmpNo();
		
		/* 사원 정보와 ajax 통신 할 때 받은 날짜 데이터와 세션에서 가져왔던 사원번호를 값을 담아준다. */
		TargetPerfomDTO parameters = new TargetPerfomDTO();
		parameters.setEmpNo(empNo);
		parameters.setPerformYear(performYear);
		
		/* 뷰에서 선택한 연도의 목표실적 조회 */
		List<TargetPerfomDTO> checkedEmpTargetPerformList = salesService.checkedEmpTargetPerformList(parameters);
		System.out.println("checkedEmpTargetPerformList : " + checkedEmpTargetPerformList);

		/* 사원 실제 월간 실적 조회 */
		List <PerformanceDTO> empPerformList = salesService.empPerformList(parameters);
		System.out.println("empPerfomList : " + empPerformList);
		
		mv.addObject("checkedEmpTargetPerformList", objectMapper.writeValueAsString(checkedEmpTargetPerformList));
		mv.addObject("empPerformList", objectMapper.writeValueAsString(empPerformList));
		mv.setViewName("jsonView");
		return mv;
	}
	
	/* 사원 개인 실적 상세조회 */
	@GetMapping("/select")
	public ModelAndView selectUserPerformDetail(ModelAndView mv, @RequestParam("empNo") int empNo, 
			@ModelAttribute CollectBillDTO parameters) {
		
		System.out.println("empNo : " + empNo);
		System.out.println("parameters : " + parameters);
		
		List<PerformanceDTO> selectUserPerformDetail = salesService.selectUserPerformDetail(empNo, parameters);
		
		System.out.println("selectUserPerformDetail : " + selectUserPerformDetail);
		
		mv.addObject("parameters", parameters);
		mv.addObject("selectUserPerformDetail", selectUserPerformDetail);
		mv.setViewName("sales/selectEmpSalesDetail");
		return mv;
	}
	
	/* 목표 실적 등록하는 모달창에서 전달 부서실적과 내 실적을 조회하기 위한 메소드 */
	@GetMapping("/last/month/perform/select")
	public ModelAndView selectLastMonthPerform(ModelAndView mv, @AuthenticationPrincipal UserImpl loginInfo) throws JsonProcessingException {
		
		/* 로그인된 사원의 번호와 부서 코드의 정보를 세션에서 가지고 온다.*/
		int empNo = loginInfo.getEmpNo();
		String deptCode = loginInfo.getDeptCode();
		
		/* LocalDate로 현재 시간 기준으로 연도와 전 달을 가지고 온다 */
		LocalDate lastMonth = LocalDate.now().minusMonths(1);
		
		int collectBillYear = lastMonth.getYear();
		int collectBillMonth = lastMonth.getMonthValue();
		
		/* 현재 연도와 전 달에 대한 값을 dto에 담아준다. */
		CollectBillDTO collectBillDate = new CollectBillDTO();
		collectBillDate.setCollectBillYear(collectBillYear);
		collectBillDate.setCollectBillMonth(collectBillMonth);
		
		/* 사원이 속한 부서 전 달 평균 실적 조회*/
		PerformanceDTO selectDeptAvgPeform = salesService.selectDeptAvgPeform(deptCode, collectBillDate);
		
		/* 사원의 전 실적 */
		PerformanceDTO selectEmpPeformLastMonth = salesService.selectEmpPeformLastMonth(empNo, collectBillDate);
		
		System.out.println("selectDeptAvgPeform : " + selectDeptAvgPeform);
		
		mv.addObject("selectDeptAvgPeform", objectMapper.writeValueAsString(selectDeptAvgPeform));
		mv.addObject("selectEmpPeformLastMonth", objectMapper.writeValueAsString(selectEmpPeformLastMonth));
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	/* 사원 목표 실적 등록*/
	@PostMapping("/insert/target")
	public ModelAndView insertTargetSales(ModelAndView mv, @AuthenticationPrincipal UserImpl loginInfo, 
			@ModelAttribute TargetPerfomDTO parameters, RedirectAttributes rttr) {
		
		int empNo = loginInfo.getEmpNo();
		
		parameters.setEmpNo(empNo);
		
		int result = salesService.insertTargetSales(parameters, loginInfo);
		
		String message = "";
		
		if(result > 0) {
			message = "목표 실적 등록을 성공하셨습니다!";
		} else {
			message = "이미 실적을 등록하셨습니다!";
		}
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/sales/selectAll");
		return mv;
	}
	
	/* 부서 전체 영업실적*/
	@GetMapping("/dept/selectAll")
	public ModelAndView selectDeptSalesAll(ModelAndView mv) {
		
		LocalDate deptPerformDate = LocalDate.now();

		int collectBillYear = deptPerformDate.getYear(); 										// 디비에 값 저장을 위해 String 타입으로 형변환(연도)
		int collectBillMonth = deptPerformDate.getMonthValue();								// 위와 내용 같음 (월)
		
		CollectBillDTO collectBillDate = new CollectBillDTO();
		collectBillDate.setCollectBillYear(collectBillYear);
		collectBillDate.setCollectBillMonth(collectBillMonth);
		
		System.out.println(collectBillDate.getCollectBillYear());
		System.out.println(collectBillDate.getCollectBillMonth());
		
		List<PerformanceDTO> selectDeptPerformList = salesService.selectDeptPerformList(collectBillDate);
		
		System.out.println("selectDeptPerformList : " + selectDeptPerformList);
		
		/* 연도와 월을 비교하기 위해 */
		mv.addObject("selectDeptPerformList", selectDeptPerformList);
		mv.addObject("collectBillYear", collectBillYear);
		mv.addObject("collectBillMonth", collectBillMonth);
		mv.setViewName("sales/selectDeptSales");
		return mv;
		
	}
	
	/* ajax 통신으로 폼에서 입력한 연도와 월에 대한 부서 실적 정보를 가지고 온다.*/
	@GetMapping("/select/perform/date")
	public ModelAndView selectPerformForDate(ModelAndView mv, HttpServletResponse response,
			@ModelAttribute CollectBillDTO parameters) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		List<PerformanceDTO> selectPerformForDate = salesService.selectPerformForDate(parameters);
		
		System.out.println("selectPerformForDate : " + selectPerformForDate);
		
		mv.addObject("selectPerformForDate", objectMapper.writeValueAsString(selectPerformForDate));
		mv.setViewName("jsonView");
		return mv;
		
	}
	
	/* 한 부서에 대한 사원들의 영업실적 조회*/
	@GetMapping("/dept/select")
	public ModelAndView deptEachPerformList(ModelAndView mv, @RequestParam String deptCode,
			@ModelAttribute CollectBillDTO parameters) {
		
		System.out.println("parameters : " + parameters);
		System.out.println("deptCode : " + deptCode);
		
		/* 첫번째 보여질 페이지는 사원들의 현재 연도의 월에 대한 실적 정보여서 
		 * 현재 시간기준으로 연도와 월을 포맷한 후 인자값으로 넘긴다.*/
		List<PerformanceDTO> selectDeptEachPerformList = salesService.selectDeptEachPerformList(parameters, deptCode);
		
		mv.addObject("parameters", parameters);
		mv.addObject("selectDeptEachPerformList", selectDeptEachPerformList);
		mv.setViewName("sales/selectDeptEachSales");
		return mv;
	}
	
	/* 한 부서에 대한 사원들의 연도와 월에 맞게 목표실적을 조회한다. */
	@PostMapping("/dept/target/perform/selectAll")
	public ModelAndView deptTargetPerformList(ModelAndView mv, HttpServletResponse response,
			@ModelAttribute CollectBillDTO parameters) throws JsonProcessingException {
		
		
		response.setContentType("application/json; charset=UTF-8");
		
		List<TargetPerfomDTO> deptTargetPerformList = salesService.deptTargetPerformList(parameters);
		
		System.out.println("deptTargetPerformList : " + deptTargetPerformList );
		System.out.println("parameters : " + parameters);
		
		mv.addObject("deptTargetPerformList", objectMapper.writeValueAsString(deptTargetPerformList));
		mv.setViewName("jsonView");
		return mv;
	}
	
	/* 상품 실적 조회 */
	@GetMapping("/product/selectAll")
	public ModelAndView selectProductSalesAll(ModelAndView mv) {
		
		/* 초기화면에 현재 연도와 날짜의 상품을 조회하기 위해 LocalDate로 현재 연도와 월의 정보를 가지고 온다. */
		LocalDate productPeformDate = LocalDate.now();

		int collectBillYear = productPeformDate.getYear(); 										// 디비에 값 저장을 위해 String 타입으로 형변환(연도)
		int collectBillMonth = productPeformDate.getMonthValue();								// 위와 내용 같음 (월)
		
		CollectBillDTO collectBillDate = new CollectBillDTO();
		collectBillDate.setCollectBillYear(collectBillYear);
		collectBillDate.setCollectBillMonth(collectBillMonth);
		
		List<PerformanceDTO> selectProductPerformList = salesService.selectProductPerformList(collectBillDate);
		
		System.out.println("selectProductPerformList : " + selectProductPerformList);
		
		mv.addObject("collectBillYear", collectBillYear);
		mv.addObject("collectBillMonth", collectBillMonth);
		mv.addObject("selectProductPerformList", selectProductPerformList);
		mv.setViewName("sales/selectProductSales");
		return mv;
	}
	
	/* 폼에서 선택한 연도와 월에 대한 상품 실적 조회하기 */
	@GetMapping("/select/product/peform/date")
	public ModelAndView selectProductPeformForDate(ModelAndView mv, HttpServletResponse response,
			@ModelAttribute CollectBillDTO parameters) throws JsonProcessingException {
		
		System.out.println("parameters : " + parameters);
		
		response.setContentType("application/json; charset=UTF-8");
		
		List<PerformanceDTO> selectProductPeformForDate = salesService.selectProductPeformForDate(parameters);
		
		System.out.println("selectProductPeformForDate : " + selectProductPeformForDate);
		
		mv.addObject("selectProductPeformForDate", objectMapper.writeValueAsString(selectProductPeformForDate));
		mv.setViewName("jsonView");
		return mv;
	}
	
	/* 카테고리 실적조회 첫화면 연도와 월별로 전체 실적 조회를 한다. */
	@GetMapping("/category/selectAll")
	public ModelAndView selectCategorySalesAll(ModelAndView mv) {
		
		/* 초기화면에 현재 연도와 날짜의 상품을 조회하기 위해 LocalDate로 현재 연도와 월의 정보를 가지고 온다. */
		LocalDate productPeformDate = LocalDate.now();

		int collectBillYear = productPeformDate.getYear(); 										// 디비에 값 저장을 위해 String 타입으로 형변환(연도)
		int collectBillMonth = productPeformDate.getMonthValue();								// 위와 내용 같음 (월)
		
		CollectBillDTO collectBillDate = new CollectBillDTO();
		collectBillDate.setCollectBillYear(collectBillYear);
		collectBillDate.setCollectBillMonth(collectBillMonth);
		
		List<PerformanceDTO> selectCategoryPerformList = salesService.selectCategoryPerformList(collectBillDate);
		
		/* 상위 카테고리 조회 셀렉트박스에 조회하기 위해서*/
		List<ProductCategoryDTO> selectRefCategoryList = salesService.selectRefCategoryList(); 
		
		System.out.println("selectCategoryPerformList : " + selectCategoryPerformList);
		System.out.println("selectRefCategoryList : " + selectRefCategoryList);
		
		mv.addObject("collectBillYear", collectBillYear);
		mv.addObject("collectBillMonth", collectBillMonth);
		mv.addObject("selectCategoryPerformList", selectCategoryPerformList);
		mv.addObject("selectRefCategoryList", selectRefCategoryList);
		mv.setViewName("/sales/selectCategorySalesAll");
		return mv;
	}
	
	/* 폼에서 선택한 카테고리와 날짜를 기준으로 실적조회 */
	@PostMapping("/select/category/peform/date") 
	public ModelAndView selectCategoryPeformForDate(ModelAndView mv, HttpServletResponse response,
			@ModelAttribute CollectBillDTO parameters, @RequestParam int refCategoryCode) throws JsonProcessingException {
		
		System.out.println("  parameters : " +  parameters);
		System.out.println("  refCategoryCode : " +  refCategoryCode);
		
		response.setContentType("UTF-8");
		
		List<PerformanceDTO> selectCategoryPerformForDate = salesService.selectCategoryPerformForDate(parameters, refCategoryCode);
		
		System.out.println("selectCategoryPerformForDate : " + selectCategoryPerformForDate);
		
		mv.addObject("selectCategoryPerformForDate", objectMapper.writeValueAsString(selectCategoryPerformForDate));
		mv.setViewName("jsonView");
		return mv;
	}
	
	/* 카테고리 상세보기 페이지*/
	@GetMapping("/category/select")
	public ModelAndView selectCategoryPerformDetail(ModelAndView mv, @RequestParam int categoryCode,
			@ModelAttribute CollectBillDTO parameters) {
		
		System.out.println("categoryCode : " + categoryCode);
		System.out.println("parameters : " + parameters);
		
		/* 카테고리 상세 보기 언제 청구 됐고 어떤 사원이 얼마의 실적을 냈는지 조회한다.*/
		List<PerformanceDTO> selectCategoryPerformDetail = salesService.selectCategoryPerformDetail(categoryCode, parameters);
		System.out.println("selectCategoryPerformDetail : " + selectCategoryPerformDetail);
		
		mv.addObject("parameters", parameters);
		mv.addObject("selectCategoryPerformDetail", selectCategoryPerformDetail);
		mv.setViewName("/sales/selectCategorySalesDetail");
		return mv;
		
	}
}
