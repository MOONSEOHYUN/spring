package com.spring.food.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.food.dao.BoardFileDAO;
import com.spring.food.dto.BoardDTO;
import com.spring.food.dto.BoardFileDTO;
import com.spring.food.service.FileService;

@Service
public class FIleServiceImpl implements FileService{
	private static final Logger logger = LoggerFactory.getLogger(FIleServiceImpl.class);
	
	@Autowired
	String saveDir;
	@Autowired
	FileService fservice;
	@Autowired
	BoardFileDAO bfdao;
	
	
	@Override
	public String fileUpload(MultipartFile file){
		if(file.isEmpty()) {
			return "";
		}
		String filename=System.currentTimeMillis()+file.getOriginalFilename();
		logger.info(filename);
		
		File f=new File(saveDir,filename);
		try {
			file.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return filename;
	}

//	여러개의 파일 처리
	@Override
	public List<String> fileUploadList(List<MultipartFile> files) throws Exception{
		List<String> flist=new ArrayList<String>();
		for(MultipartFile file:files) {
			String filename=fileUpload(file);
			if(!filename.equals("")) {
				flist.add(filename);
			}
		}
		return flist;
	}

	@Override
	public void insert(BoardDTO bdto, List<MultipartFile> files) throws Exception{
//		파일디렉토리에 저장
		List<String> flist=fservice.fileUploadList(files);
//		파일db에 저장
		for(String filename:flist) {
			BoardFileDTO bfdto=new BoardFileDTO();
			bfdto.setBnum(bdto.getBnum());
			bfdto.setFilename(filename);
			bfdao.insert(bfdto);
		}
	}

	@Override
	public void delete(List<Integer> deletefiles) throws Exception{
		if(deletefiles==null) {
			return ;
		}
		for(int fnum:deletefiles) {
			bfdao.delete(fnum);
		}
	}

	@Override
	public void deleteBnum(int bnum) throws Exception {
		bfdao.deleteBnum(bnum);
	}
}
