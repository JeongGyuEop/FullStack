package net.skhu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
@RestController
이 어노테이션이 붙은 컨트롤러를 레스트 컨트롤러라고 부른다.

레스트 컨트롤러 액션 메소드의 리턴값은 데이터이다.
그 데이터가 웹브라우저에 전송된다.
그 데이터는 JSON 형태의 텍스트로 변환되어 웹브라우저에 전송된다.

JSON (Javascript Object Notation)

Java 객체를 JSON 형태의 객체로 변환할 때,
Java 객체 속성이 JSON 객체의 속성으로 변환된다.


@Controller
이 어노테이션이 붙은 컨트롤러는 그냥 컨트롤러라고 부른다.

이 컨트롤러의 액션메소드의 리턴값은 뷰(view) 템플릿 파일의 경로이다.
액션메소드가 리턴한 후, 그 경로의 뷰가 실행된다.
뷰의 실행결과 출력이 웹브라우저에 전송된다.
즉 HTML 태그들이 웹브라우저에 전송된다.

*/

import net.skhu.dto.Product;
@RestController
@RequestMapping("first")
public class FirstController {

//http://localhost:8088/first/test1 URL을
//웹브라우저가 웹서버에 요청하면,
//웹서버에서 FirstController 컨트롤러 클래스의 test1 액션 메소드가 자동으로 호출되어 실행된다.
//참고. 자동으로 호출할 액션 메소드를 찾을 때 컨트롤러 클래스 이름이나 액션 메소드의 이름은 상관 없고,
//@RequestMapping, @GetMapping 어노테이션에 등록된 URL만 일치하면 된다.


	@GetMapping("test1")
	public String test1() {

		return "안녕하세요";
	}

	//http://localhost:8088/first/test2
	@GetMapping("test2")
	public String[]  test2() {

		return new String[] {"월", "화", "수","목","금","토","일"};
		//["월", "화", "수", "목", "금", "토", "일"]
		//test2 액션 메소드가 리턴한 String 배열이 JSON 포멧으로 웹브라우저에 전송되었다.
		//웹브라우저에 보이는 내용이 Stirng 배열 JSON 포맷이다.
	}

	//http://localhost:8088/first/test3
	@GetMapping("test3")
	public Product test3() {
		return new Product("맥주", 2000);

		//{"name":"맥주","unitCost":2000}
		//test3 액션 메소드가 리턴한 Product 객체가 JSON 포멧으로 웹브라우저에 전송되었다.
		//웹브라우저에 보이는 내용이 객체 한 개의 JSON 포맷이다.

		//여기서 "name",  "unitCost" 부분은
		//Project Java 객체의 멤버 변수 이름이 아니고, 속성명이다.
		//즉 get 메소드 이름이다.
	}

	//http://localhost:8088/first/test4
	@GetMapping("test4")
	public Product[] test4() {
		return new Product[] { new Product("맥주",2000),
							   new Product("우유",1500) };
//[{"name":"맥주","unitCost":2000},{"name":"우유","unitCost":1500}]

//test4 액션 메소드가 리턴한 Product 객체 배열이 JSON 포멧으로 웹브라우저에 전송되었다.
//웹브라우저에 보이는 내용이 객체 배열의 JSON 포맷이다.

	}





}













