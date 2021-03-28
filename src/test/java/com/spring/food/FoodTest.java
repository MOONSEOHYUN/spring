package com.spring.food;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.food.service.FoodAdditivesService;
import com.spring.food.service.FoodApiService;
import com.spring.food.service.FoodService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FoodTest {
	
	@Autowired
	private FoodService fservice;
	@Autowired
	private FoodAdditivesService faservice;
	@Autowired
	private FoodApiService apiservice;

	@Test
	public void Foodtest() throws Exception{
		fservice.food("BSSH_NM", "매일식품주식회사");
	}
	
	@Test
	public void FoodAddtest() throws Exception{
		faservice.FoodAdditives();
	}
	
	@Test
	public void FoodApitest() throws Exception{
		apiservice.foodAdd("PRDLST_REPORT_NO", "197506140032047");
	}

}
