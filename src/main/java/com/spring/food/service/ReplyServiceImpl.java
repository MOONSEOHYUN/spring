package com.spring.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.food.dao.ReplyDAO;
import com.spring.food.dto.ClassifyDTO;
import com.spring.food.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDAO rdao;
	@Autowired
	private ClassifyService cservice;

	@Override
	public void insert(ReplyDTO rdto) throws Exception {
//		글순서: 부모의 글순서+1
		rdto.setRestep(rdto.getRestep()+1);
//		기존 등록된 글순서+1
		rdao.updateRestep(rdto);
//		글의 레벨: 부모의 레벨+1
		rdto.setRelevel(rdto.getRelevel()+1);
		
		rdao.insert(rdto);
	}

	@Override
	public List<ReplyDTO> selectList(int bnum) throws Exception {
		return rdao.selectList(bnum);
	}
	
	
	@Override
	public void update(ReplyDTO rdto) throws Exception {
		rdao.update(rdto);
	}

	@Override
	public String delete(int rnum) throws Exception {
//		댓글의 자식이 존재한다면 삭제 불가
		ReplyDTO rdto=rdao.selectOne(rnum);
		int cnt=rdao.selectReplyCnt(rdto);
		if(cnt>0) {
			return "댓글의 댓글 존재, 삭제 불가";
		}
		
		rdao.delete(rnum);
		return "삭제 완료";
	}

	@Override
	public void deleteBoard(int bnum) throws Exception {
		rdao.deleteBoard(bnum);
	}

//	좋아요+1
	@Override
	public void updateLikecnt(int rnum, String id) throws Exception {
//		댓글 싫어요 유저 삭제
		int cnt=cservice.delete(5, rnum, id);
//		삭제가 됐다면 다시 
		if(cnt>0) {
			rdao.updateDislikecntMinus(rnum);
		}
		
//		기존에 조회한 유저가 있는지 조회
		ClassifyDTO cdto=cservice.selectOne(4, rnum, id);
		//유저가 있다면 좋아요수 안올라감
		if(cdto!=null) return ;	
				
//		만약 조회 사용자가 없다면
//		likeUser테이블에 insert
		cservice.insert(4, rnum, id);
//		reply테이블에 있는 likecnt수를 update
		rdao.updateLikecnt(rnum);

	}
//	싫어요+1
	@Override
	public void updateDislikecnt(int rnum, String id) throws Exception {
//		좋아요->싫어요
		int cnt=cservice.delete(4, rnum, id);
//		삭제가 됐다면 다시 
		if(cnt>0) {
			rdao.updateLikecntMinus(rnum);
		}
		
		
		ClassifyDTO cdto=cservice.selectOne(5, rnum, id);
		//유저가 있다면 싫어요수 안올라감
		if(cdto!=null) return ;	
				
//		만약 조회 사용자가 없다면
//		likeUser테이블에 insert
		cservice.insert(5, rnum, id);
//		board테이블에 있는 dislikecnt수를 update
		rdao.updateDislikecnt(rnum);
	}

	@Override
	public ReplyDTO selectOne(int rnum) throws Exception {
		return rdao.selectOne(rnum);
	}
}
