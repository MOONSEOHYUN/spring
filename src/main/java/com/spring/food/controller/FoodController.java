package com.spring.food.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.food.dto.FoodAdditivesDTO;
import com.spring.food.service.FoodAdditivesService;
import com.spring.food.service.FoodApiService;
import com.spring.food.service.FoodService;

@Controller
@RequestMapping("food")
public class FoodController {
	@Autowired
	private FoodService fservice;
	@Autowired
	private FoodAdditivesService faservice;
	@Autowired
	private FoodApiService apiservice;
	
//	식품 리스트 폼
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String foodList() throws Exception{
		
		return "food/foodList";
	}
//	식품 리스트
	@ResponseBody
	@RequestMapping(value="/foodList", method=RequestMethod.GET)
	public List<Map<String,String>> foodList(String key, String value) throws Exception{
		return fservice.food(key, value);
	}
	
//	식품 상세
	@RequestMapping(value="/detail/{PRDLST_REPORT_NO}", method=RequestMethod.GET)
	public String foodDetail(@PathVariable("PRDLST_REPORT_NO") String PRDLST_REPORT_NO, Model model) throws Exception{
		String key="PRDLST_REPORT_NO";
		List<Map<String, String>> foodlist=fservice.food(key, PRDLST_REPORT_NO);
		
//		식품
		Map<String, String> fmap=foodlist.get(0);
		System.out.println("식품명: "+fmap.get("PRDLST_NM"));
//		String[] mtrlArr=fmap.get("RAWMTRL_NM").split(",");
		
//		식품첨가물
//		List<FoodAdditivesDTO> falist=faservice.foodAddSelectList();
		
		
//		식품첨가물 개수
//		int facnt=apiservice.foodAdd(key, PRDLST_REPORT_NO);
//		
//		model.addAttribute("mtrlArr",mtrlArr);
//		model.addAttribute("falist",falist);
//		model.addAttribute("facnt",facnt);
		
		Map<String,Object> resultMap=apiservice.foodAdd(key, PRDLST_REPORT_NO);
		
		model.addAttribute("fmap",fmap);
		model.addAttribute("flist",resultMap.get("flist"));
		model.addAttribute("falist",resultMap.get("falist"));
		model.addAttribute("cnt",resultMap.get("cnt"));
		
		return "food/foodDetail";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
