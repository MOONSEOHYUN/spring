package com.spring.food.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.food.dao.FoodAdditivesDAO;
import com.spring.food.dto.FoodAdditivesDTO;

@Service
public class FoodApiServiceImpl implements FoodApiService{
	@Autowired
	private FoodService fservice;
	@Autowired
	private FoodAdditivesDAO fadao;
	
	public Map<String,Object> foodAdd(String key, String value) throws Exception{
//		식품
		List<Map<String, String>> foodlist=fservice.food(key, value);
		Map<String, String> fmap=foodlist.get(0);
		String[] mtrlArr=fmap.get("RAWMTRL_NM").split(",");
		
		List<String> flist=new ArrayList<String>();
		
		System.out.println("mtrlArr: "+mtrlArr.length);
		
//		식품첨가물
		List<String> falist=new ArrayList<String>();
		
		Map<String, Object> resultMap=new HashMap<>();
		
		int cnt=0;
		
		for(int i=0; i<mtrlArr.length; i++) {
			if(fadao.foodAddSelectOne(mtrlArr[i])!=null){
				falist.add(mtrlArr[i]);
				cnt++;
				
			}else {
				flist.add(mtrlArr[i]);
			}
		}
		resultMap.put("flist", flist);
		resultMap.put("falist",falist);
		resultMap.put("cnt", cnt);
		return resultMap;
	}
}
