package com.spring.food.service;

import java.util.List;

import com.spring.food.dto.ReplyDTO;

public interface ReplyService {
//	댓글 추가
	public void insert(ReplyDTO rdto) throws Exception;
//	댓글 한건 
	public ReplyDTO selectOne(int rnum) throws Exception;
//	댓글 리스트
	public List<ReplyDTO> selectList(int bnum) throws Exception;
//	댓글 수정
	public void update(ReplyDTO rdto) throws Exception;
//	댓글 삭제
	public String delete(int rnum) throws Exception;
//	
	public void deleteBoard(int bnum) throws Exception;
	
//	댓글 좋아요, 싫어요
	public void updateLikecnt(int rnum, String id) throws Exception;
	public void updateDislikecnt(int rnum, String id) throws Exception;
}
