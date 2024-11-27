package com.myspring.pro28.ex03;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//사장 
//@EnableAsync 어노테이션을 사용하여 비동기적으로 메일을 보낼 수 있도록 설정
@EnableAsync
@Controller
public class MailController {
    @Autowired
    private MailService mailService;
 
    // @RequestMapping 어노테이션을 사용하여 
    //'/sendMail.do' URL 경로에 GET 요청이 들어왔을 때 sendSimpleMail 메서드가 실행됩니다.
    @RequestMapping(value = "/sendMail.do", method = RequestMethod.GET)
    public void sendSimpleMail(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 요청과 응답의 인코딩을 UTF-8로 설정
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
        // PrintWriter 객체를 사용하여 클라이언트에게 응답을 전송
        PrintWriter out = response.getWriter();
        
        //HTML태그들을 작성해  StringBuffer객체 메모리에 문자열로 저장한후 이메일 내용으로 보냅니다.
        
        	//StringBuffer 객체 생성
        	StringBuffer sb = new StringBuffer();
        	sb.append("<html>");
        	sb.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
        	sb.append("<h1>" + "제품소개" + "</h1><br>");
        	sb.append("신간 도서를 소개합니다.<br><br>");
        	sb.append("<a href='https://product.kyobobook.co.kr/detail/S000000935744'>");
        		sb.append("<img src='https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788960777330.jpg' alt='자바 ORM 표준 JPA 프로그래밍 대표 이미지'>");
        	sb.append("</a>");				
        	sb.append("</html>");
        
        	//위 html태그를 문자열로 변환해서 다시 저장
        	String str = sb.toString();
        
        //MailService의 sendMail 메서드를 호출하여 수신자, 제목, 내용을 전달하고, 
        mailService.sendMail("gueeop0219@gmail.com","신상품을 소개 합니다.",str);
        
        // MailService의 sendPreConfiguredMail 메서드를 호출하여 미리 설정된 메일을 전송합니다.
        mailService.sendPreConfiguredMail("테스트 메일입니다.");
        
        // 클라이언트에게 '메일을 보냈습니다!!' 메시지를 응답으로 전송
        out.print("메일을 보냈습니다!!");
    }
}














