package com.spring.food.service;

import java.util.Map;

public interface NaverLoginService {
//	네이버 인증 api url 생성
	public Map<String, String> getApiUrl() throws Exception;
	
//	callback
	public String getNaverUser(String code, String state) throws Exception;
	
//	네이버 간편가입 로그인
	public int loginNaver(String email) throws Exception;
}
