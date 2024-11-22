package com.spring.account;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//부장

//AccountService클래스의 모든 메소드에 트랜잭션 기능을 적용 시킵니다.

@Transactional(propagation = Propagation.REQUIRED)
public class AccountService {	
	
	private AccountDAO accDAO;	
	
	//참고. action-service.xml파일의 코드에서 호출함
	public void setAccDAO(AccountDAO accDAO) {
		this.accDAO = accDAO;
	}	
	//계좌 이체 후 잔액을 갱신 하는 단위 기능을 하는 AccountDAO에 명령 하는 메소드 
	public void sendMoney() {
		//홍길동이 김유신에게 500만원을 이체 하므로
		//홍길동의 계좌에 500만원이 차감시키는 UPDATE문을 실행하기 위한 
		//첫번째 AccountDAO객체의 메소드 호출!
		accDAO.updateBalance1();
		
		//김유신의 계좌에 500만원이 누적되게 UPDATE문을 실행하기 위한
		//두번쨰 AccountDAO객체의 메소드 호출!
		accDAO.updateBalance2();
		
	}
	
	

}











