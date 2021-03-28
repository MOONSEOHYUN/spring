package com.spring.food.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.food.controller.LoginController;
import com.spring.food.service.LoginService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService lservice;
	
//	로그인
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(String id, String passwd, HttpSession session, RedirectAttributes rdttr, Model model) throws Exception{
		logger.info(id+":"+passwd);
		Map<String,Object> resultMap=lservice.loginCheck(id, passwd);
		if((int)resultMap.get("result")==0) {
			logger.info(id);
			session.setAttribute("id",id);
			session.setMaxInactiveInterval(60*60*100);
		}
		rdttr.addFlashAttribute("resultMap",resultMap);
		return "redirect:/main";
	}
	
//	로그아웃
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rdttr) {
//		세션의 모든 정보 소멸
		session.invalidate();
		
		rdttr.addFlashAttribute("msg","로그아웃되었습니다.");
		return "redirect:/main";
	}
}
