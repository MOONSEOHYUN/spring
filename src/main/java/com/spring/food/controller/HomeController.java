package com.spring.food.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.food.dto.PageDTO;
import com.spring.food.service.NaverLoginService;

@Controller
@SessionAttributes("pdto")
public class HomeController {
	
	@Autowired
	private NaverLoginService nservice;
	
//	메인폼
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main(PageDTO pdto, Model model, HttpSession session) throws Exception{
		pdto.setCurPage(1);
		model.addAttribute("pdto",pdto);	//@SessionAttributes("pdto")에 데이터를 넣음
		
		//네이버 간편가입 url 얻기
		Map<String, String> resultMap = nservice.getApiUrl();
		//클라이언트 인증값 세션에 저장
		session.setAttribute("state", resultMap.get("state"));
		model.addAttribute("apiURL", resultMap.get("apiURL") );
		
		return "main";
	}
	
	//회사
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public String info() {
		return "company";
	}
}
