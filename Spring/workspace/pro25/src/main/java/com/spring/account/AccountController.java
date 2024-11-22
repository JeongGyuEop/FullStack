package com.spring.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
/*
-- 예금자의 계좌정보를 저장하는 테이블
CREATE TABLE cust_account(
    accountNo varchar2(20) PRIMARY KEY,-- 계좌번호
    custName varchar2(50), -- 예금자 
    balance number(20,4) -- 금액(잔액)
);

-- 홍길동과 김유신의 계좌 정보를 생성합니다.
insert into cust_account(accountNO, custName, balance)
values('70-490-930','홍길동',10000000);

insert into cust_account(accountNO, custName, balance)
values('70-490-911','김유신',10000000);

commit;	

실험1. 트랜잭셩 기능을 적용함
http://localhost:8090/pro25/account/sendMoney.do 주소를 입력하여 
정상적으로 계좌 이체 기능을 하고 서로 계좌 잔액이 갱신 되었는지 DB의 테이블에서 확인!

실험2. 트랜잭션 기능이 적용 되어 있지 않음
*/


//사장
public class AccountController extends MultiActionController{
	
	//부장(AccountService객체의 주소)를 주입 받아 저장할 변수 선언
	private AccountService accService;
	
	/*
	 action-servlet.xml파일에 작성 해 놓은
	 <property name="accService" ref="accService"> 태그로 
	 아래의 setAccService메소드를 호출합니다.
	 ref자리에 <bean>의 id속성값을 전달하면 
	 action-service.xml파일에 작성 해놓은  AccountService객체의 주소가 매개변수로 전달됩니다.
	 전달 받은 AccountService객체의 주소를 위 변수에 저장(주입) 합니다.
	 */
	public void setAccService(AccountService accService) {
		this.accService = accService;	
	}
	
	//금액을 이체하는 기능의 메소드
	public ModelAndView sendMoney(HttpServletRequest request,
							      HttpServletResponse repResponse) throws Exception {
		
		//부장(AccountService)에게 금액 이체 명령!
		accService.sendMoney();
		
		//이체에 성공하면
		//웹브라우저에 보여줄 뷰명을 ModelAndView객체에 저장 후 디스패처서블릿으로 반환
		return new ModelAndView("result");
		
	}
	
	

}












