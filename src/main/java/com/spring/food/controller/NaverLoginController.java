package com.spring.food.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.food.service.NaverLoginService;

@Controller
public class NaverLoginController {
	private static final Logger logger = LoggerFactory.getLogger(NaverLoginController.class);
	
	@Autowired
	private NaverLoginService nservice;
		
//	네이버로그인 인증코드 콜백
	@RequestMapping("callback")
	public String callback(String code, String state, HttpSession session) throws Exception{
		String email=nservice.getNaverUser(code, state);
		
//		이메일을 이용해 회원가입 및 로그인
		nservice.loginNaver(email);
//		세션에 email저장
		session.setAttribute("id", email);
		session.setMaxInactiveInterval(60*60*100);
		
		return "redirect:/main";
	}
}
