package com.spring.food.dao;

import com.spring.food.dto.ClassifyDTO;

public interface ClassifyDAO {
//	
	public void insert(ClassifyDTO cdto) throws Exception;
	
//	조회한 유저
	public ClassifyDTO selectOne(ClassifyDTO cdto) throws Exception;
	
//	좋아요, 싫어요 유저삭제
	public int delete(ClassifyDTO cdto) throws Exception;
}
