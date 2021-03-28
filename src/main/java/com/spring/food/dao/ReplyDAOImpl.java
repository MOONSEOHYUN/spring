package com.spring.food.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.food.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(ReplyDTO rdto) throws Exception {
		session.insert("com.spring.food.ReplyMapper.insert",rdto);
		
	}

	@Override
	public void updateRestep(ReplyDTO rdto) throws Exception {
		session.update("com.spring.food.ReplyMapper.updateRestep",rdto);
	}

	@Override
	public List<ReplyDTO> selectList(int bnum) throws Exception {
		return session.selectList("com.spring.food.ReplyMapper.selectList",bnum);
	}

	@Override
	public void update(ReplyDTO rdto) throws Exception {
		session.update("com.spring.food.ReplyMapper.update",rdto);
	}

	@Override
	public void delete(int rnum) throws Exception {
		session.delete("com.spring.food.ReplyMapper.delete",rnum);
	}

	@Override
	public ReplyDTO selectOne(int rnum) throws Exception {
		return session.selectOne("com.spring.food.ReplyMapper.selectOne",rnum);
	}

	@Override
	public int selectReplyCnt(ReplyDTO rdto) throws Exception {
		return session.selectOne("com.spring.food.ReplyMapper.selectReplyCnt",rdto);
	}

	@Override
	public void deleteBoard(int bnum) throws Exception {
		session.delete("com.spring.food.ReplyMapper.deleteBoard",bnum);
	}

	@Override
	public void updateLikecnt(int rnum) throws Exception {
		session.update("com.spring.food.ReplyMapper.updateLikecnt",rnum);
	}

	@Override
	public void updateDislikecnt(int rnum) throws Exception {
		session.update("com.spring.food.ReplyMapper.updateDislikecnt",rnum);
	}

	@Override
	public void updateLikecntMinus(int rnum) throws Exception {
		session.update("com.spring.food.ReplyMapper.updateLikecntMinus",rnum);
	}

	@Override
	public void updateDislikecntMinus(int rnum) throws Exception {
		session.update("com.spring.food.ReplyMapper.updateDislikecntMinus",rnum);
	}
}
