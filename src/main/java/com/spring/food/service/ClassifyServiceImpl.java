package com.spring.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.food.dao.ClassifyDAO;
import com.spring.food.dto.ClassifyDTO;

@Service
public class ClassifyServiceImpl implements ClassifyService{

	@Autowired
	private ClassifyDAO cdao;
	
//	구분하는 것들 따로 뺀 메소드
	public ClassifyDTO setCDTO(int gubun, int num, String id) {
//		매개변수 gubun은 테이블의 gubun하고 다른 것
		
		ClassifyDTO cdto=new ClassifyDTO();
		if(gubun==1) {
			cdto.setGubun("1");	//게시물
			cdto.setNum(num);		//게시물 번호
			cdto.setId(id);		//게시물 조회한 유저
			cdto.setLikegubun("0");	//게시물 조회
		}else if(gubun==2) {
			cdto.setGubun("1");	//게시물
			cdto.setNum(num);		//게시물 번호
			cdto.setId(id);		//게시물 조회한 유저
			cdto.setLikegubun("1");	//좋아요
		}else if(gubun==3) {
			cdto.setGubun("1");	//게시물
			cdto.setNum(num);		//게시물 번호
			cdto.setId(id);		//게시물 조회한 유저
			cdto.setLikegubun("2");	//싫어요
		}else if(gubun==4) {
			cdto.setGubun("2");	//댓글
			cdto.setNum(num);		//댓글 번호
			cdto.setId(id);		//게시물 조회한 유저
			cdto.setLikegubun("1");	//좋아요
		}else if(gubun==5) {
			cdto.setGubun("2");	//댓글
			cdto.setNum(num);		//댓글 번호
			cdto.setId(id);		//게시물 조회한 유저
			cdto.setLikegubun("2");	//싫어요
		}
		
		return cdto;
	}
	
	@Override
	public void insert(int gubun, int num, String id) throws Exception{
		ClassifyDTO cdto=setCDTO(gubun, num, id);
		cdao.insert(cdto);
		
	}

	@Override
	public ClassifyDTO selectOne(int gubun, int num, String id) throws Exception {
		ClassifyDTO cdto=setCDTO(gubun, num, id);	
		
		return cdao.selectOne(cdto);
	}

	@Override
	public int delete(int gubun, int num, String id) throws Exception {
		ClassifyDTO cdto=setCDTO(gubun, num, id);
		
		
		return cdao.delete(cdto);
	}

}
