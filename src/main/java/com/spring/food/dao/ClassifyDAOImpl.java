package com.spring.food.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.food.dto.ClassifyDTO;

@Repository
public class ClassifyDAOImpl implements ClassifyDAO{
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(ClassifyDTO cdto) throws Exception {
		session.insert("com.spring.food.ClassifyMapper.insert", cdto);
	}

	@Override
	public ClassifyDTO selectOne(ClassifyDTO cdto) throws Exception {
		return session.selectOne("com.spring.food.ClassifyMapper.selectOne", cdto);
	}

	@Override
	public int delete(ClassifyDTO cdto) throws Exception {
		return session.delete("com.spring.food.ClassifyMapper.delete", cdto);
	}
}
