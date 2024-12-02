package com.bookshop01.goods.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;

@Repository("goodsDAO")
public class GoodsDAOImpl  implements GoodsDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override                                  
	public List<GoodsVO> selectGoodsList(String goodsStatus ) throws DataAccessException {
		
		System.out.println("GoodsDAOImpl 클래스의 selectGoodsList 메소드 시작");

		System.out.println("GoodsDAOImpl 클래스의 selectKeywordSearch 메소드에서 mapper.goods.selectGoodsList 매퍼파일 호출");
		List<GoodsVO> goodsList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsList",goodsStatus);

		System.out.println("GoodsDAOImpl 클래스의 selectGoodsList 메소드 종료");
	    return goodsList;	
     
	}
	
//주제 : Ajax 이용해 입력한 검색어 관련  데이터 자동으로 표시하기
	//<input>에 검색 키워드를 입력하기 위해 키보드의 키를 눌렀다가 떼면 ~
	//입력된 키워드가 포함된 도서상품 책제목을 조회해서 가져옵니다.
	@Override
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException {
		
		System.out.println("GoodsDAOImpl 클래스의 selectKeywordSearch 메소드 시작");

		System.out.println("GoodsDAOImpl 클래스의 selectKeywordSearch 메소드에서 mapper.goods.selectKeywordSearch 매퍼파일 호출");
	   List<String> list=(ArrayList)sqlSession.selectList("mapper.goods.selectKeywordSearch",keyword);

		System.out.println("GoodsDAOImpl 클래스의 selectKeywordSearch 메소드 종료");
	   return list; 
	}
	
	@Override
	public ArrayList selectGoodsBySearchWord(String searchWord) throws DataAccessException{
		
		System.out.println("GoodsDAOImpl 클래스의 selectGoodsBySearchWord 메소드 시작");

		System.out.println("GoodsDAOImpl 클래스의 selectKeywordSearch 메소드에서 mapper.goods.selectGoodsBySearchWord 매퍼파일 호출");
		ArrayList list=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsBySearchWord",searchWord);

		System.out.println("GoodsDAOImpl 클래스의 selectGoodsBySearchWord 메소드 종료");
		 return list;
	}
	
	//상품 아이디로 상세페이지 화면에 보여질  도서상품 하나의 정보를 조회 !!!
	@Override
	public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException{
		
		System.out.println("GoodsDAOImpl 클래스의 selectGoodsDetail 메소드 시작");

		System.out.println("GoodsDAOImpl 클래스의 selectKeywordSearch 메소드에서 mapper.goods.selectGoodsDetail 매퍼파일 호출");
		GoodsVO goodsVO=(GoodsVO)sqlSession.selectOne("mapper.goods.selectGoodsDetail",goods_id);

		System.out.println("GoodsDAOImpl 클래스의 selectGoodsDetail 메소드 종료");
		return goodsVO;
	}
	
	//상품 아이디로 상세피이지 화면에 보여질 도서상품 하나의 이미지 정보 여러개를 조회!!!
	@Override
	public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException{
		
		System.out.println("GoodsDAOImpl 클래스의 selectGoodsDetailImage 메소드 시작");

		System.out.println("GoodsDAOImpl 클래스의 selectKeywordSearch 메소드에서 mapper.goods.selectGoodsDetailImage 매퍼파일 호출");
		List<ImageFileVO> imageList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsDetailImage",goods_id);

		System.out.println("GoodsDAOImpl 클래스의 selectGoodsDetailImage 메소드 종료");
		return imageList;
	}
	
}




