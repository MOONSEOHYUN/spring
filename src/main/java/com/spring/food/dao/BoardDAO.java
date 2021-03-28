package com.spring.food.dao;

import java.util.List;

import com.spring.food.dto.BoardDTO;
import com.spring.food.dto.PageDTO;

public interface BoardDAO {
//	게시물 추가
	public int insert(BoardDTO bdto) throws Exception;
//	게시물 수정
	public int update(BoardDTO bdto) throws Exception;
//	게시물 삭제
	public int delete(int bnum) throws Exception;
//	게시물 전체 조회
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception;
//	제시물 상세 조회
	public BoardDTO selectOne(int bnum) throws Exception;
	
//	조회수
	public void updateReadcnt(int bnum) throws Exception;
//	좋아요
	public void updateLikecnt(int bnum) throws Exception;
//	싫어요
	public void updateDislikecnt(int bnum) throws Exception;
	
//	좋아요-1
	public void updateLikecntMinus(int bnum) throws Exception;
//	싫어요-1
	public void updateDislikecntMinus(int bnum) throws Exception;
	
	//전체 건수 조회
	public int selectTotCnt(PageDTO pdto);
}
