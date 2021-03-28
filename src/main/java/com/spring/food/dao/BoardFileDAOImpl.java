package com.spring.food.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.food.dto.BoardFileDTO;

@Repository
public class BoardFileDAOImpl implements BoardFileDAO{

	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(BoardFileDTO bfdto) throws Exception {
		session.insert("com.spring.food.BoardFileMapper.insert",bfdto);	
	}

	@Override
	public List<BoardFileDTO> selectList(int bnum) throws Exception {
		return session.selectList("com.spring.food.BoardFileMapper.selectList",bnum);
	}

	@Override
	public void delete(int fnum) {
		session.delete("com.spring.food.BoardFileMapper.delete",fnum);
		
	}

	@Override
	public void deleteBnum(int bnum) {
		session.delete("com.spring.food.BoardFileMapper.deleteBnum",bnum);
	}
}
