package com.bookshop01.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop01.cart.dao.CartDAO;
import com.bookshop01.cart.vo.CartVO;
import com.bookshop01.goods.vo.GoodsVO;

@Service("cartService")
@Transactional(propagation=Propagation.REQUIRED)
public class CartServiceImpl  implements CartService{
	@Autowired
	private CartDAO cartDAO;
	
	
//장바구니 목록 조회!	
	//고객이 장바구니 담기 버튼을 클릭하면 장바구니 테이블에는 해당 상품의 번호만 저장됩니다.
	//따라서 장바구니 페이지에 상품정보를 같이 표시 하려면 장바구니테이블에 저장된 상품 번호를 이용해
	//상품정보를 따로 조회한 후  장바구니 페이지로 전달 해서 표시해야 합니다.
	//여기서는 회원 id로 상품번호를 조회한 후  이를 이용해 다시 상품 상세 정보를 조회 합니다.
	//그리고 조회한 장바구니 정보와  상품 정보를 Map에 저장한후 컨트롤러로 반환합니다. 
	public Map<String ,List> myCartList(CartVO cartVO) throws Exception{
		
		Map<String,List> cartMap = new HashMap<String,List>();
		
//장바구니 페이지에 표시할 장바구니테이블에 저장된 상품 정보를 조회 합니다. 
		List<CartVO> myCartList=cartDAO.selectCartList(cartVO);

//t_shopping_cart(장바구니 테이블)에서 조회한 주문상품 정보들		
//	CART_ID GOODS_ID	MEMBER_ID	CART_GOODS_QTY		CREDATE
//		3		395		lee				1				23/04/06  CartVO
//		1		342		lee				9				23/04/06  CartVO
//		2		339		lee				5				23/04/06  CartVO
//		4		397		lee				1				23/04/06  CartVO
		
			
		//장바구 테이블에서 조회된 상품이 없는 경우 컨트롤러로 NULL을 반환합니다. 
		if(myCartList.size()==0){ 
			return null;
		}
//장바구니 페이지에 표시할  도서 상품 정보를 조회합니다. 
//		 장바구니 테이블에 담긴 상품번호를 이용해 도서상품 테이블과 도서이미지정보가저장되는 테이블 ! 
//		 2개의 테이블을 JOIN해서  SELECT 조회
		List<GoodsVO> myGoodsList=cartDAO.selectGoodsList(myCartList);
		
//		그리고 조회한 장바구니 정보와  상품 정보를 Map에 저장한후 컨트롤러로 반환합니다.	
		cartMap.put("myCartList", myCartList);
		cartMap.put("myGoodsList",myGoodsList);		
		return cartMap;
	}

	public boolean findCartGoods(CartVO cartVO) throws Exception{
		 return cartDAO.selectCountInCart(cartVO);
		
	}	
	public void addGoodsInCart(CartVO cartVO) throws Exception{
		cartDAO.insertGoodsInCart(cartVO);
	}
	
	//매개변수로 전달 받는 데이터(CartVO객체의 정보 : 주문자의 정보, 주문상픔의 수정할 수량정보)를 매개변수 로 받아서
	//DB의 테이블 정보를 수정 명령 하는 메소드 
	public boolean modifyCartQty(CartVO cartVO) throws Exception{
	
		int result = cartDAO.updateCartGoodsQty(cartVO);
		
		if(result == 1) { //수량 수정 성공
			return true;
		}
		return false;
		 
	}
	public void removeCartGoods(int cart_id) throws Exception{
		cartDAO.deleteCartGoods(cart_id);
	}
	
}






