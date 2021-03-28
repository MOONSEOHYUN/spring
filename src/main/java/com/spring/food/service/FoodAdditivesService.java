package com.spring.food.service;

import java.util.List;
import java.util.Map;

import com.spring.food.dto.FoodAdditivesDTO;

public interface FoodAdditivesService {
//	식품첨가물 파싱
	public void FoodAdditives() throws Exception;
//	식품첨가물 리스트
	public List<FoodAdditivesDTO> foodAddSelectList() throws Exception;	

}
