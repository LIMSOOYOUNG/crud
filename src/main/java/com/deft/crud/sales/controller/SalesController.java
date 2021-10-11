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

		/* 폼에다 실적을 등록하기 위한 연과 월을 확인해주기 위해 LocalDate 생성 후 현재 연도와 월의 객체를 뷰에 전달*/
		LocalDate insertTargetDate = LocalDate.now();
		
		String performYear = Integer.toString(insertTargetDate.getYear());				// 디비에 값 저장을 위해 String 타입으로 형변환(연도)
		String performMonth = Integer.toString(insertTargetDate.getMonthValue());       // 위와 내용 같음 (월)
		System.out.println("monthValue : " + insertTargetDate.getMonthValue());
		
		TargetPerfomDTO targetPerformDate = new TargetPerfomDTO();
		targetPerformDate.setPerformYear(performYear);
		targetPerformDate.setPerformMonth(performMonth);
		targetPerformDate.setEmpNo(empNo);
		
		/* 로그인 된 사원의 목표 실적 조회*/
		List<TargetPerfomDTO> empTargetPerformList = salesService.empTargetPerformList(targetPerformDate);

		System.out.println("empTargetPerformList : " + empTargetPerformList);
		
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
		
		/* 뷰에서 선택한 연도의 목표실적 조회*/
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
	public ModelAndView allDeptPerformList(ModelAndView mv) {
		
		LocalDate deptPerformDate = LocalDate.now();

		String collectBillYear = Integer.toString(deptPerformDate.getYear());				// 디비에 값 저장을 위해 String 타입으로 형변환(연도)
		String collectBillMonth = Integer.toString(deptPerformDate.getMonthValue());        // 위와 내용 같음 (월)
		
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
		
		System.out.println("parameters : " + parameters);
		
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
	
	
	
}
