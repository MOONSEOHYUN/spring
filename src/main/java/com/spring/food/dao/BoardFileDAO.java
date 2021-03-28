package com.spring.food.dao;

import java.util.List;

import com.spring.food.dto.BoardFileDTO;

public interface BoardFileDAO {
//	파일 추가
	public void insert(BoardFileDTO bfdto) throws Exception;
//	
	public List<BoardFileDTO> selectList(int bnum) throws Exception;
//	파일 삭제
	public void delete(int fnum);
//	
	public void deleteBnum(int bnum);
}
