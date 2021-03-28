package com.spring.food.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.food.controller.ReplyController;
import com.spring.food.dto.ReplyDTO;
import com.spring.food.service.ReplyService;

@RestController		
@RequestMapping("reply")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Autowired
	private ReplyService rservice;
	
//	댓글 추가
	@RequestMapping(value="/", method=RequestMethod.POST, produces="application/text; charset=utf-8")
	public String Add(@RequestBody ReplyDTO rdto, HttpSession session, HttpServletRequest request) throws Exception{
		rdto.setId((String)session.getAttribute("id"));
		rdto.setIp(request.getRemoteAddr());
		
		rservice.insert(rdto);
		
		return "댓글 저장 완료";
	}
	
//	댓글 수정
	@RequestMapping(value="/", method=RequestMethod.PUT, produces="application/text; charset=utf-8")
	public String update(@RequestBody ReplyDTO rdto) throws Exception{
		
		rservice.update(rdto);
		
		return "댓글 수정 완료";
	}
	
//	댓글 삭제
	@RequestMapping(value="/{rnum}", method=RequestMethod.DELETE, produces="application/text; charset=utf-8")
	public String delete(@PathVariable("rnum") int rnum) throws Exception{
		
		return rservice.delete(rnum);
	}
	
	
//	댓글 리스트
	@RequestMapping(value="/{bnum}", method=RequestMethod.GET)
	public List<ReplyDTO> rlist(@PathVariable("bnum") int bnum) throws Exception{
		
		return rservice.selectList(bnum);
	}
	
	//댓글 좋아요
	@RequestMapping(value="ReplyLikecnt/{rnum}", method=RequestMethod.GET)
	public Map<String,Integer> ReplyLikecnt(@PathVariable("rnum") int rnum, HttpSession session) throws Exception{
		String id=(String)session.getAttribute("id");
	//	좋아요 update
		rservice.updateLikecnt(rnum, id);
		
		Map<String,Integer> resultMap=new HashMap<String, Integer>();
	//	좋아요 select
		ReplyDTO rdto=rservice.selectOne(rnum);
		resultMap.put("likecnt", rdto.getLikecnt());
		resultMap.put("dislikecnt", rdto.getDislikecnt());
		
		return resultMap;
	}
	
	//댓글 싫어요
	@RequestMapping(value="ReplyDislikecnt/{rnum}", method=RequestMethod.GET)
	public Map<String,Integer> ReplyDislikecnt(@PathVariable("rnum") int rnum, HttpSession session) throws Exception{
		String id=(String)session.getAttribute("id");
	//	좋아요 update
		rservice.updateDislikecnt(rnum, id);
		
		Map<String,Integer> resultMap=new HashMap<String, Integer>();
	//	좋아요 select
		ReplyDTO rdto=rservice.selectOne(rnum);
		resultMap.put("likecnt", rdto.getLikecnt());
		resultMap.put("dislikecnt", rdto.getDislikecnt());
		
		return resultMap;
	}
}
