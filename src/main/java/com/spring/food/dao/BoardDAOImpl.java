package com.spring.food.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.food.dto.BoardDTO;
import com.spring.food.dto.PageDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession session;
	
	@Override
	public int insert(BoardDTO bdto) throws Exception {
		return session.insert("com.spring.food.BoardMapper.insert",bdto);
	}

	@Override
	public int update(BoardDTO bdto) throws Exception {
		return session.update("com.spring.food.BoardMapper.update",bdto);
	}

	@Override
	public int delete(int bnum) throws Exception {
		return session.delete("com.spring.food.BoardMapper.delete",bnum);
	}

	@Override
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception {
		return session.selectList("com.spring.food.BoardMapper.selectList",pdto);
	}

	@Override
	public BoardDTO selectOne(int bnum) throws Exception {
		return session.selectOne("com.spring.food.BoardMapper.selectOne",bnum);
	}

	@Override
	public void updateReadcnt(int bnum) throws Exception {
		session.update("com.spring.food.BoardMapper.updateReadcnt",bnum);
	}

	@Override
	public void updateLikecnt(int bnum) throws Exception {
		session.update("com.spring.food.BoardMapper.updateLikecnt",bnum);
		
	}

	@Override
	public void updateDislikecnt(int bnum) throws Exception {
		session.update("com.spring.food.BoardMapper.updateDislikecnt",bnum);
		
	}

	@Override
	public void updateLikecntMinus(int bnum) throws Exception {
		session.update("com.spring.food.BoardMapper.updateLikecntMinus",bnum);
		
	}

	@Override
	public void updateDislikecntMinus(int bnum) throws Exception {
		session.update("com.spring.food.BoardMapper.updateDislikecntMinus",bnum);
	}

	@Override
	public int selectTotCnt(PageDTO pdto) {
		return session.selectOne("com.spring.food.BoardMapper.selectTotCnt", pdto);
	}
}
