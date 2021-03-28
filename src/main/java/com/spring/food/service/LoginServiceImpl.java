package com.spring.food.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.food.dao.MemberDAO;
import com.spring.food.dto.MemberDTO;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	MemberDAO mdao;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public Map<String, Object> loginCheck(String id, String passwd) throws Exception{
//		기존에 존재하는 회원인지 체크
		Map<String,Object> resultMap=new HashMap<>();
		MemberDTO mdto=mdao.selectOne(id);
		if(mdto==null) {
			resultMap.put("msg", "회원이 아닙니다.");
			resultMap.put("result",-1);
			
			return resultMap;
		}else if(!bcryptEncoder.matches(passwd, mdto.getPasswd())) {
//			기존에 존재하는 회원이면 패스워드 체크
//			기존에 사용자가 입력한 passwd와 db에 등록된 passwd 매치
			resultMap.put("msg", "비밀번호 불일치");
			resultMap.put("result", -1);
				
			return resultMap;
		}else if(!mdto.getEmailauth().equals("1")) {
//			이메일 인증 체크
			resultMap.put("msg","이메일 인증을 해주세요.");
			resultMap.put("result", -1);
			
			return resultMap;
		}else {
			resultMap.put("msg", "로그인 성공");
			resultMap.put("result", 0);
			
			return resultMap;
		}

	}
	
}
