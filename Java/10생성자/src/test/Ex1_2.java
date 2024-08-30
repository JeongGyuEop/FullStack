/* 
	
	11클래스와객체 자바프로젝트 내부의 Ex1.java(과일장수이야기) 파일 업그레이드 시키기
	(Ex1.java 를 -> Ex1_1.java로 업그레이드 하기) 

------------------------------------------------

두명의 과일장수와 한명의 구매자가 있다고 가정 하자!

과일장수1 : 보유하고 있는 사과의 수는 30개이고, 개당가격은 1,500원
과일장수2 : 보유하고 있는 사과의 수는 20개이고, 개당가격은 1,000원
과일 구매자

[시물레이션 상황] 
"나는 과일장수1에게 4,500원어치 사과를 구매했고, 과일장수2에게 2,000원어치 사과를 구매했다."

문제점 : 두명의 과일장수 객체를 생성해야 하는데.. 
	   과일장수들의 사과보유수와 , 사과 개당가격이 다르기 때문에  
       인스턴스변수의 초기값도 달라저야한다. 
       그러므로 전의 Ex1.java파일 코드처럼 클래스를 정의하면서 
       변수값 final int APPLE_PRICE = 1000;를 초기화할 수 없다.
       생성되는 과일장수 객체마다 변수 값을 달리해야 하기 때문이다.

문제해결 할수 있는 최선의 방법 :
- 과일 장수들 객체를 생성하고 나서, int apple_PRICE 인스턴스변수를 초기화합니다.
- int apple_price 객체 변수를 초기화 하기위한 메소드를 생성 하여 클래스 내부에 추가. 

--------------------------------------------------------
	
*/


// --------------------- 생성자를 이용하는 방법으로 업그레이드 시키자

// FruitSeller(과일장수) 클래스 내부 수정
// 순서 1. 사과 한개당 가격 변수에 final을 붙여 상수로 만들어주자.
//			단! 사과 한개당 가격은 메모리 생성시 호출되는 생성자 내부에서 초기화 하자

// 순서 2. 과일 장수 객체의 사과 한개당 가격, 사과 보유 수, 판매수익을 각각
//			객체 변수들에 초기화시킬 생성자 추가

//순서3. 추가한 initMembers 메소도 삭제하기!

//-------------------------------------------------------------------------------
// FruitBuyer(과일 구매자)클래스 내부 수정
// 순서 1. 현재 소유하고 있는 현금을 0원, 개수 0개로 초기화

// 순서 2. 구매자의 현재 소유하고 있는 현금을 매개변수로 받아 초기화,
//			구매자의 현재 사과 보유 개수를 매개변수로 받아 초기화할 생성자 추가



//(2단계) 클래스(설계도) 정의 : 변수 + 메소드
//과일장수 FruitSeller라는 이름의 틀(클래스)을 정의합니다.
package test;


class FruitSeller{ 
	
	/*변수*/
	//변수(상수화)
	//사과 한개당 가격
	final int APPLE_PRICE;
	//보유하고 있는 사과의 수 저장할 변수
	int numOfApple;
	//판매수익을 저장할 변수
	int myMoney;
	
	/* 생성자 */
	// 과일 장수 객체의 사과 한개당 가격, 사과 보유 수, 판매수익을 각각
	// 객체 변수들에 초기화시킬 생성자 추가 
	public FruitSeller(int apple_price, int numOfApple_, int myMoney_) {
		APPLE_PRICE = apple_price;
		numOfApple = numOfApple_;
		myMoney = myMoney_;
	}

	/*메소드*/
	//사과판매 행동 메소드	
	int saleApple(int money) { //사과구매액이 메소드의 매개변수로 전달받음
		
		//판 사과 개수 
		int num = money / APPLE_PRICE;//구매자가 사과구매를 위해 지불 한 금액에~ 개당 가격을 나누면 판 사과의 개수를 얻을 수 있다.		
		//사과 판매후 사과의 수줄이기
		numOfApple -= num;
		//판매 수익 누적
		myMoney += money;
	
		//실제 구매(판매)가 발생한  사과의 수를 반환
		return num; 		
	}
	
	//사과판매후 남은 사과수,판매수익 출력 하는 기능의 메소드
	public void showSaleResult(){
		System.out.println("남은 사과 : " + numOfApple);
		System.out.println("판매 수익 : " + myMoney);
			
	}
	
}// FruitSeller 클래스 끝


//(2단계) 클래스(설계도) 정의 : 변수 + 메소드
//나(과일구매자) FruitBuyer라는 이름의 틀(클래스)을 정의합니다.
class FruitBuyer {
	/*변수*/
	//현재 소유하고 있는 현금
	int myMoney;
	//현재 소유하고 있는 사과수
	int numOfApple;
		
	/* 생성자 */
	// 구매자의 현재 소유하고 있는 현금을 매개변수로 받아 초기화할 생성자 추가
	public FruitBuyer(int myMoney_) {
		myMoney = myMoney_; // 초기화
	}
	
	/*메소드*/
	//과일구매 기능 메소드
	//매개변수 : 구매하기위한 판매자대상, 구매금액
	public void buyApple(FruitSeller seller , int money) {
		//판매자에게 과일 구매하기 ,구매한사과수 리턴 받아와서 현재사과수에 누적 
		numOfApple += seller.saleApple(money);
		//판매자에게 과일 구매후 현재 소유하고 있는 현금
		myMoney -= money;		
	}	
	//과일구매후 현재 남은 현금과 사과의수 출력 기능 
	public void showBuyResult() {
		System.out.println("현재잔액 : " + myMoney);
		System.out.println("사과 개수 : " + numOfApple);
	}		
}


public class Ex1_2 {
	public static void main(String[] args) {

		// 과일장수 2명 객체 생성
		
		// 과일장수 객체 생성과 동시에 객체(인스턴스)변수 값 초기화
		// 방법 : 생성자 호출!
		
		// 과일 장수 1 : 사과 한 개당 가격 1500, 사과보유 수 30개, 판매수익 0원
		FruitSeller seller1 = new FruitSeller(1500, 30, 0);
		// 과일 장수 2 : 사과 한 개당 가격 1000원, 사과보유 수 : 20개, 판매수익 0원
		FruitSeller seller2 = new FruitSeller(1000, 20, 0);
		
		
		// 과일 구매자 1명 객체 생성
		
		// 과일 구매자 객체 생성과 동시에 객체 변수 값 초기화를 위한 생성자 호출!
		// 과일 구매자가 현재 소유하고 있는 현금 10000원 , 현재 구매한 사과 개수 0개
		FruitBuyer buyer1 = new FruitBuyer(10000);
		
		// 과일 구매자 객체는 과일장수1 객체에게 4500원어치 사과 구매하기
		buyer1.buyApple(seller1, 4500);
		// 과일 구매자 객체는 과일장수2 객체에게 2000원어치 사과 구매하기
		buyer1.buyApple(seller2, 2000);
		
		
		// 과일장수1, 과일장수2의 현재 객체변수값 상태 출력
		seller1.showSaleResult();
		System.out.println("-----------------------------------------------");
		seller2.showSaleResult();
		System.out.println("-----------------------------------------------");

		
		// 과일구매자 객체변수값 상태 출력
		buyer1.showBuyResult();
		
		
	 }
}

