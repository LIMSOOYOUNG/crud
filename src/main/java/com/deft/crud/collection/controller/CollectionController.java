package com.deft.crud.collection.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deft.crud.collection.model.dto.ChargeDTO;
import com.deft.crud.collection.model.service.CollectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/collection")
public class CollectionController {
	
	private final CollectionService collectionService;
	private final ObjectMapper objectMapper;
	
	@Autowired
	public CollectionController(CollectionService collectionService, ObjectMapper objectMapper) {
		this.collectionService = collectionService;
		this.objectMapper = objectMapper;
	}
	
	/* 수금 내역 연과 월별로 조회*/
	@GetMapping("/selectAll")
	public ModelAndView selectCollectionAll(ModelAndView mv) {
		
		/* 초기 페이지는 현재 연도와 월을 기준으로 조회하기 위해 LocalDate로 현재 연도와 월을 조회*/
		LocalDate selectChargeDate = LocalDate.now();
		int chargeYear = selectChargeDate.getYear();
		int chargeMonth = selectChargeDate.getMonthValue();
		
		ChargeDTO chargeDate = new ChargeDTO();
		chargeDate.setChargeYear(chargeYear);
		chargeDate.setChargeMonth(chargeMonth);
		
		List<ChargeDTO> selectCollectionAll = collectionService.selectCollectionAll(chargeDate);
		
		mv.addObject("chargeYear", chargeYear);
		mv.addObject("chargeMonth", chargeMonth);
		mv.addObject("selectCollectionAll", selectCollectionAll);
		mv.setViewName("/collection/selectCollectionAll");
		return mv;
	}
	
	/* 날짜별로 실적 확인 */
	@GetMapping("/select/date")
	public ModelAndView selectCollectionForDate(ModelAndView mv, HttpServletResponse response,
			@ModelAttribute ChargeDTO charge) throws JsonProcessingException {
		
		response.setContentType("UTF-8");
		
		List<ChargeDTO> selectCollectionForDate = collectionService.selectCollectionForDate(charge);
		
		mv.addObject("selectCollectionForDate", objectMapper.writeValueAsString(selectCollectionForDate));
		mv.setViewName("jsonView");
		return mv;
	}
}
