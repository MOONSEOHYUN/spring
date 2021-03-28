package com.spring.food.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.food.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession session;
		
	@Override
	public MemberDTO selectOne(String id) throws Exception{
		return session.selectOne("com.spring.food.MemberMapper.selectOne",id);
	}

	@Override
	public int insert(MemberDTO mdto) throws Exception{
		return session.insert("com.spring.food.MemberMapper.insert",mdto);
	}

	@Override
	public int emailauthUpdate(String id) throws Exception{
		return session.update("com.spring.food.MemberMapper.emailauthUpdate",id);
	}

	@Override
	public void update(MemberDTO mdto) throws Exception{
		session.update("com.spring.food.MemberMapper.update",mdto);
	}
	
	// 네이버 간편 가입
	@Override
	public MemberDTO selectOneNaver(String email) throws Exception{
		return session.selectOne("com.spring.food.MemberMapper.selectOneNaver", email);
	}
	
	@Override
	public int insertNaver(String email) throws Exception{
		return session.insert("com.spring.food.MemberMapper.insertNaver", email);
	}


}
