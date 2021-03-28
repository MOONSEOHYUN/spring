package com.spring.food.service;

import java.util.Map;

public interface FoodApiService {
//	식품 속 식품첨가물 개수
	public Map<String,Object> foodAdd(String key, String value) throws Exception;
}
