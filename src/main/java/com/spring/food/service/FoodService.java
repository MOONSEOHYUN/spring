package com.spring.food.service;

import java.util.List;
import java.util.Map;

public interface FoodService {
	public List<Map<String, String>> food(String key, String value) throws Exception;
}
