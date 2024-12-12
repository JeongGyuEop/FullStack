package net.skhu.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
컨트롤러 클래스는 웹브라우저의 URL 요청을 받아서,
웹서버에서 실행되는 자바 클래스이다.
웹브라우저가 웹서버에 어떤 URL을 요청을 하면,
그 URL에 해당하는 컨트롤러의 메소드가 자동으로 호출되어 실행된다.

@Controller
컨트롤러 클래스에 이 어노테이션(annotation)을 붙여주어야 한다.
이것을 붙여주지 않으면 컨트롤러 클래스가 실행되지 않는다.
404 Not Found 에러가 발생할 것이다.

액션 메소드
웹브라우저가 어떤 URL을 웹서버에 요청하면, 그 요청된 URL에 해당하는 컨트롤러의 어떤 메소드가 자동으로 호출된다.
이렇게 웹브라우저의 요청에 의해서 자동으로 호출되는 컨트롤러의 메소드를 액션 메소드라고 부른다.


@GetMapping 어노테이션과 액션 메소드 URL
액션 메소드에 붙은 @GetMapping("/") 어노테이션의 "/" 부분은
그 액션 메소드를 호출하기 위한 URL 이다.

http://localhost:8088/ URL을 웹브라우저가 웹서버에 요청하면,
웹서버에서 HomeController 컨트롤러 클래스의 index 액션 메소드가 자동으로 호출되어 실행된다.

자동으로 호출할 액션 메소드를 찾을 때 컨트롤러 클래스 이름이나 액션 메소드의 이름은 상관 없고,
@GetMapping 어노테이션에 등록된 URL만 일치하면 된다.
그리고 HTTP request method는 GET 이어야 한다.
만약 요청된 URL과 일치하는 액션 메소드가 없다면,
404 Not Found 에러가 발생할 것이다.
*/

@Controller
public class HomeController {

	//http://localhost:8088/
	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("message", "나쁜 아침");

		//뷰 파일에 전달할 데이터를 Model 객체에 넣는 코드이다.
        //전달되는 데이터의 이름은 "now" 이고, 값은 new Date() 객체이다.
		//(현재 날짜 시각 객체)
		model.addAttribute("now", new Date() );

		return "index";
		/*
        컨트롤러 액션 메소드의 리턴 값
        컨트롤러의 액션 메소드는 문자열을 리턴한다.
        컨트롤러의 액션 메소드가 리턴하는 문자열은, 뷰(View) 파일의 경로이다.
        컨트롤러의 액션 메소드가 리턴된 후, 뷰 파일이 실행된다.
        액션 메소드가 리턴한 경로의 뷰 파일이 실행된다.

        뷰 파일은 src/main/resources/templates/ 아래에 있어야 한다.

        액션 메소드의 리턴값이 return "index"; 이면,
        뷰 파일은 src/main/resources/templates/index.html 경로에 있어야 한다.
        만약 이 경로에 뷰 파일이 있지 않다면, 뷰 템플릿 파일을 찾을 수 없다는 에러가 발생할 것이다.
           view template file cannot be found
           view template file cannot be resolved

        액션 메소드의 리턴값이 return "home/index"; 이면,
        뷰 파일은 src/main/resources/templates/home/index.html 경로에 있어야 한다.

    	*/
	}


}















