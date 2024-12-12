package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.skhu.dto.Product;


@Controller   // <bean id="secondController" class="패키지경로.SecondConroller"/>
@RequestMapping("second")
public class SecondController {

	//메소드들을 액션메소드라 부릅니다

	//http://localhost:8088/second/test1
	@GetMapping("test1")
	public String test1(Model model) {
		//액션 메소드가 뷰에 전달할 데이터를, 이 Model 객체에 넣어서 전달한다.
		//즉 Model 객체는, 데이터 전달 상자라고 보면 된다.
		//이렇게 전달되는 데이터를 model attribute 라고 한다.

		model.addAttribute("message", "안녕하세요");
		//model attribuet 데이터의 이름은 "message" 이고, 값은 "안녕하세요" 문자열이다.

		return "second/test1";

	}
	//http://localhost:8088/second/test2
	@RequestMapping("test2")
	public String test2(Model model) {

		Product product = new Product("맥주", 2000);

		model.addAttribute("product", product);
		//model attribuet 데이터의 이름은 "product" 이고, 값은 Product 객체이다.

		return "second/test2"; // <- 뷰 경로 리턴
	}







}
















