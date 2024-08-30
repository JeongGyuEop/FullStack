
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



//(2단계) 클래스(설계도) 정의 : 변수 + 메소드
//과일장수 FruitSeller라는 이름의 틀(클래스)을 정의합니다.

class FruitSeller{ 
	
	/*변수*/
	//변수(상수화)
	//사과 한개당 가격
//	final int APPLE_PRICE = 1000;
	
	// 상수를 변수 메모리로 바꿔서 만들어주자
	int apple_price ;
	
	//보유하고 있는 사과의 수 저장할 변수
	int numOfApple;

	//판매수익 을 저장할 변수
	int myMoney;
	
	// 기본생성자가 만들어지는 위치   
	public FruitSeller() {
		// TODO Auto-generated constructor stub
	}

	/*메소드*/
	//사과판매 행동 메소드	
	int saleApple(int money) { //사과구매액이 메소드의 매개변수로 전달받음
		
		//판 사과 개수 
		int num = money / apple_price;//구매자가 사과구매를 위해 지불 한 금액에~ 개당 가격을 나누면 판 사과의 개수를 얻을 수 있다.		
		
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
	
	// 나중에 FruitSelller 클래스의 객체 메모리 생성후
	// 각 객체 변수 3개의 값을 저장할 기능의 메소드 추가
	//						사과 1개당 가격, 현재 보유한 사과 수	판매수익이자
	public void initMembers(int apple_price_, 	int numOfApple_,		int myMoney_ 	) {
		// 객체변수 명 = 매개변수 명;
		apple_price = apple_price_;
		numOfApple = numOfApple_;
		myMoney = myMoney_;
	}
	
		
	
}// FruitSeller 클래스 끝



//(2단계) 클래스(설계도) 정의 : 변수 + 메소드
//나(과일구매자) FruitBuyer라는 이름의 틀(클래스)을 정의합니다.
class FruitBuyer {
	/*변수*/
	//현재 소유하고 있는 현금
	int myMoney = 10000; // 10000으로 수정
	//현재 소유하고 있는 사과수
	int numOfApple = 0;
		
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




public class Ex1_1 {
	public static void main(String[] args) {

		// 해결방법 사용 : 
		// 과일장수 1, 2 객체를 생성하고 나서, 
		// 객체 변수를 추가한 메소드로 초기화합니다.
		
		// 과일장수 1 객체 생성
		FruitSeller seller1 =  new FruitSeller();
		// 생성된 과일 장수 1객체의 객체변수의 값은 저장하기 위해
		// -> 추가한 initMembers 메소드 호출
		// 사과 1개당 가격 : 1,500, 현재 보유한 사과갯수 : 30개, 판매수익이자 잔고 : 0원
		seller1.initMembers(1500, 30, 0);
		
		// 과일장수 2 객체 생성
		FruitSeller seller2 = new FruitSeller();
		// 생성된 과일 장수 2객체의 객체변수들의 값을 저장하기 위해
		// -> 추가한 initMembers 메소드 호출
		// 사과 한개당 가격 : 1,000,, 현재 보유한 사과 갯수 : 20개, 판매수익이자 잔고 : 0원
		seller2.initMembers(1000, 20, 0);
		
		// 1명의 과일 구매자 객체 생성
		FruitBuyer buyer = new FruitBuyer();
		// 과일구매자 객체는 과일장수 1에게 4500원을 지불하고 사과구매
		buyer.buyApple(seller1, 4500);
		
		// 과일구매자 객체의 현재 객체변수에 저장된 값들의 상황출력
		System.out.println("----------- 과일 구매자의 현재 상황 ------------");
		buyer.showBuyResult();
		
		// 과일 장수 1 객체의 현재 객체변수에 저장된 값들의 상활출력
		System.out.println("----------- 과일 장수 1의 현재 상황 -------------");
		seller1.showSaleResult();
		
		// 1명의 과일 구매자 객체가 과일 장수 2객체에게 2000원어치사과 구매하기
		buyer.buyApple(seller2, 2000);
				
		// 과일구매자 객체의 현재 객체변수에 저장된 값들의 상황출력
		System.out.println("----------- 과일 구매자의 현재 상황 ------------");
		buyer.showBuyResult();
				
		// 과일 장수 1 객체의 현재 객체변수에 저장된 값들의 상활출력
		System.out.println("----------- 과일 장수 2의 현재 상황 -------------");
		seller2.showSaleResult();

		
		/*
		 	이 프로그램은 과일장수 1, 과일장수 2 객체들의 사과 1개당 가격은 객체 메모리를 생성한 후
		 	각각 1500원, 1000원 으로 다르게 설정하는 것은 가능했다.
		 	
		 	그러간 이 프로그램은 문제점 2가지가 있다.
		 	
		 	1. 과일장수1 과 2객체는 생성하고 난 다음에 객체 변수들의 값을 초기화해야한다.
		 		즉, 두 줄에 걸쳐서 코드 문장을 작성해야 하나의 객체 메모리를 생성해서 완성할 수 있다.
		 		이것은 코드의 가독성이 저하 된다.
		 		
		 	2. FruitSeller(과일장수)클래스로 객체를 생성하면 객체 메모리 내부의 APPLE_PRICE 상수의 final
		 		선언이 사라진 것이 문제가 된다.
		 		
		 		final 선언이 사라진 것이 왜 문제가 될까
		 			: 클래스 설계 시 과일 장수가 판매하는 사과 한개당 가격은 변경되지 않음을 가정해 두고
		 			  객체 변수 int apple_price로 변경했다.
		 			  그러나 initMember 메소드 내부에서 구현코드로 apple_price 변수값을 초기화 하려다보니
		 			  어쩔 수 없이 final 선언을 강제로 삭제 시켰다.
		 			  
		 			  메소드라는 것은 만들어 놓고 여러 번 호출할 수 있으므로 initMember 메소드를 여러 번
		 			  호출한다면 객체 메모리의 초기화하는 작업의 실수를 범할 수도 있기 때문에
		 			  결론은 객체 메모리를 생성하는 동시에 단 한 번만 호출되는 생성자를 이용해 
		 			  객체 메모리를 생성 후 빠르게 객체변수들을 초기화 해야 한다.
		 		
		 	이 프로그램의 문제점들의 해결방법은 클래스 설계시 내부에 생성자를 만들어 놓고 사용하자
		 */

						
	 }
}

