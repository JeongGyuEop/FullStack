package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.pro29.HomeController;

/*
	주제 : @RestController 어노테이션 기호를 이용해 REST API 기능 구현하기 

	내용 -  @RestControoler어노테이션 을 이용해 컨트롤러에서  웹브라우저로  
	            기본형 데이터 ,  VO객체의 속성값, Map에 저장된 데이터를  전송해 보겠습니다.

	
  @RestController  어노테이션 기호 기능에 대하여...
  
  	- 위 어노테이션 기호는 클래스에 적용되며 , 해당 클래스의 모든 메소드가 RESTful웹서비스의 엔드포인트로 작동을 합니다.
  	- 위 어노테이션 기호가 적용된 클래스의 모든 메소드는  HTTP요청을 처리하고  HTTP응답을 반환합니다.
  	  예를 들어, HTTP GET 요청을 처리하는 메소드는  @GetMapping 어노테이션 기호를 사용하여 정의할수 있습니다.
  	 또한 , @PostMapping, @PutMapping, @PatchMapping,  @DeleteMapping 등과 같은 어노테이션 을 사용하여 
  	 다양한 HTTP 메소드를 처리하는 메소드를 정의할수 있습니다.
  	 
    - @RestController 어노테이션 기호는 @Controller 어노테이션 기호와   @ResponseBody어노테이션 기호의 기능을 조합하여 작성된것으로 볼수 있다.
      @Controller는 해당 클래스가 컨트롤러임을 나타내고,
      @ResponseBody는  해당 메소드가  HTTP 응답 본문(body)을 나타낸다는 것을 나타냅니다.
      @RestController 어노테이션 기호를 사용하면  응답 본문 처리를 더  간단 하게 작성할수 있습니다.
  
  
참고.
 @ResponseBody
 - 위 어노테이션 기호는 @Controller어노테이션 기호와 함꼐 사용 되며,
   @ResponseBody 어노테이션 기호가 작성된 메소드는 반환값을  HTTP 응답의 본문으로 변환합니다.
      예를 들어  반환값이  객체인 경우, Spring은 해당 객체를 JSON 객체나 , XML 객체 형식으로 변환하여  HTTP 응답의본문으로 반환합니다. 

*/

//JSP같은 뷰이름을 반환 하는 것이 아니고 , 
//JSON , XML과 같은 데이터를 웹브라우저로 전송하는 컬트롤러에 이 어노테이션 기호를 설정합니다.
@RestController
@RequestMapping("/test/*")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	//http://[도메인]/[컨텍스트 패스]/[리소스 경로]/[리소스 식별자]

	//http://localhost:8090/pro29/test/hello 주소를 입력하여 GET으로 요청!   전송된 문자열을 표시 할것임
	//Hello REST!!
	@RequestMapping("/hello")
	public String hello() {
		return "Hello REST!!";
	}
	
	//http://localhost:8090/pro29/test/member 주소를 입력하여 GET으로 요청! 
	//MemberVO의 변수값들을 JSONObject객체 형태로 변환후 웹브라우저로 전송!
	@RequestMapping("/member")
    public MemberVO member() {
    	
		MemberVO vo = new MemberVO();
				 vo.setId("hong");
				 vo.setPwd("1234");
				 vo.setName("홍길동");
				 vo.setEmail("hong@test.com");
		
		return vo; 
		
    }
	
	//List배열같은 컬렉션을 JSON으로 만들어 웹브라우저에 전송 해보겠습니다.
	//http://localhost:8090/pro29/test/membersList
	@RequestMapping("/membersList")
	public List<MemberVO> listMembers(){
		
		//ArrayList배열  생성
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		//MemberVO객체를 10개 생성 하여  ArrayList배열에 저장
		for(int i=0;  i<10;  i++) {
			 
			 MemberVO vo = new MemberVO();
			 vo.setId("hong" + i);
			 vo.setPwd("1234" + i);
			 vo.setName("홍길동" + i);
			 vo.setEmail("hong"+i+"@test.com");
			
			 list.add(vo);
		}//for
			
		return list; //ArrayList배열을 JSONArray배열 형태로 자동 변환후 웹브라우저로 전송(반환) 합니다.
	}

/*	
	ResponseEntity 클래스를 사용해 웹브라우저로 응답하기
	
	 - @RestController어노테이션은 별도의 View를 제공하지 않은채 데이터를 웹브라우저로 전달하므로
	     전달 과정에서 예외가 발생할수 있습니다.
	     예외에 대해  좀더 세밀한 제어가 필요한 경우 ResponseEntity클래스를 사용하면 됩니다.
		
	     예를 들어 안드로이드 기반의 어떤 모바일 쇼일몰 앱 이 있는데, 
	     명절 기간에 주문자가 한꺼번에 몰리면서  톰캣 서버에 부하가 걸렸다고 가정합시다
	    일정 시간이 지나도 주문이 처리 되지 않으면 서버에서 ResponseEntity클래스에 Http 상태 코드를 설정하여 앱으로 전송하도록 합니다.
	    그러면 앱에서 Http 상태코드를 인식할수 있는 기능을 이용해 주문 상태나 예외 발생 메세지를 알려 줄수 있습니다. 

그룹들
서버 오류 응답 코드들
      500  - INTERNAL_SERVER_ERROR 상수   :  처리할수 없는 내부 오류가 발생했다는 의미 
      501  - NOT_IMPLEMENTED 상수 : 요청 메소드는 서버가 지원하지 않거나 처리할수 없다는 의미
      503  - SERVICE_UNAVAILABLE 상수 : 서버는 요청을 처리할 준비가 되지 않았다는 의미
성공 응답 코드들
	  200 -  OK 상수 : 요청이 성공적으로 완료되었다는 의미 
	  201 -  CREATED 상수 : 요청이 성공적이었으며 그결과로 새로운 리소스가 생성되었다는 의미
	  202 -  ACCEPTED 상수 : 요청은 수신 했지만 그에 응하여 행동할수 없는다는 의미
정보 응답 코드들
	  100 
	  101
리다이렉션 메세지
	  300
	  301 
	  302
	  303
클라이언트 오류 응답 
	  400 - BAD_REQUEST 상수 : 이응답은 잘못된 문법으로 인해 서버가 요청을 이해할수 없다는 의미 
	  401 - UNAUTHORIZED 상수 : 인증되지 않았다는 의미 
	  403 - FORBIDDEN 상수 : 클라이언트가 콘텐트에 접근할 권리를 가지고 있지 않다는 의미 
	  404 - NOT_FOUND 상수 : 서버는 요청 받은 리소스를 찾을수 없다는 의미 
	  
	  
	  
    
     
*/	
	@RequestMapping("/membersList2")
	public  ResponseEntity<List<MemberVO>>   listMembers2(){
		
		//ArrayList배열  생성
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		//MemberVO객체를 10개 생성 하여  ArrayList배열에 저장
		for(int i=0;  i<10;  i++) {
			 
			 MemberVO vo = new MemberVO();
			 vo.setId("hong" + i);
			 vo.setPwd("1234" + i);
			 vo.setName("홍길동" + i);
			 vo.setEmail("hong"+i+"@test.com");
			
			 list.add(vo);
		}//for
			
		return  new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR); 
		//위ArrayList배열의 정보를 JSONArray배열과 JSONObject를 자동으로 만들어서 웹브라우저(클라이언트)에 바로 리턴해서 보여준다.
		/*
		[{"id":"hong0","pwd":"12340","name":"홍길동0","email":"hong0@test.com"},
		 {"id":"hong1","pwd":"12341","name":"홍길동1","email":"hong1@test.com"},
		 {"id":"hong2","pwd":"12342","name":"홍길동2","email":"hong2@test.com"},
		 {"id":"hong3","pwd":"12343","name":"홍길동3","email":"hong3@test.com"},
		 {"id":"hong4","pwd":"12344","name":"홍길동4","email":"hong4@test.com"},
		 {"id":"hong5","pwd":"12345","name":"홍길동5","email":"hong5@test.com"},
		 {"id":"hong6","pwd":"12346","name":"홍길동6","email":"hong6@test.com"},
		 {"id":"hong7","pwd":"12347","name":"홍길동7","email":"hong7@test.com"},
		 {"id":"hong8","pwd":"12348","name":"홍길동8","email":"hong8@test.com"},
		 {"id":"hong9","pwd":"12349","name":"홍길동9","email":"hong9@test.com"}]
		*/
	}	
	
	
	@RequestMapping("/membersMap")
	public Map<Integer, MemberVO> membersMap(){
		
		Map<Integer, MemberVO> map = new HashMap<Integer, MemberVO>();
		
		//MemberVO객체를 10개 생성 하여  Map배열에 저장
		for(int i=0;  i<10;  i++) {
			 
			 MemberVO vo = new MemberVO();
			 vo.setId("hong" + i);
			 vo.setPwd("1234" + i);
			 vo.setName("홍길동" + i);
			 vo.setEmail("hong"+i+"@test.com");
			
			 map.put(i, vo);
			 
		}//for
			
		return map; //map배열을 JSONObject객체 형태로 자동 변환후 웹브라우저로 전송(반환) 합니다.
	}
	
/*	
	@PathVariable 어노테이션 사용하기 
	- 어노테이션 을 사용하면 브라우저에서 요청주소로 전달된 요청한값(매개변수)를 가져올수 있습니다. 
	
	예) 웹브라우저 주소창에 입력하여 전달 되는 전체 데이터
	   http://localhost:8090/pro29/test/notice/112  입력후 112를  요청한값으로 사용
	
		{num} <- 112 
		{num} 으로 지정된 112를  @PathVariable("num") int num 매개변수에 자동 할당 되어 요청한 값 112를 받을수 있습니다. 
*/	
	@RequestMapping(value="/notice/{num}", method=RequestMethod.GET)
	public int notice(@PathVariable("num") int num) throws Exception{
		
		return num; //웹브라우저로 전송 !
	}
	
	
	
/*	
	
@PathVariable은 Spring Framework에서 사용되는 어노테이션 중 하나입니다. 
이 어노테이션은 HTTP 요청 URL에서 변수를 추출하여 메소드 파라미터와 연결합니다. 
즉, @PathVariable 어노테이션을 사용하여 URL 경로에 포함된 변수를 메소드 파라미터로 전달할 수 있습니다.

예를 들어, 다음과 같은 URL 경로가 있다고 가정해보겠습니다: /users/{userId}. 
이 URL 경로에서 {userId} 부분은 변수를 의미합니다. 
이 변수는 @PathVariable 어노테이션을 사용하여 메소드 파라미터로 전달할 수 있습니다. 
따라서, @PathVariable 어노테이션을 사용하여 다음과 같은 메소드를 작성할 수 있습니다:	
	
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
	    User user = userService.getUserById(userId);
	    return ResponseEntity.ok(user);
	}

	
	위의 코드에서 @PathVariable 어노테이션을 사용하여 
	userId 변수를 메소드 파라미터로 전달합니다. 
	이렇게 하면 URL 경로에서 추출한 userId 변수를 사용하여 
	userService에서 해당하는 User 객체를 검색하고 반환합니다.

	@PathVariable 어노테이션은 기본값이 required = true이므로, 
	URL 경로에서 변수를 추출하지 못하면 예외가 발생합니다. 
	하지만 required = false로 설정하면 
	해당 변수가 URL 경로에서 누락되더라도 예외를 발생시키지 않고 
	null 값을 파라미터로 전달합니다.
	
	
*/	
	
	/*
	    주제: @RequestBody를  이용해  JSON 데이터를 MemberVO객체로 자동 변환합니다.
	    
	  @RequestBody 어노테이션 기호는 스프링프레임워크에서 사용되는 어노테이션 기호중 하나로,
	  Http 요청의 바디(body)에 포함된 데이터를 java객체로 변환해 줍니다.
	  
	   Request객체의   Body영역의 내용을  java객체로 변환 하려면
	   @RequestBody 어노테이션 기호를 사용하여  컨트롤러의 매개변수앞에 작성해서 적용 해야 합니다.
	     이어노 테이션 기호는 매개변수로 전달 되는 요청 데이터를 자동으로 매개변수의 타입을 보고 그 객체를 생성해서  각변수에 자동 바인딩 해서 
	     전달 됩니다.
	   
	    @PostMapping("/info")
	    public ResponseEntity<void> createUser(@RequestBody User user){
	         
	    }
	    
	    위 코드의 설명 : 
	    createUser메소드의 매개변수 user는  @RequestBody어노테이션으로 인해  request객체의 body영역에 포함된 JOSONObject객체 데이터를 
	    자동으로 변환하여  User객체로 받아옵니다.
	    
	    
	*/
	@RequestMapping(value="/info", method=RequestMethod.POST)
	public void modify(@RequestBody MemberVO  vo) {
					   //JSONObject "{ id:'park', name:'박지성', pwd:'1234', email:'park@test.com'}"문자열로  전송된 데이터를
					   //MemberVO 객체의 변수에 자동으로 설정해 주고 
					   //MemberVO객체를 매개변수로 반환받음
		
		logger.info(vo.toString());
		
		
	}
	
	
	
}
















