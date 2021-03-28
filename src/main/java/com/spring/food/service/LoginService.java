package com.spring.food.service;

import java.util.Map;

public interface LoginService {
	public Map<String,Object> loginCheck(String id, String passwd) throws Exception;
}
