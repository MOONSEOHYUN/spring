package com.spring.food.dao;

import com.spring.food.dto.MemberDTO;

public interface MemberDAO {
//	멤버 한명 조회
	public MemberDTO selectOne(String id) throws Exception;
//	멤버 추가
	public int insert(MemberDTO mdto) throws Exception;
//	이메일 인증
	public int emailauthUpdate(String id) throws Exception;
//	멤버 수정
	public void update(MemberDTO mdto) throws Exception;
	
//	네이버간편가입 유저
	public MemberDTO selectOneNaver(String email) throws Exception;
	public int insertNaver(String email) throws Exception;
}
