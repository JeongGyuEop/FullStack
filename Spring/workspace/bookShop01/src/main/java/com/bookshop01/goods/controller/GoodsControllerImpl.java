package com.bookshop01.goods.controller;

import java.util.ArrayList;
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

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;

import net.sf.json.JSONObject;


/*
	상품 상세 이미지를 표시하면서 빠른 메뉴(퀵 메뉴라고도함)에 최근 본 상품을 추가하여 표시합니다.
	빠른 메뉴에는 최대 네개 까지 상품을 저장할수 있습니다.
	
	빠른 메뉴에 최근 본 상품을 추가하고 표시 하는 과정
	1. 세션에 저장된 최근 본 상품 목록을 가져옵니다.
	2. 상품 목록에 저장된 상품 개수가 네 개 미만이고  방금 본 상품이 목록에 있는지 체크 합니다.
	3. 목록에 없으면 목록에 추가합니다.
	4. 다시 상품 목록을 세션에 저장합니다.
	5. 화면에 상품을 표시하는 quickMenu.jsp에서는 세션의 최근 본 상품 목록을 가져와 차례대로 표시 합니다.

*/

@Controller("goodsController")
@RequestMapping(value="/goods") // 1단계 요청 주소
public class GoodsControllerImpl extends BaseController   implements GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	//						2단계 요청주소
	@RequestMapping(value="/goodsDetail.do" ,method = RequestMethod.GET)
									//main.jsp에서 상품 클릭시 전달한 상품번호(상품아이디) 얻기 
	public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id, 
			                       HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		System.out.println("GoodsControllerImpl 클래스의 goodsDetail 메소드 시작");
		
		 // /goods/goodsDetail   웹브라우저에 보여줄 뷰의  주소 는 ViewIntercepter클래스에서 생성하여 request에 저장했었음!!
		String viewName=(String)request.getAttribute("viewName"); 
		
		//빠른메뉴에 표시 될 최근 본 상품 목록 정보가 session메모리영역에 있는지 없는지 판단하기 위해 session영역 얻기
		HttpSession session=request.getSession();
		
		//도서 상품 정보를 조회한후  Map으로 반환 받습니다. 
		Map goodsMap=goodsService.goodsDetail(goods_id); //상품 아이디 전달!
		
		ModelAndView mav = new ModelAndView(viewName); //   /goods/goodsDetail  저장
		mav.addObject("goodsMap", goodsMap);  //조회된 도서 상품 정보 저장 
		
		
		GoodsVO goodsVO=(GoodsVO)goodsMap.get("goodsVO");
		//상품 상페페이지에서 조회한 상품 정보를 빠른메뉴(퀵메뉴)에 표시하기 위해 !!! 
		//메소드 호출시!  조회된 도서상품 번호, 조회한 도서상품 정보!GoodsVO객체, 세션을  전달합니다.
		addGoodsInQuick(goods_id,goodsVO,session); 

		System.out.println("GoodsControllerImpl 클래스의 goodsDetail 메소드 종료");
		
		return mav;
	}
	
	//상품 상페페이지에서 조회한 상품 정보를 빠른메뉴(퀵메뉴)에 표시하기 위해 !!! 
	//메소드 호출시!  조회된 도서상품 번호, 조회한 도서상품 정보!GoodsVO객체, session을  전달받습니다. 
	
	//session에 최근본 상품정보(퀵메뉴에 보여질 상품정보)가 저장되어 있지 않으면  2번쨰 매개변수 GoodsVO goodsVO로 전달 받은
	//상품상세페이지 요청시 조회한 도서상품정보(GoodsVO객체)를 ArrayList배열을 생성하여 목록을 추가합니다.
	
//session에 또한 최근 본 상품정보가 저장된(퀵메뉴에 보여질 상품정보가 저장된) ArrayList배열이 저장되어 있고 4개 미만일 경우
	//ArrayList배열에서 GoodsVO객체를 하나씩얻어 상품상세피이지 요청시 조회한 도서상품 번호(현재 메소드의 첫번째 매개변수 String goods_id로 전달받은 번호)와 비교하여
	//이미 최근본 상품인지 아닌지를 확인합니다.  
	//이미 최근본 상품이라면  already_existed변수의값을 true로 설정하고 빠져 나갑니다.
	//상품상세페이지 요청시 본 상품이  최근본 상품이 아니라면 기존 최근 본 상품정보가 저장된(퀵메뉴에 보여질 상품정보가 저장된) ArrayList배열에 상품상세페이지 요청시 본 상품 정보를 추가합니다.
	
//session에 또한 최근 본 상품정보가 저장된(퀵메뉴에 보여질 상품정보가 저장된) ArrayList배열이 저장되어 있지 않으면?
	//상품상세페이지 요청시 본 상품정보(두번쨰 매개변수 GoodsVO goodsVO로 받는 상품정보)를  ArrayList배열 생성후 추가시킵니다.
	private void addGoodsInQuick(String goods_id,GoodsVO goodsVO,HttpSession session){

		System.out.println("GoodsControllerImpl 클래스의 addGoodsInQuick 메소드 시작");
		
		boolean already_existed=false;
		
		List<GoodsVO> quickGoodsList; 
		
		//세션에 저장된 최근 본 상품 목록을 가져 옵니다.
		quickGoodsList=(ArrayList<GoodsVO>)session.getAttribute("quickGoodsList");
		 
		//최근 본 상품이 있는 경우 
		if(quickGoodsList!=null){
			//최근 본 상품목록이 네개 미만인 경우
			if(quickGoodsList.size() < 4){
				
				for(int i=0; i<quickGoodsList.size();i++){		
					
					GoodsVO _goodsBean=(GoodsVO)quickGoodsList.get(i);
					
					//상품 목록을 가져와 이미 존재하는 상품인지 비교 합니다.
					if(goods_id.equals(_goodsBean.getGoods_id())){
						//이미 존재 할 경우 변수의 값을 true로 설정합니다.
						already_existed=true;
						break;
					}
				}
				
				//already_existed변수값이 false이면 상품 정보를 목록에 저장합니다.
				if(already_existed==false){
					quickGoodsList.add(goodsVO);
				}
			}
			
		}else{
			//최근 본 상품 목록이 없으면  생성하여  상품정보를 저장합니다.
			quickGoodsList =new ArrayList<GoodsVO>();
			quickGoodsList.add(goodsVO);
			
		}
		//최근 본 상품 목록을 세션에 저장합니다.
		session.setAttribute("quickGoodsList",quickGoodsList);
		//최근 본 상품목록에 저장된 상품 개수를 세션에 저장합니다.
		session.setAttribute("quickGoodsListNum", quickGoodsList.size());

		System.out.println("GoodsControllerImpl 클래스의 addGoodsInQuick 메소드 종료");
	}

/*	
참고.
@ResponseBody는 Spring MVC에서 사용되는 어노테이션 중 하나로, 
	Controller에서 처리한 데이터를 HTTP Response Body에 직접 작성해주는 역할을 합니다. 	
	이 어노테이션을 사용하면 Controller 메서드가 반환하는 값을 View가 아닌, Response Body에 직접 작성할 수 있습니다.

	예를 들어, Controller에서 JSON 형식의 데이터를 반환하고자 할 때, 
	@ResponseBody 어노테이션을 이용하여 JSON 형식으로 변환된 데이터를 HTTP Response Body에 작성할 수 있습니다. 
	이를 통해, 별도의 View 페이지 없이도 JSON 데이터를 반환할 수 있으며, 클라이언트 측에서 이를 바로 처리할 수 있습니다.

	또한, @ResponseBody 어노테이션을 사용하면, HTTP Response의 Content-Type 헤더 값도 자동으로 설정됩니다. 
	반환되는 데이터의 형식에 따라 자동으로 Content-Type을 설정하여, 
	클라이언트에서 받는 데이터가 어떤 형식인지 명확하게 알 수 있도록 합니다.	
	
*/	
//주제 : Ajax 이용해 입력한 검색어 관련  데이터 자동으로 표시하기	
	//header.jsp페이지에서 검색을 위해 검색키워드를 입력하고 검색요청을 했을때 
	//검색키워드가 포함된 도서상품 책 제목목록을 조회해서  JSONObject로 만들어서 다시~~웹브라우저 전송 하는 메소드 
																		  //전송하는 JSON데이터의 한글 인코딩을 지정 합니다. 
	@RequestMapping(value="/keywordSearch.do",method = RequestMethod.GET,produces = "application/text; charset=utf8")
	//JSON데이터를 웹브라우저로 출력합니다.             //검색할 키워드를 가져 옵니다. 
	public @ResponseBody String  keywordSearch(@RequestParam("keyword") String keyword,
			                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("GoodsControllerImpl 클래스의 keywordSearch 메소드 시작");
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//System.out.println(keyword);
		if(keyword == null || keyword.equals("")) {
		   return null ;
		}
		keyword = keyword.toUpperCase();
		
		//<input>에 검색 키워드를 입력하기 위해 키보드의 키를 눌렀다가 떼면 ~
		//입력된 키워드가 포함된 도서상품 책제목을 조회해서 가져옵니다.
	    List<String> keywordList =goodsService.keywordSearch(keyword);
	    
	    //JSONObject객체 -> {  } 객체 를 생성 
		JSONObject jsonObject = new JSONObject();
		//{ "keyword": keywordList }
		jsonObject.put("keyword", keywordList);
		
		// "{ 'keyword': keywordList }"	
	    String jsonInfo = jsonObject.toString();
	    
//	    {"keyword":["가장 빨리 만나는 자바9",
//	    			"자바로 배우는 리팩토링",
//	    			"자바 EE 디자인 패턴",
//	    			"유지 보수가 가능한 코딩의 기술-자바편",
//	    			"초보자를 위한 자바 프로그래밍",
//	    			"자바스크립트 배우기",
//	    			"Try! helloworld 자바스크립트"]}
	
	    System.out.println("GoodsControllerImpl 클래스의 keywordSearch 메소드의 JSON 데이터 : " + jsonInfo);

		System.out.println("GoodsControllerImpl 클래스의 keywordSearch 메소드 종료");
	    
	    return jsonInfo ;
	}
	
	
	// 검색버튼 누르면 !~ http://localhost:8090/bookshop01/goods/searchGoods.do 입력한 검색어 단어가 포함된 도서상품 검색 
	@RequestMapping(value="/searchGoods.do" ,method = RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord,
			                       HttpServletRequest request, HttpServletResponse response) throws Exception{

		System.out.println("GoodsControllerImpl 클래스의 searchGoods 메소드 시작");
		
		String viewName=(String)request.getAttribute("viewName"); //  /goods/searchGoods
		
		List<GoodsVO> goodsList=goodsService.searchGoods(searchWord); // 자바 EE 디자인 패턴
		
		ModelAndView mav = new ModelAndView(viewName); // /goods/searchGoods
		
		mav.addObject("goodsList", goodsList);

		System.out.println("GoodsControllerImpl 클래스의 searchGoods 메소드 종료");
		
		return mav;
		
	}
	

}




