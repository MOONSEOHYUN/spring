package com.spring.food.service;

import com.spring.food.dto.ClassifyDTO;

public interface ClassifyService {
//	좋아요, 싫어요 구분
	public void insert(int gubun, int num, String id) throws Exception;
//	
	public ClassifyDTO selectOne(int gubun, int num, String id) throws Exception;
	
//	조회한 유저 삭제 (좋아요/싫어요-1을 위해)
	public int delete(int gubun, int num, String id) throws Exception;
}
