package com.spring.food.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.food.dao.MemberDAO;
import com.spring.food.dto.MemberDTO;
import com.spring.food.service.FileService;
import com.spring.food.service.MailSendService;
import com.spring.food.service.MemberServiceImpl;

@Service
public class MemberServiceImpl implements MemberService{
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private MemberDAO mdao;
	@Autowired
	private FileService fservice;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	@Autowired
	private MailSendService msservice;
	
	@Override
	public Map<String, String> idCheck(String id) throws Exception {
		Map<String, String> resultMap=new HashMap<String, String>();
//		만약 id가 존재한다면
		MemberDTO mdto=mdao.selectOne(id);
		if(mdto!=null) {
			resultMap.put("msg","사용 불가능한 아이디입니다.");
			resultMap.put("yn", "n");
		}else {
			resultMap.put("msg","사용 가능한 아이디입니다.");
			resultMap.put("yn", "y");
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> insert(MemberDTO mdto) throws Exception {
		logger.info(mdto.toString());
		
		Map<String,Object> resultMap=new HashMap<>();
		
//		회원 중복 처리
		MemberDTO rdto=mdao.selectOne(mdto.getId());
		if(rdto!=null) {
			logger.info("기존 회원 존재"+rdto.toString());
			resultMap.put("msg", "기존 회원 존재");
			resultMap.put("result", -1);
			return resultMap;
		}
		
//		파일 처리
		String filename=fservice.fileUpload(mdto.getImgfile());
		mdto.setFilename(filename);
		logger.info(mdto.toString());
		
//		암호화 처리 (평문->암호문)
		mdto.setPasswd(bcryptEncoder.encode(mdto.getPasswd()));
		int cnt=mdao.insert(mdto);
		
//		이메일 전송
		String authKey=msservice.sendAuthMail(mdto.getId(), mdto.getEmail());
		logger.info(authKey);
		
		resultMap.put("authKey", authKey);
		resultMap.put("msg", "가입 완료");
		resultMap.put("result", 0);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> emailauthUpdate(String id) throws Exception {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		mdao.emailauthUpdate(id);
		resultMap.put("msg", "이메일 인증 완료");
		resultMap.put("result", 0);
		return resultMap;
	}

	@Override
	public Map<String, Object> update(MemberDTO mdto) throws Exception {
		Map<String,Object> resultMap=new HashMap<>();
//		비밀번호 처리
//		기존 비밀번호 반드시 입력: 기존 비번 불일치시 수정 불가
		if(!bcryptEncoder.matches(mdto.getOldpasswd(), mdao.selectOne(mdto.getId()).getPasswd())) {
			resultMap.put("msg", "기존 비밀번호 불일치");
			resultMap.put("result", -1);
			
			return resultMap;
		}else if(mdto.getPasswd().equals("")) {
//			변경 비번은 선택: 변경 비번 없으면 기존 비번 그대로 저장
//			암호화 처리 (평문->암호문)
			mdto.setPasswd(bcryptEncoder.encode(mdto.getOldpasswd()));
		}else {
//			변경할 비번이 있다면
			mdto.setPasswd(bcryptEncoder.encode(mdto.getPasswd()));
		}
		
//		파일처리
		String filename=fservice.fileUpload(mdto.getImgfile());
//		파일 이름이 없다면 파일 수정 안함
		if(!filename.equals("")) {
			mdto.setFilename(filename);
		}
		
		mdao.update(mdto);
		resultMap.put("msg", "수정 완료");
		resultMap.put("result", 0);
		
		return resultMap;
	}

	@Override
	public MemberDTO selectOne(String id) throws Exception {
		return mdao.selectOne(id);
	}
}
