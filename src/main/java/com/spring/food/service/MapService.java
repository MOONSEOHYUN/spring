package com.spring.food.service;

import java.util.Map;

public interface MapService {
//	주소를 이준으로 위치값 찾기
	public Map<String,Double> geocoding(String address) throws Exception;
}
