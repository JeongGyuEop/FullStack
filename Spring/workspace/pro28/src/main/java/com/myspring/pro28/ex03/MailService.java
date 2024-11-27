package com.myspring.pro28.ex03;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

//부장

@Service("mailService")
public class MailService {

	//mail-contxt.xml파일에서 
	//설정한 <bean id="mailSender" class="...JavaMailSenderImpl">객체를 자동 주입합니다.
	@Autowired
	private JavaMailSender mailSender;
	
	//mail-contxt.xml파일에서 
	//설정한 정기적으로 이메일을 보낼 데이터가 저장된 템플릿인 	
	//<bean id="preConfiguredMessage" 
	//      class="org.springframework.mail.SimpleMailMessage"> 객체를 자동 주입합니다.  
	@Autowired
	private SimpleMailMessage preConfiguredMessage;
	
	
	
	@Async
	//@Async 애너테이션 기호로 지정된 메소드는 비동기로 동작합니다
	//따라서 보내는 메일수가 많을 경우에는 메일 보내는 작업을 따로 수행하므로 작업이 끝날때까지 기다릴 필요가 없습니다
	public void sendMail(String to, String subject, String body) {
		
		//JavaMailSenderImpl 클래스의 createMimeMessage() 메소드를 호출하면, 
		//빈 MimeMessage 객체가 생성됩니다. 
		//이 MimeMessage 객체는 이후에 이메일의 내용(제목, 본문, 수신자, 발신자 등)을 설정하기 위해 사용됩니다. 
		//JavaMailSenderImpl 클래스는 JavaMailSender 인터페이스를 구현하므로, 
		//createMimeMessage() 메소드를 호출하여 MimeMessage 객체를 생성할 수 있습니다. 
		//이후에 MimeMessage 객체를 사용하여 이메일의 내용을 설정하고 
		//send() 메소드를 호출하여 이메일을 전송할 수 있습니다.
		MimeMessage message = mailSender.createMimeMessage();
		
		
		try {
			/*
			
			MimeMessageHelper 객체에 설정할 정보는 다음과 같습니다.

				수신자(To), 참조(Cc), 숨은 참조(Bcc) 이메일 주소
				보내는 사람(From) 이메일 주소
				이메일 제목(Subject)
				이메일 본문 내용
				첨부 파일
				이메일 인코딩 방식(UTF-8 등)
			
			*/
	
		//JavaMailSender를 이용하여 MimeMessageHelper 객체를 생성하는 코드입니다.
			//message로 전달한 값은?? 생성된 MimeMessage 객체를 매개변수로 전달하여
			//				      MimeMessageHelper 객체를 생성합니다.
			//true로 전달한 값은?? 이메일을 보낼때 다중 파일 첨부를 허용하는 불리언 값입니다.
			//UTF-8로 전달한 값은?? 보내는 이메일 본문내용의 문자 인코딩을 설정하는 값입니다. 			
			MimeMessageHelper messageHelper = 
			new MimeMessageHelper(message, true, "UTF-8");
			
		
			// 참조(Cc) -  복수의 수신자 이메일 주소를 추가할 수 있습니다.
            // messageHelper.setCc("zzzzzz@naver.com");   
			
			//메일 수신시 지정한 보내는 사람(송신자)의 이메일 주소와  이름으로 표시되게 합니다. 
			//이메일을 보내는 사람(송신자)의 이메일 주소 적기!!!!!!!!!
			messageHelper.setFrom("gueeop0219@gmail.com","홍길동 동생의 동생");
	
		
			
			//보내는 이메일 제목,  
			//이메일을 받을 사람의 이메일주소 , 
			//보내는 이메일 본문 내용을 설정해 메일을 보냅니다
			
			//보내는 이메일 제목(Subject)
			messageHelper.setSubject(subject); //"테스트 메일"
			
			//수신자(To) 이메일을 받을 사람의 이메일주소
			messageHelper.setTo(to);  //"gueeop0219@gmail.com"
			
			//보내는 이메일 본문 내용
//			messageHelper.setText(body);//"안녕하세요.보낸 메일 내용입니다."   
			messageHelper.setText(body, true); //메일 내용이 HTML태그 형식으로 표시 되게 하려면
											   //setText메소드 호출시 두번쨰 인자값을 true로 설정해야함
			
			//이메일을 보냅니다.
			mailSender.send(message);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//수신자에게 메일을 정기적으로 보내는 경우!!!!! 사용!!!! 
	//mail-context.xml에서 미리 설정한 수신 주소로 메일 내용을 보냅니다.
		@Async
		public void sendPreConfiguredMail(String message) {
			
		  SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
		  mailMessage.setText(message);//보낼 메일 내용 설정
		  mailSender.send(mailMessage);//메일 보냄 
		}	
		

	
}






