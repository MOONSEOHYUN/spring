package com.spring.food.dao;

import java.util.List;

import com.spring.food.dto.FoodAdditivesDTO;

public interface FoodAdditivesDAO {
//	식품첨가물 추가
	public void foodAddInsert(FoodAdditivesDTO fadto) throws Exception;
//	식품첨가물 존재 여부
	public FoodAdditivesDTO foodAddSelectOne(String pcode) throws Exception;
//	식품첨가물 리스트
	public List<FoodAdditivesDTO> foodAddSelectList() throws Exception;
}
