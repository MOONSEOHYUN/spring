package com.spring.food.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.food.dto.BoardDTO;

public interface FileService {
//	한개 파일
	public String fileUpload(MultipartFile file);
//	여러개의 파일 처리
	public List<String> fileUploadList(List<MultipartFile> files) throws Exception;
//	
	public void insert(BoardDTO bdto, List<MultipartFile> files) throws Exception;
//	파일 삭제
	public void delete(List<Integer> deletefiles) throws Exception;
//	게시물번호로 해당 게시물 삭제
	public void deleteBnum(int bnum) throws Exception;
}
