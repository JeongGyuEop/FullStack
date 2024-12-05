package com.myspring.pro29;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
    //http://localhost:8090/pro29/ 로 요청하면 호출되는 메소드로 JSONTest.jsp화면을 웹브라우저로 보여줌 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "JSONTest2"; //JSONTest.jsp를 웹브라우저에 표시 하도록 지정 
	}
	
}
