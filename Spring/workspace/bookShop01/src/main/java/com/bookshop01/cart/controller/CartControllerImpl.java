package com.bookshop01.cart.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.cart.service.CartService;
import com.bookshop01.cart.vo.CartVO;
import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.member.vo.MemberVO;

@Controller("cartController")
@RequestMapping(value="/cart")
public class CartControllerImpl extends BaseController implements CartController{
	@Autowired
	private CartService cartService;
	@Autowired
	private CartVO cartVO;
	@Autowired
	private MemberVO memberVO;
	
	//   /cart/myCartList.do 장바구니 테이블에 저장된 상품 목록 조회요청을 받으면 호출되는 메소드로!
	//   장바구니 테이블에서 조회한  장바구니 목록과 상품 정보 목록을  Map에 저장합니다. 
	//   그리고 장바구니 목록을 표시하는 페이지에서 상품을  주문을 할 경우에 대비해 상품 정보를 미리 세션영역에 저장(바인딩) 합니다.
	@RequestMapping(value="/myCartList.do" ,method = RequestMethod.GET)
	public ModelAndView myCartMain(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		
		String viewName=(String)request.getAttribute("viewName");  //  /cart/myCartList
		
		ModelAndView mav = new ModelAndView(viewName); // 위 /cart/myCartList 뷰 주소 저장 	
		
		HttpSession session=request.getSession();		
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");	
		String member_id=memberVO.getMember_id();
	
		cartVO.setMember_id(member_id);	
		//장바구니 페이지에 표시할 상품 정보를 조회 후 Map담아 반환 해옵니다.
		//Map에 담긴 2개의 List배열은 다음과 같습니다. 
		//- 1. 로그인한 회원의 아이디를 이용해 장바구니 테이블에서 조회한 장바구니테이블에 저장되어 있는 상품정보를 조회한 
		//	   CartVO객체들이 저장된  List배열 데이터~!   "myCartList"
		//- 2. 로그인한 회원의 아이디를 이용해 장바구니 테이블에서 조회한 상품번호에 대한 상품을 도서상품 테이블과 도서이미지정보테이블을 조인해서
		//     조회한 GoodsVO객체들이 저장된 List배열 데이터~!	 "myGoodsList"
		Map<String ,List> cartMap=cartService.myCartList(cartVO);
		
		//장바구니 목록을 세션에 저장합니다.
		session.setAttribute("cartMap", cartMap);
		
		//mav.addObject("cartMap", cartMap);
		return mav;
	}
	
	
	//goodsDetail.jsp화면에서 장바구니 버튼을 클릭하여 장바구니에 상품을 추가해줘~ 
	//라는 요청 주소 cart/addGoodsInCart.do를 받았을때....
	@RequestMapping(value="/addGoodsInCart.do" ,method = RequestMethod.POST,produces = "application/text; charset=utf8")
	public  @ResponseBody String addGoodsInCart(//전송된 상품 번호를 받습니다.
												@RequestParam("goods_id") int goods_id,
			                                    HttpServletRequest request, 
			                                    HttpServletResponse response)  throws Exception{
		
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id=memberVO.getMember_id();
	
		cartVO.setGoods_id(goods_id);
		cartVO.setMember_id(member_id);
		
		//도서 상품 번호가 장바구니 테이블에 있는지 조회 합니다.  
		boolean isAreadyExisted=cartService.findCartGoods(cartVO);
		
		
		System.out.println("isAreadyExisted:"+isAreadyExisted);
		
		//도서 상품 번호가 , 이미 장바구니에 테이블에 있으면  이미 추가되었다는 메세지를 브라우저로 전송하고
		if(isAreadyExisted==true){
			return "already_existed";
		
		//도성 상품 번호가, 장바구니 테이블에 없으면? 장바구니 테이블에 추가(INSERT)시킵니다. 
		//추가 성공 메세지를 브라우저로 전송 합니다. 
		}else{
			cartService.addGoodsInCart(cartVO);
			return "add_success";
		}
	}
	
	
	
	
	//myCartList.jsp중앙화면(장바구니 목록페이지)에서 변경할 수량 정보를 입력하고 수량 변경 요청이 들어오면 호출되는 메소드 
	//cart/modifyCartQty.do
	@RequestMapping(value="/modifyCartQty.do" ,method = RequestMethod.POST)
	public @ResponseBody String  modifyCartQty(@RequestParam("goods_id") int goods_id,
			                                   @RequestParam("cart_goods_qty") int cart_goods_qty,
			                                    HttpServletRequest request, HttpServletResponse response)  throws Exception{
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id=memberVO.getMember_id();
		cartVO.setGoods_id(goods_id);
		cartVO.setMember_id(member_id);
		cartVO.setCart_goods_qty(cart_goods_qty);
		
		// 주문 하는 상품의 수량을 DB에 UPDATE하기 위해 메소드 호출!
		boolean result=cartService.modifyCartQty(cartVO);
		
		if(result==true){
		   return "modify_success"; //AJAX로 수량UDPATE요청한 myCartList.jsp로 전달!
		}else{
			  return "modify_failed";	
		}
		
	}
	//장바구니 번호를 매개변수로 받아서 받은 번호에 해당하는 상품을 삭제 
	@RequestMapping(value="/removeCartGoods.do" ,method = RequestMethod.POST)
	public ModelAndView removeCartGoods(@RequestParam("cart_id") int cart_id,
			                          HttpServletRequest request, HttpServletResponse response)  throws Exception{
		ModelAndView mav=new ModelAndView();
		cartService.removeCartGoods(cart_id);
		mav.setViewName("redirect:/cart/myCartList.do");
		return mav;
	}
}
