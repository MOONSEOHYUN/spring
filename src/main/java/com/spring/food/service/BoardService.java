package com.spring.food.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.spring.food.dto.BoardDTO;
import com.spring.food.dto.PageDTO;

public interface BoardService {
	public int insert(BoardDTO bdto, List<MultipartFile> files) throws Exception;
	public int update(BoardDTO bdto, List<MultipartFile> files, List<Integer> deletefiles) throws Exception;
	public int delete(int bnum) throws Exception;
	public Map<String,Object> selectList(PageDTO pdto) throws Exception;
	public Map<String,Object> selectOne(int bnum) throws Exception;
	
//	조회수
	public void updateReadcnt(int bnum, String id) throws Exception;
//	좋아요
	public void updateLikecnt(int bnum, String id) throws Exception;
//	싫어요
	public void updateDislikecnt(int bnum, String id) throws Exception;
//	게시물만 조회
	public BoardDTO selectOneBoard(int bnum) throws Exception;
}
