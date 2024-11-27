package com.bookshop01.cart.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop01.cart.vo.CartVO;
import com.bookshop01.goods.vo.GoodsVO;

@Repository("cartDAO")
public class CartDAOImpl  implements  CartDAO{
	@Autowired
	private SqlSession sqlSession;
	
	//로그인한 회원 아이디를 이용해  장바구니 테이블에 담긴 상품 정보를 조회 후 반환 하는 메소드 
	public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException {
		
		List<CartVO> cartList =(List)sqlSession.selectList("mapper.cart.selectCartList",cartVO);
		return cartList;
	}
	
//	 장바구니 테이블에 담긴 상품번호를 이용해 도서상품 테이블과 도서이미지정보가저장되는 테이블 ! 
//	  2개의 테이블을 JOIN해서  SELECT 조회
	public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException {
		
		List<GoodsVO> myGoodsList;
		myGoodsList = sqlSession.selectList("mapper.cart.selectGoodsList",cartList);
		return myGoodsList;
	}
	public boolean selectCountInCart(CartVO cartVO) throws DataAccessException {
		String  result =sqlSession.selectOne("mapper.cart.selectCountInCart",cartVO);
		return Boolean.parseBoolean(result);
	}

	public void insertGoodsInCart(CartVO cartVO) throws DataAccessException{
		int cart_id=selectMaxCartId();
		cartVO.setCart_id(cart_id);
		sqlSession.insert("mapper.cart.insertGoodsInCart",cartVO);
	} 
	
	//로그인한 주문자가 주문을 위해 하나의 상품 수량정보를 UPDATE시키기 위해 호출되는 메소드 
	public int updateCartGoodsQty(CartVO cartVO) throws DataAccessException{
		
		 return sqlSession.update("mapper.cart.updateCartGoodsQty",cartVO);
	}
	
	public void deleteCartGoods(int cart_id) throws DataAccessException{
		sqlSession.delete("mapper.cart.deleteCartGoods",cart_id);
	}

	private int selectMaxCartId() throws DataAccessException{
		int cart_id =sqlSession.selectOne("mapper.cart.selectMaxCartId");
		return cart_id;
	}

}
