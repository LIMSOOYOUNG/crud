package com.deft.crud.estimate.model.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.deft.crud.configuration.CrudApplication;
import com.deft.crud.estimate.model.dto.EstimateDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CrudApplication.class})
public class EstimateServiceTests {
	
	@Autowired
	private EstimateService estimateService;
	
	@Test
	public void testSelectAllEstimate() throws Exception {

		List<EstimateDTO> estimateList = estimateService.selectEstimateList();
		
		assertNotNull(estimateList);
		
		for(EstimateDTO estimate : estimateList) {
			System.out.println(estimate);
		}
	}
}
