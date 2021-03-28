package com.spring.food.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.food.controller.MemberController;
import com.spring.food.dto.MemberDTO;
import com.spring.food.service.MemberService;
import com.spring.food.service.NaverLoginService;

@Controller
@RequestMapping("member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService mservice;
	@Autowired
	private NaverLoginService nservice;
	
//	가입폼
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(HttpSession session, Model model) throws Exception{
		//네이버 간편가입 url 얻기
		Map<String, String> resultMap = nservice.getApiUrl();
		//클라이언트 인증값 세션에 저장
		session.setAttribute("state", resultMap.get("state"));
		model.addAttribute("apiURL", resultMap.get("apiURL") );
		
		return "member/add";
	}
//	가입
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(MemberDTO mdto, Model model, RedirectAttributes rdttr, HttpSession session) throws Exception {
		logger.info(mdto.toString());
		Map<String,Object> resultMap=mservice.insert(mdto);
		logger.info(resultMap.toString());
		
//		인증키를 세션에 넣기
//		key: id, value: authKey
		session.setAttribute(mdto.getId(), resultMap.get("authKey"));
		session.setMaxInactiveInterval(30*60);
		
		if((int)resultMap.get("result")==-1) {
			model.addAttribute("resultMap",resultMap);
			return "member/add";
		}else {
			rdttr.addFlashAttribute("resultMap",resultMap);
			return "redirect:/main";
		}
		
	}
	
//	아이디 중복체크
	@ResponseBody
	@RequestMapping(value="idCheck", method=RequestMethod.POST)
	public Map<String, String> idCheck(String id) throws Exception{
		logger.info(id);
		Map<String, String> resultMap=mservice.idCheck(id);
		logger.info(resultMap.toString());
		return resultMap;
	}
	
//	주소창
	@RequestMapping(value="jusoPopup")
	public String jusoPopup() {
		return "member/jusoPopup";
	}
	
//	이메일에서 인증 클릭했을떄
	@RequestMapping("signUpConfirm")
	public String signUpConfirm(@RequestParam Map<String, String> map, HttpSession session, RedirectAttributes rdttr) throws Exception{
	    //id, authKey 가 일치할경우 emailauth 업데이트
		logger.info(map.toString());
		String sessionAuthKey= (String)session.getAttribute(map.get("id"));	//세션의 authKey 가져오기 (id의 value값이 authKey)
		if(sessionAuthKey==null) {
			logger.info("세션 미존재");
			return "member/add";
		}
		logger.info(sessionAuthKey);
		if(sessionAuthKey.equals(map.get("authKey"))) {
			logger.info("메일인증 성공");
//			인증성공하면 emailauth 1로 수정
			logger.info(map.get("id"));
			
			Map<String, Object> resultMap=mservice.emailauthUpdate(map.get("id"));
			rdttr.addFlashAttribute("resultMap",resultMap);
			
		}else {
			rdttr.addFlashAttribute("msg","인증키가 일치하지 않습니다.");
		}
		
		return "redirect:/main";
		
	}
	
//	회원수정폼
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public String modify(HttpSession session, Model model) throws Exception{
		String id=(String)session.getAttribute("id");
		MemberDTO mdto=mservice.selectOne(id);
		logger.info(mdto.getFilename());
		model.addAttribute("mdto",mdto);
		return "member/modify";
	}
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute("mdto") MemberDTO mdto, Model model, RedirectAttributes rdttr) throws Exception{
		Map<String,Object> resultMap=mservice.update(mdto);
		if((int)resultMap.get("result")==-1) {
			model.addAttribute("resultMap",resultMap);
			model.addAttribute("mdto",mdto);
			return "member/modify";
		}else {
			rdttr.addFlashAttribute("resultMap", resultMap);
			return "redirect:/main";
		}
	}
}
