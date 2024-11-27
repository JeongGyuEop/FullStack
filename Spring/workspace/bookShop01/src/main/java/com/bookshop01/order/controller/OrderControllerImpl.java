package com.bookshop01.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.member.vo.MemberVO;
import com.bookshop01.order.service.OrderService;
import com.bookshop01.order.vo.OrderVO;


/*
	상품주문(구매하기)은 로그인을 한상태에서만 가능합니다.
	만약 로그인하지 않은 상태에서 구매하기를 클릭하면 alert('로그인 후 구매가능합니다'); 경고창을 띄워주고
	로그인창으로 이동합니다.  그래서 로그인 과정을 수행한 후 주문페이지로 이동하도록 해야합니다.
	
	상품 주문을 처리 하는 과정은 아래와 같습니다.
	1. 구매하기를 클릭하면 웹브라우저에서 전송된 주문 상품정보를 ArrayList배열에 저장해  Session에 저장한 후 
	     주문 페이지로 이동합니다.
    2. 주문페이지 에서  수령자와 배송지 정보를 입력받습니다. 
             최종 주문시 컨트롤러에서 미리 Session에 저장된 주문 상품 목록을
             가져와 전송된 수령자 정보와 배송지 정보를 합칩니다.
    3. 주문정보를 합친 최종 ArrayList배열을 SQL문으로 전달하여 주문을 처리합니다.  
    
      
    --------------------------------------------------------
    
    장바구니 상품 주문하기
    설명 : 장바구니에 담긴 상품들을 한꺼번에 주문하는 기능을 구현 해 봅시다.
              상품여러개를 주문하는 SQL문 역시 한개를 주문할때와 동일합니다.   
      
     장바구니에 담긴 상품들을을 선택한 후 상품을 주문하는 과정
     1. 장바구니 페이지를 나타내기 전 장바구니에 추가된 상품정보를 미리 세션에 저장합니다. 
     2. 장바구니 페이지에서 주문할 상품을 선택한 후 주문할 상품번호와 각 상품 주문 수량을 배열에 담아 컨트롤러에 전송합니다. 
     3. 컨트롤러에서는 전송된 상품번호와 세션에 저장된 상품들의 상품번호를 비교해 같으면 상품 정보를 주문 목록의 OrderVO객체의 변수에 설정 합니다.
     4. 전송된 각 상품별 주문 수량을  OrderVO객체의 변수에 설정합니다. 
     5. 다시 OderVO객체를 myOrderList키를 이용해 세션에 저장한 ArrayList배열에 저장합니다.           
*/

//   /order/orderAllCartGoods.do

@Controller("orderController")
@RequestMapping(value="/order")
public class OrderControllerImpl extends BaseController implements OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderVO orderVO;
	
	
	//goodsDetail.jsp페이지에서 구매하기 버튼을 눌러 주문요청한 주소   /order/orderEachGoods.do
	@RequestMapping(value="/orderEachGoods.do" ,method = RequestMethod.POST)
	public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO,
			                           HttpServletRequest request, 
			                           HttpServletResponse response)  throws Exception{
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		session=request.getSession();
		
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn"); //true 또는 false를 반환 받음.  true -> 로그인함
																  //                         false -> 미로그인 
		
		String action=(String)session.getAttribute("action");
	
		
		//로그인을 하지 않았다면 먼저 로그인 후 주문을 처리하도록 주문 정보와 주문 페이지 요청 URL을 session에 저장합니다. 
		if(isLogOn==null || isLogOn==false){
			session.setAttribute("orderInfo", _orderVO);
			session.setAttribute("action", "/order/orderEachGoods.do");
			return new ModelAndView("redirect:/member/loginForm.do");
			
		//로그인을 하고 구매하기를 눌렀다면? 
	    //로그인을 하지 않고 구매하기를 눌렀을때...
		//session에  주문을 처리하도록 주문 정보와 주문페이지 요청 주소를 저장 해 놓았으므로	
		}else{
		 
			 //로그인을 하지 않고 구매하기를 눌렀을때 session에 저장 했던   주문 페이지  요청 주소가 존재하면?
			 if(action!=null && action.equals("/order/orderEachGoods.do")){
				 
				orderVO=(OrderVO)session.getAttribute("orderInfo"); //session에서 주문정보(OderVO객체)를 가져오고 
				
				session.removeAttribute("action");//session에 저장되어 있던  주문페이지 요청 주소  (action,"/order/orderEachGoods.do")를 제거 함. 
			 
			 //로그인을 하지 않고 구매하기를 눌렀을때 session에 저장 했던   주문 페이지  요청 주소가 존재 하지 않으면?
			 }else {
				
				 //goodsDetail.jsp페이지에서 구매하기 버튼을 눌러 주문요청한 현재 메소드의 매개변수로 전달 받은 OrderVO객체(주문 정보)를 가져온다.
				 orderVO=_orderVO;
			 }
		 }
		
		//향후  tiles_order.xml파일에서 중앙화면의 주소가 결정됨 
		String viewName=(String)request.getAttribute("viewName"); //  /order/orderEachGoods
		ModelAndView mav = new ModelAndView(viewName);
		
		// OrderVO객체(주문 정보)를 저장할 ArrayList배열 생성
		List myOrderList=new ArrayList<OrderVO>();
		//브라우저에서 전달한 주문 정보를 ArrayList배열에 저장 
		myOrderList.add(orderVO);

		//로그인 요청 당시 ~ 입력한 아이디, 비밀번호를 이용해 회원정보를 조회한 정보가 저장된 MemeberVO객체는 session에 저장되어 있었으므로
		//로그인 했다면~~  MemberVO객체를 꺼내 온다.
		MemberVO memberInfo=(MemberVO)session.getAttribute("memberInfo");
		
		//주문 상품 정보와  주문자 정보를 session영역에 저장(바인딩) 후  주문 창으로 전달합니다. 
		session.setAttribute("myOrderList", myOrderList); //OrderVO객체(주문 정보)가 저장된  ArrayList배열 정보 
		session.setAttribute("orderer", memberInfo); //로그인한 주문자 정보
		return mav;
	}
	
	
	// myCartList.jsp화면(장바구니 목록 화면)에서  주문할 상품을 체크박스를 클릭해 선택하고  각 상품의 주문 수량을 입력하여 
    // 주문하기 버튼을 클릭하여  배송지 정보와 결제 정보를 최종 입력후  최종결제 요청을 하는 중앙화면을 요청하는 주소!
	//  /order/orderAllCartGoods.do 요청 주소를 받으면 호출되는 메소드 
	@RequestMapping(value="/orderAllCartGoods.do" ,method = RequestMethod.POST)
	public ModelAndView orderAllCartGoods(
										  //체크박스에 선택하 상품 수량을 배열로 받습니다. 
										  @RequestParam("cart_goods_qty")  String[] cart_goods_qty,
										  HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		
		String viewName=(String)request.getAttribute("viewName");  //  /order/orderAllCartGoods
		ModelAndView mav = new ModelAndView(viewName);
		
		//미리 세션에 저장한 장바구니 상품 목록을 가져옵니다. 
		HttpSession session=request.getSession();
		Map cartMap=(Map)session.getAttribute("cartMap");
		List<GoodsVO> myGoodsList=(List<GoodsVO>)cartMap.get("myGoodsList");
		
		
		List myOrderList=new ArrayList<OrderVO>();
				
		//미리 세션에 저장된 로그인한 구매자 정보를 얻자
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		
		//장바구니에 저장된 구매할 상품 개수만큼 반복합니다. 
		for(int i=0; i<cart_goods_qty.length;i++){
			
			//문자열로 결합되어 전송된 상품 번호와 주문 수량을  split()메소드를 이용해 분리 합니다. 
			String[] cart_goods=cart_goods_qty[i].split(":");
			
			for(int j = 0; j< myGoodsList.size();j++) {
				
				//장바구니 목록(myGoodsList배열)에서 차례대로 GoodsVO객체를 가져옵니다.
				GoodsVO goodsVO = myGoodsList.get(j);
				
				//GoodsVO객체의 goods_id변수에 저장되어 있는? 구매할 상품 번호를 가져옵니다. 
				int goods_id = goodsVO.getGoods_id();
				
				//전송된 상품 번호와  GoodsVO객체에서 꺼내온 상품번호와 같으면 
				//주문하는 상품이므로  OrderVO객체를 생성한 후 상품 정보를 OrderVO객체에 설정합니다.
				//그리고 다시 myOrderList참조변수를 가진 ArrayList배열에 OrderVO객체를 저장하게 됩니다.`
				if(goods_id==Integer.parseInt(cart_goods[0])) {
					OrderVO _orderVO=new OrderVO();
					String goods_title=goodsVO.getGoods_title();
					int goods_sales_price=goodsVO.getGoods_sales_price();
					String goods_fileName=goodsVO.getGoods_fileName();
					_orderVO.setGoods_id(goods_id);
					_orderVO.setGoods_title(goods_title);
					_orderVO.setGoods_sales_price(goods_sales_price);
					_orderVO.setGoods_fileName(goods_fileName);
					_orderVO.setOrder_goods_qty(Integer.parseInt(cart_goods[1]));
					myOrderList.add(_orderVO);
					break;
				}
			}
		}
		//장바구니 목록에서 주문하기 위해 선택한 상품만  myOrderList참조변수명을 가진  ArrayList배열에 저장한 후 세션에 담습니다. 
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("orderer", memberVO);
		return mav;
	}	
	
	//팝업창에서 최종결제하기 버튼을 눌렀을때 
	// 최종 결제하기 버튼을 눌러  최종 결제 요청한 주소  /order/payToOrderGoods.do
	@RequestMapping(value="/payToOrderGoods.do" ,method = RequestMethod.POST)
										//주문창(goodsDetail.jsp)에서 입력한 상품 수령자 정보와 배송지 정보를 Map에 바로 저장후 매개변수로 받습니다.
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> receiverMap,
			                            HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		
		String viewName=(String)request.getAttribute("viewName");  // /order/payToOrderGoods
		ModelAndView mav = new ModelAndView(viewName);
		
		HttpSession session=request.getSession();
		MemberVO memberVO=(MemberVO)session.getAttribute("orderer");
		String member_id=memberVO.getMember_id();
		String orderer_name=memberVO.getMember_name();
		String orderer_hp = memberVO.getHp1()+"-"+memberVO.getHp2()+"-"+memberVO.getHp3();
		
		List<OrderVO> myOrderList=(List<OrderVO>)session.getAttribute("myOrderList");
		
		//주문창에서 입력한 수령자 정보와 배송지 정보를 주문 상품 정보 목록과 합칩니다. 
		for(int i=0; i<myOrderList.size();i++){
			OrderVO orderVO=(OrderVO)myOrderList.get(i);
			orderVO.setMember_id(member_id);
			orderVO.setOrderer_name(orderer_name);
			orderVO.setReceiver_name(receiverMap.get("receiver_name"));
			
			orderVO.setReceiver_hp1(receiverMap.get("receiver_hp1"));
			orderVO.setReceiver_hp2(receiverMap.get("receiver_hp2"));
			orderVO.setReceiver_hp3(receiverMap.get("receiver_hp3"));
			orderVO.setReceiver_tel1(receiverMap.get("receiver_tel1"));
			orderVO.setReceiver_tel2(receiverMap.get("receiver_tel2"));
			orderVO.setReceiver_tel3(receiverMap.get("receiver_tel3"));
			
			orderVO.setDelivery_address(receiverMap.get("delivery_address"));
			orderVO.setDelivery_message(receiverMap.get("delivery_message"));
			orderVO.setDelivery_method(receiverMap.get("delivery_method"));
			orderVO.setGift_wrapping(receiverMap.get("gift_wrapping"));
			orderVO.setPay_method(receiverMap.get("pay_method"));
			orderVO.setCard_com_name(receiverMap.get("card_com_name"));
			orderVO.setCard_pay_month(receiverMap.get("card_pay_month"));
			orderVO.setPay_orderer_hp_num(receiverMap.get("pay_orderer_hp_num"));	
			orderVO.setOrderer_hp(orderer_hp);	
			myOrderList.set(i, orderVO);
		}//end for
		
		//주문 정보를 DB에 추가 합니다. 
	    orderService.addNewOrder(myOrderList);
	    
	    //주문 완료  결과창에 주문자 정보를 표시할수 있도록 저장후 전달 하게 합니다. 
		mav.addObject("myOrderInfo",receiverMap);
		
		//주문 완료 결과창에  주문 상품목록을 표시할수 있도록 저장후 전달 하게 합니다. 
		mav.addObject("myOrderList", myOrderList);
		
		return mav;
	}
	

}





