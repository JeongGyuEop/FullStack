

/*
 	객체 지향 프로그래밍 기법 3단계
 	
 	1단계. 자동차 객체 모델링
 		데이터
 			색상 : 자동차의 색상을 나타내는 문자열 데이터
 			모델명 : 자동차의 모델명을 나타내는 문자열 데이터
 			제조연도 : 자동차의 제조 연도를 나타내는 정수 데이터
 			현재 속도 : 자동차의 현재 속도를 나타내는 정수 데이터
 			연료량 : 자동차의 연료 잔량을 나타내는 정수 데이터
 			전원 상태 : 엔진의 켜짐 또는 꺼짐의 상태를 나타내는 boolean 데이터
 		
 		기능(동작)
 			시작 : 자동차읜 엔진을 켜는 기능
 			정지 : 자동차의 엔진을 끄는 기능
 			가속 : 자동차의 속도를 증가시키는 기능
 			감속 : 자동차의 속도를 감소시키는 기능
 			주행 : 자동차를 주행한느 기능, 현재 속도를 출력하거나 연료를 소모함
 			연료 보충 : 연료를 보충하는 기능
 			
 */

// 2단계 : 클래스를 만드는 일 (설계)
public class Car {
	
	//변수 : 1단계에서 모델링한 데이터를 저장할 변수
	
		// 색상 : 자동차의 색상을 나타내는 문자열 데이터
		String color;
		
		// 모델명 : 자동차의 모델명을 나타내는 문자열 데이터
		String model;
		
		// 제조연도 : 자동차의 제조 연도를 나타내는 정수 데이터
		int year;
		
		// 현재 속도 : 자동차의 현재 속도를 나타내는 정수 데이터
		int currentSpeed;
		
		// 연료량 : 자동차의 연료 잔량을 나타내는 정수 데이터
		int fuelLevel;
		
		// 전원 상태 : 엔진의 켜짐 또는 꺼짐의 상태를 나타내는 boolean 데이터
		boolean engineStatus; // true : 엔진 켜짐, false : 엔진 꺼짐
	
	
	// 메서드 : 1단계에서 모델링한 동작이나 기능들을 메서드로 표현
		/*
		 	시작 : 자동차의 엔진을켜는 기능
		 	
		 	메소드 명 - start
		 	구현 코드 내용
		 		1. 엔진의 전원상태를 켜지게 true로 설정
		 		2. "모델명의 엔진이 시작되었습니다." --> 출력
		 */
		void start() {
			engineStatus = true;
			System.out.println(model + "의 엔진이 시작되었습니다.");
		}
		
		/*
		  	정지 : 자동차의 엔진을 끄는 기능
		  	메소드명 - stop
		  	구현 코드 내용
		  		1. 엔진의 전원 상태를 꺼짐 false로 설정
		  		2. "모델명의 엔진이 정지되었습니다." --> 출력
		 */
		void stop() {
			engineStatus = false;
			currentSpeed = 0;
			System.out.println(model + "의 엔진이 정지되었습니다.");
		}
		
		/*
		 	가속 : 자동차의 속도를 증가시키는 기능
		 	메소드명 : accelerate
		 	매개변수 : 자동차의 속도를 증가시킬 정수(속도)값을 받을 increment 매개변수 선언
		 	구현 내용
		 		1. 조건문 if 문을 작성하여 만약 엔진이 켜져 있으면? 
		 			currentSpeed 변수값(현재 속도가 저장된 값)을 매개변수 increment로 받은
		 			증가시킬 속도값을 누적해서 현재 속도를 증가시킨다.
		 			그리고 가속에 따른 연료 소모를 적용하기 위해 fuelLevel 변수값 
		 			(연료 잔량이 저장됨)을 증가한 속도 / 10 계산값으로 차감시킨다.
		 			"model 이(가) 가속되었습니다. 현재속도 : currentSpeed km/h" <-- 출력
		 			
		 		2. 만약 엔진이 꺼져 있으면?
		 			"model의 자동차가 엔진이 꺼져있습니다. 먼저 엔진을 시작하세요." <-- 출력
		*/
		void accelerate(int increment) {
			if(engineStatus) {
				currentSpeed += increment;
				
				fuelLevel -= increment/10;
				
				System.out.println(model + "이(가) 가속되었습니다. 현재속도 : " + currentSpeed + "km/h");
			} else {
				System.out.println(model + "의 자동차가 엔진이 꺼져있습니다. 먼저 엔진을 시작하세요.");
			}
		}
			
		/*
		 	감속 : 자동차의 속도를 감소시키는 기능
		 	메소드명 - decelerate
		 	매개변수 - 자동차의 속도를 감소시킬 정수(속도)값을 매개변수 decrement 선언
		 	구현 내용
		 		1. 엔진이 켜져있을 경우
		 			속도를 매개변수 decrement로 받은 값으로 감소(차감) 시키고
		 			1.1 만약 현재 속도 값이 0보다 작다면?
		 				속도가 0미만으로 내려가지 않도록 하기 위해
		 				currentSpeed 변수값을 0으로 저장
		 				"model 이(가) 감속되었습니다. 현재속도: currentSpeed km/h" <- 출력
		 				
		 		2. 엔진이 꺼져 있을 경우
		 			"model의 엔진이 꺼져 있습니다. 먼저 엔진을 시작하세요." <- 출력
		*/
		void decelerate(int decrement) {
			if(engineStatus){
				currentSpeed -= decrement;
				if(currentSpeed < 0) {
					currentSpeed = 0;
				}
				System.out.println(model + "이(가) 감속되었습니다. 현재속도 : " + currentSpeed + "km/h" );
			} else {
				System.out.println(model + "의 엔진이 꺼져 있습니다. 먼저 엔진을 시작하세요.");
			}
		}
		
		/*
		 	연료 보충 : 연료를 보충하는 기능
		 	메소드명 - refuel
		 	매개변수 - 보충할 연료데이터(정수)를 매개변수 amount 선언
		 	구현 내용
		 		1. 연료 잔량이 저장되는 변수의 값을 매개변수 amount로 받은 보충연료값 누적
		 		2. "model에 연료가 보충되었습니다. 현재 연료량 :  L" <-- 출력
		*/
		void refuel(int amount) {
			fuelLevel += amount;
			System.out.println(model + "에 연료가 보충되었습니다."
					+ "현재 연료량 : " + fuelLevel + "L");
		}
	
}
