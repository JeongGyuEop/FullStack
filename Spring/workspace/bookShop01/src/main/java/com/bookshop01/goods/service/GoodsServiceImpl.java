package com.bookshop01.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop01.goods.dao.GoodsDAO;
import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;

@Service("goodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsDAO goodsDAO;
	
	public Map<String,List<GoodsVO>> listGoods() throws Exception {
		

		System.out.println("GoodsServiceImpl 클래스의 listGoods 메소드 시작");
		
		Map<String,List<GoodsVO>> goodsMap=new HashMap<String,List<GoodsVO>>();
		
		List<GoodsVO> goodsList=goodsDAO.selectGoodsList("bestseller");
		goodsMap.put("bestseller",goodsList);
		
		
		goodsList=goodsDAO.selectGoodsList("newbook");
		goodsMap.put("newbook",goodsList);
		
		goodsList=goodsDAO.selectGoodsList("steadyseller");
		goodsMap.put("steadyseller",goodsList);
		

		System.out.println("GoodsServiceImpl 클래스의 listGoods 메소드 종료");
		
		return goodsMap;
	}
	
	//상품아이디를 매개변수로 전달 받아 도서상품정보 + 도서이미지정보를  GoodsDAOImpl의 메소드로 조회 명령 하는 메소드
	public Map goodsDetail(String _goods_id) throws Exception {
		
		System.out.println("GoodsServiceImpl 클래스의 goodsDetail 메소드 시작");
		
		Map goodsMap=new HashMap();
		
		GoodsVO goodsVO = goodsDAO.selectGoodsDetail(_goods_id); //도서 상품 조회
		
		goodsMap.put("goodsVO", goodsVO);
		
		List<ImageFileVO> imageList =goodsDAO.selectGoodsDetailImage(_goods_id); //도서상품의 이미지 정보 조회 
		
		goodsMap.put("imageList", imageList);

		System.out.println("GoodsServiceImpl 클래스의 goodsDetail 메소드 종료");
		
		return goodsMap;
	}
	
//주제 : Ajax 이용해 입력한 검색어 관련  데이터 자동으로 표시하기	
	//<input>에 검색 키워드를 입력하기 위해 키보드의 키를 눌렀다가 떼면 ~
	//입력된 키워드가 포함된 도서상품 책제목을 조회해서 가져옵니다.
	public List<String> keywordSearch(String keyword) throws Exception {

		System.out.println("GoodsServiceImpl 클래스의 keywordSearch 메소드 시작");
		
		List<String> list=goodsDAO.selectKeywordSearch(keyword);
		
		System.out.println("GoodsServiceImpl 클래스의 keywordSearch 메소드 종료");
		return list;
	}
	
	public List<GoodsVO> searchGoods(String searchWord) throws Exception{

		System.out.println("GoodsServiceImpl 클래스의 searchGoods 메소드 시작");
		
		List goodsList=goodsDAO.selectGoodsBySearchWord(searchWord);
		
		System.out.println("GoodsServiceImpl 클래스의 searchGoods 메소드 종료");
		return goodsList;
	}
	
	
}
