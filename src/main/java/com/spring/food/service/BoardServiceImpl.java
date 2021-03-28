package com.spring.food.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.food.dao.BoardDAO;
import com.spring.food.dao.BoardFileDAO;
import com.spring.food.dto.BoardDTO;
import com.spring.food.dto.BoardFileDTO;
import com.spring.food.dto.ClassifyDTO;
import com.spring.food.dto.PageDTO;
import com.spring.food.service.FileService;
import com.spring.food.service.ClassifyService;
import com.spring.food.service.ReplyService;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO bdao;
	@Autowired
	private FileService fservice;
	@Autowired
	private BoardFileDAO bfdao;
	@Autowired
	private ClassifyService cservice;
	@Autowired
	private ReplyService rservice;
	
//	게시물+파일
	@Transactional	//board랑 boardfile 묶어서 같이 insert
	@Override
	public int insert(BoardDTO bdto, List<MultipartFile> files) throws Exception {
		bdao.insert(bdto);
//		게시물 파일 저장
		fservice.insert(bdto,files);
		
		return 0;
	}

	@Override
	public int update(BoardDTO bdto, List<MultipartFile> files, List<Integer> deletefiles) throws Exception {
//		게시물 수정
		bdao.update(bdto);
//		파일 삭제
		fservice.delete(deletefiles);
//		파일 추가
		fservice.insert(bdto, files);
		
		return 0;
	}
	
	@Transactional
	@Override
	public int delete(int bnum) throws Exception {
//		파일삭제
		fservice.deleteBnum(bnum);
//		좋싫 삭제
//		댓글삭제
		rservice.deleteBoard(bnum);
//		게시물삭제
		bdao.delete(bnum);
		
		return 0;
	}

	@Override
	public Map<String,Object> selectList(PageDTO pdto) throws Exception {
		Map<String,Object> resultMap=new HashMap<String, Object>();
		//페이징 처리 계산
		int curPage = pdto.getCurPage(); //현재페이지번호
		int perPage = pdto.getPerPage(); //한페이지당 게시물수
		int perBlock = pdto.getPerBlock();  //페이지 블럭의 수
		
		//시작번호
		int startNo = (curPage-1)*perPage+1;
		//끝번호
		int endNo = startNo + perPage - 1;
		
		//시작페이지
		int startPage = curPage-((curPage-1)%perBlock);
		//끝페이지
		int endPage = startPage + perBlock-1;
		
		//전체게시물수
		int totCnt = bdao.selectTotCnt(pdto);
		//전체페이지수 
		int totPage = totCnt / perPage;
		//만약에 나머지가 있으면 +1
		if (totCnt%perPage != 0) {
			totPage ++;
		}
		//끝페이지 재수정
		if (endPage>totPage) endPage=totPage; 
		
		//pdto에 셋
		pdto.setStartNo(startNo);
		pdto.setEndNo(endNo);
		pdto.setStartPage(startPage);
		pdto.setEndPage(endPage);
		pdto.setTotPage(totPage);
		
		resultMap.put("blist", bdao.selectList(pdto));	//게시물 리스트
		resultMap.put("pdto", pdto);	//pageDTO
		
		return resultMap;
	}

	@Override
	public Map<String,Object> selectOne(int bnum) throws Exception {
//		게시글 조회
		BoardDTO bdto=bdao.selectOne(bnum);
//		파일 조회
		List<BoardFileDTO> bflist= bfdao.selectList(bnum);
		
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("bdto",bdto);
		resultMap.put("bflist",bflist);
		
		return resultMap;
	}

	@Override
	public void updateReadcount(int bnum, String id) throws Exception {
//		기존에 조회한 유저가 있는지 조회
		ClassifyDTO cdto=cservice.selectOne(1, bnum, id);
		//유저가 있다면 
		if(cdto!=null) return ;	
				
//		만약 조회 사용자가 없다면
//		1.insert
//		있다면
//		2.조회수+1

//		1.게시물 조회
//		2.게시물 좋아요
//		3.게시물 싫어요
//		4.댓글 좋아요
//		5.댓글 싫어요
		cservice.insert(1, bnum, id);
		
		bdao.updateReadcount(bnum);
	}
	
//	게시물만 조회(좋아요, 싫어요를 위한)
	@Override
	public BoardDTO selectOneBoard(int bnum) throws Exception {
		return bdao.selectOne(bnum);
	}

//	좋아요+1
	@Override
	public void updateLikecnt(int bnum, String id) throws Exception {
//		싫어요 유저 삭제
//		(조회/좋아요/싫어요는 유저당 한번밖에 못하는데 그것을 구분해 주는 게 likeUserMapper에 insert
//		좋아요/싫어요 하면 insert가 되고(정보 추가) 취소하면 나중에 다시 좋/싫을 할 수 있도록 정보를 삭제해줘야 함
		int cnt=cservice.delete(3, bnum, id);
//		삭제가 됐다면 다시 
		if(cnt>0) {
			bdao.updateDislikecntMinus(bnum);
		}
		
//		기존에 조회한 유저가 있는지 조회
		ClassifyDTO cdto=cservice.selectOne(2, bnum, id);
		//유저가 있다면 좋아요수 안올라감
		if(cdto!=null) return ;	
				
//		만약 조회 사용자가 없다면
//		likeUser테이블에 insert
		cservice.insert(2, bnum, id);
//		board테이블에 있는 likecnt수를 update
		bdao.updateLikecnt(bnum);

	}
//	싫어요+1
	@Override
	public void updateDislikecnt(int bnum, String id) throws Exception {
//		좋아요->싫어요
		int cnt=cservice.delete(2, bnum, id);
//		삭제가 됐다면 다시 
		if(cnt>0) {
			bdao.updateLikecntMinus(bnum);
		}
		
		
		ClassifyDTO cdto=cservice.selectOne(3, bnum, id);
		//유저가 있다면 싫어요수 안올라감
		if(cdto!=null) return ;	
				
//		만약 조회 사용자가 없다면
//		likeUser테이블에 insert
		cservice.insert(3, bnum, id);
//		board테이블에 있는 dislikecnt수를 update
		bdao.updateDislikecnt(bnum);
	}
}
