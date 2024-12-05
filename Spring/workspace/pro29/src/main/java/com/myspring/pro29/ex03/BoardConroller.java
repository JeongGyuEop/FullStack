package com.myspring.pro29.ex03;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.pro29.ex01.TestController;

@RestController
@RequestMapping("/boards") //첫 번째 단계의 요청명을 매핑합니다.
public class BoardConroller {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardConroller.class);

	//http://localhost:8090/pro29/boards/all
	//GET방식으로 요청이 들어오면 모든 글의 정보를 조회 하는 메소드가 호출됨
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<ArticleVO>> listArticles(){
		
		logger.info("listArticles 메소드 호출됨!");
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		
		for(int i=0;  i<10;  i++) {
			ArticleVO vo = new ArticleVO();
			vo.setArticleNO(i);
			vo.setWriter("이순신"+i);
			vo.setTitle("안녕하세요"+i);
			vo.setContent("새 상품을 소개합니다." + i);
			
			list.add(vo);
		}
		
		return new ResponseEntity(list, HttpStatus.OK);	
	}
	
	
	//GET 방식으로 요청하면서 글번호를 전달하므로 글번호에 대한 글 하나의 정보 조회 하는 메소드
	//요청하는 주소 -> http://localhost:8090/pro29/boards/144
	@RequestMapping(value="/{articleNO}", method=RequestMethod.GET)
	public ResponseEntity<ArticleVO> findArticle(@PathVariable("articleNO")   Integer articleNO){
		
		logger.info("findArticle 메소드 호출됨!");
		
		ArticleVO vo = new ArticleVO(); //144번 글의 정보를 조회 해서 저장할 객체
		vo.setArticleNO(articleNO);
		vo.setWriter("홍길동");
		vo.setTitle("안녕하세요");
		vo.setContent("홍길동 글입니다.");
		
		return new ResponseEntity(vo, HttpStatus.OK);
	}
	
	//POST방식으로 요청하므로 요청시 JSON으로 전달되는 객체를  새글로 추가 하는 메소드 
	//AJAX 새글 추가 요청주소 -> http://localhost:8090/pro29/boards
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> addArticle(@RequestBody ArticleVO articleVO){
		/*
		  @RequestBody ArticleVO articleVO 매개변수	
			request객체의 body영역에 저장된  JSONObject객체내부의 속성 정보들을
			작성된 매개변수 ArticleVO articleVO에 맞는 
			new ArticleVO()객체를 생성후 각변수에 자동으로 저장합니다.
			 그리고 ArticleVO객체를 매개변수로 받습니다.		
		*/		                   
		ResponseEntity<String> resEntity = null; 
		
		try {
			logger.info("addArticle 메소드 호출됨");
			logger.info(articleVO.toString());
			logger.info("새글 글번호 : " + articleVO.getArticleNO());
			logger.info("새글 글제목 : " + articleVO.getTitle());
			logger.info("새글 글작성자 : " + articleVO.getWriter());
			logger.info("새글 글내용 : " + articleVO.getContent());
			
			resEntity = new ResponseEntity("ADD_SUCCEEDED",HttpStatus.OK);
			
		}catch (Exception e) {		
			resEntity = new ResponseEntity( e.getMessage(), HttpStatus.BAD_REQUEST);		
		}		
		return resEntity;
	}
		
	//PUT 방식으로 요청하므로 articleNO에 대한 글을 전달되는 JSON정보로 수정하는 메소드
	//AJAX 글 수정 요청주소 -> http://localhost:8090/pro29/boards/114   
	@RequestMapping(value="/{articleNO}", method=RequestMethod.PUT)
	public ResponseEntity<String> modArticle(@PathVariable("articleNO") Integer articleNO, 
											 @RequestBody ArticleVO articleVO){
		
		ResponseEntity<String> resEntity = null;
		
		try {
			 logger.info("modArticle메소드가 호출되었음");
			 logger.info("매개변수로 전달 받는 수정할 글 번호 정보 : " + articleNO.toString());
			 logger.info("매개변수로 전달 받는 ArticleVO객체의 정보 : " + articleVO.getArticleNO());
			 logger.info("매개변수로 전달 받는 ArticleVO객체의 정보 : " + articleVO.getContent());
			 logger.info("매개변수로 전달 받는 ArticleVO객체의 정보 : " + articleVO.getTitle());
			 logger.info("매개변수로 전달 받는 ArticleVO객체의 정보 : " + articleVO.getWriter());
			 logger.info("매개변수로 전달 받는 ArticleVO객체의 정보 : " + articleVO.getClass().getName());
			 
			 
			 
			 resEntity = new ResponseEntity<String>("MOD_SUCCEEDED", HttpStatus.OK);
			 
		} catch (Exception e) {
			
			resEntity = new ResponseEntity( e.getMessage(), HttpStatus.BAD_REQUEST);		
		}
		
		return resEntity;
	}
	
	
	
}










