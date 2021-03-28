package com.spring.food.service;

import java.util.Map;

import com.spring.food.dto.MemberDTO;

public interface MemberService {
//	아이디 체크
	public Map<String, String> idCheck(String id) throws Exception;
//	회원 추가
	public Map<String, Object> insert(MemberDTO mdto) throws Exception;
//	이메일 인증 업뎃
	public Map<String, Object> emailauthUpdate(String id) throws Exception;
//	회원 수정
	public Map<String, Object> update(MemberDTO mdto) throws Exception;
//	회원 한명 조회
	public MemberDTO selectOne(String id) throws Exception;
}
