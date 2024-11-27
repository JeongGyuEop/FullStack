package com.bookshop01.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;


//@Controller("mainController"): 해당 클래스를 컨트롤러로 지정하며, 빈의 이름을 "mainController"로 설정한다.
//@EnableAspectJAutoProxy: AOP를 사용하기 위한 어노테이션으로, 이를 설정하면 자동으로 AOP 프록시를 생성하여 적용한다.
@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController {
	
	//GoodsServiceImpl.java파일에 작성 해 놓은 
	//public class GoodsServiceImpl implements GoodsService {} 의
	//<bean>을 자동 주입 합니다.
	@Autowired
	private GoodsService goodsService;

	
	
	// http://localhost:8090/bookShop01/main/main.do 입력하여 메인화면 요청시
	// 메인화면 중앙에 보여줄 베스트셀러, 신간, 스테디 셀러를 조회한후  Map에 저장하여  JSP로 전달합니다.
	@RequestMapping(value= "/main/main.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//세션영역에 side_menu속성값 user를 저장 해 놓으면
		//첫 메인 화면 main.jsp의 왼쪾사이드메뉴 모습은 비로그인된 상태로 접속한 사이드 왼쪾메뉴를 나타내기 위해 쓰입니다.
		//참고. 그밖에 세연영역에 저장되는 값 확인을 위해 common폴더에 만들어져 있는 side.jsp를 열어 확인 해보세요
		HttpSession session;
		session=request.getSession();
		session.setAttribute("side_menu", "user");
				
		ModelAndView mav=new ModelAndView();
		
		//ViewNameIntercetor클래스의 preHandle메소드 내부에서 request에 바인딩한 뷰 주소 /main/main 을!!!!!!!
		//request.getAttribute("viewName"); 으로 얻어 
		//views폴더/main폴더/main.jsp메인 페이지로 조회된 베스트셀러 , 신간, 스테디셀러 도서 정보를 보내기 위해  ModelAndView객체에 저장 (바인딩)
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		//베스트 셀러, 신간, 스테디 셀러  도서정보를 조회 해 Map에 저장받기 위해  서비스의 메소드 호출!
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods();
		mav.addObject("goodsMap", goodsMap); //ModelAndView에 main.jsp중앙화면에 보여줄 조회된 도서 정보가 저장된 Map을 저장 (바인딩)
		
		  System.out.println("-------------------------------------------------------------------");
	      System.out.println("-------------------------------------------------------------------");
	      System.out.println("흐름2.");
	      System.out.println("MainController컨트롤러 클래스 내부의 main메소드 호출되었으며");
	      System.out.println("main메소드 내부에서 ViewNameInterceptor클래스에서 request에 바인딩한 ");
	      System.out.println("뷰 주소 " + viewName + " 을 request.getAttribute('viewName'); 으로 얻음");
	      System.out.println("ModelAndView에 " + viewName + " 뷰 주소 저장 함.");
	      System.out.println(" 세션 메모리 영역에 session.setAttribute('side_menu',user); 로 저장 함.");
	      System.out.println(" 'side_menu'속성 값에 따라 화면 왼쪽에 표시되는 메뉴 항목을 다르게 하기 위해 저장 세션에 저장 하였음");
	      System.out.println(" Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods(); 이코드에 의해 베스드셀러 신간, 스테디셀러 정보를 조회해 Map에 저장 후 반환 받습니다.");
	      System.out.println(" 마지막으로 ModelAndView에 추가로~ mav.addObject('goodsMap', goodsMap); 코드에 의해 조회된 Map을 저장시킵니다.");
	      System.out.println(" return mav; 구문에 의해 ViewNameInterceptor클래스의 postHandle 메소드가 호출됩니다.");
	      
	      
	      System.out.println("-------------------------------------------------------------------");
	      System.out.println("-------------------------------------------------------------------");
	      
	      System.out.println("흐름3. 그 후 tiles_main.xml 파일에 작성한 <definition name=/main/main ...> 태그의 name속성값 /main/main이 ModelAndView에 저장 했던 /main/main과 일치하면 해당 뷰템플릿을 메인화면으로 보여줍니다.");
	
		
		return mav;
		
		
		
		
	}
}





