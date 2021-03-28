package com.spring.food.dao;

import java.util.List;

import com.spring.food.dto.ReplyDTO;

public interface ReplyDAO {
//	댓글 추가
	public void insert(ReplyDTO rdto) throws Exception;
//	
	public void updateRestep(ReplyDTO rdto) throws Exception;
//	댓글 리스트
	public List<ReplyDTO> selectList(int bnum) throws Exception;
//	댓글 수정
	public void update(ReplyDTO rdto) throws Exception;
//	댓글 삭제
	public void delete(int rnum) throws Exception;
//	
	public void deleteBoard(int bnum) throws Exception;
//	
	public ReplyDTO selectOne(int rnum) throws Exception;
//	
	public int selectReplyCnt(ReplyDTO rdto) throws Exception;
	
//	좋아요,싫어요 수정 (+,-)
	public void updateLikecnt(int rnum) throws Exception;
	public void updateDislikecnt(int rnum) throws Exception;
	public void updateLikecntMinus(int rnum) throws Exception;
	public void updateDislikecntMinus(int rnum) throws Exception;
}
