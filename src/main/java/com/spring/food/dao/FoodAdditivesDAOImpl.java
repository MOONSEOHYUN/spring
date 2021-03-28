package com.spring.food.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.food.dto.FoodAdditivesDTO;

@Repository
public class FoodAdditivesDAOImpl implements FoodAdditivesDAO{
	@Autowired
	private SqlSession session;

	@Override
	public void foodAddInsert(FoodAdditivesDTO fadto) throws Exception {
		session.insert("com.spring.food.FoodAdditivesMapper.foodAddInsert",fadto);
	}

	@Override
	public FoodAdditivesDTO foodAddSelectOne(String pcode) throws Exception {
		return session.selectOne("com.spring.food.FoodAdditivesMapper.foodAddSelectOne",pcode);
	}

	@Override
	public List<FoodAdditivesDTO> foodAddSelectList() throws Exception {
		return session.selectList("com.spring.food.FoodAdditivesMapper.foodAddSelectList");
	}

}
