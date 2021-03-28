package com.spring.food.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.spring.food.controller.BoardController;
import com.spring.food.dto.BoardDTO;
import com.spring.food.dto.PageDTO;
import com.spring.food.service.BoardService;

@Controller
@RequestMapping("board")
@SessionAttributes("pdto")	
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService bservice;

	
//	게시글 추가
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add() {
		return "board/add";
	}
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(BoardDTO bdto, List<MultipartFile> uploadfiles, HttpSession session, HttpServletRequest request) throws Exception {
		//세션에서 id 가져오기 (로그인하면 가져옴)
		bdto.setId((String)session.getAttribute("id"));
		bdto.setIp(request.getRemoteAddr());
		
		bservice.insert(bdto, uploadfiles);
		return "redirect:/board/";
	}
	
//	전체 조회
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(@ModelAttribute("pdto") PageDTO pdto) {
		return "board/list";
	}
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Map<String,Object> listdata(@ModelAttribute("pdto") PageDTO pdto) throws Exception{
		
		return bservice.selectList(pdto);
	}

//	상세 조회
	@RequestMapping(value="detail/{bnum}", method=RequestMethod.GET)
	public String detail(@PathVariable("bnum") int bnum, Model model, HttpSession session, HttpServletRequest request) throws Exception{
		String id=(String)session.getAttribute("id");
		if(id==null) {
			id=request.getRemoteAddr();
		}
		
//		조회수+1
		bservice.updateReadcnt(bnum,id);
				
		Map<String,Object> resultMap=bservice.selectOne(bnum);
		
		model.addAttribute("resultMap",resultMap);
		return "board/detail";
	}
	
//	좋아요
	@ResponseBody
	@RequestMapping(value="likecnt/{bnum}")
	public Map<String,Integer> likecnt(@PathVariable("bnum") int bnum, HttpSession session) throws Exception{
		String id=(String)session.getAttribute("id");
//		좋아요 update
		bservice.updateLikecnt(bnum, id);
		
		Map<String,Integer> resultMap=new HashMap<String, Integer>();
//		좋아요 select
		BoardDTO bdto=bservice.selectOneBoard(bnum);
		resultMap.put("likecnt", bdto.getLikecnt());
		resultMap.put("dislikecnt", bdto.getDislikecnt());
		
		return resultMap;
	}
	
//	싫어요
	@ResponseBody
	@RequestMapping(value="dislikecnt/{bnum}")
	public Map<String,Integer> dislikecnt(@PathVariable("bnum") int bnum, HttpSession session) throws Exception{
		String id=(String)session.getAttribute("id");
//		좋아요 update
		bservice.updateDislikecnt(bnum, id);
		
		Map<String,Integer> resultMap=new HashMap<String, Integer>();
//		좋아요 select
		BoardDTO bdto=bservice.selectOneBoard(bnum);
		resultMap.put("likecnt", bdto.getLikecnt());
		resultMap.put("dislikecnt", bdto.getDislikecnt());
		
		return resultMap;
	}
	
	
//	수정폼
	@RequestMapping(value="modify/{bnum}", method=RequestMethod.GET)
	public String modify(@PathVariable("bnum") int bnum, Model model) throws Exception{
		Map<String,Object> resultMap= bservice.selectOne(bnum);
		model.addAttribute("resultMap",resultMap);
		return "board/modify";
	}
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modify(BoardDTO bdto, @RequestParam(value="fileDelete", required = false) List<Integer> fileDelete, List<MultipartFile> uploadfiles) throws Exception{
		bservice.update(bdto, uploadfiles, fileDelete);
		
		return "redirect:/board/detail/"+bdto.getBnum();
	}
	
//	삭제
	@RequestMapping(value="delete/{bnum}")
	public String delete(@PathVariable("bnum") int bnum) throws Exception{
		bservice.delete(bnum);

		return "redirect:/board/";
	}
}
