package 포장클래스;

public class Ex2 {

	public static void main(String[] args) {
		//int -> Integer포장(래퍼)클래스 학습
		
	//첫번째) Integer클래스의 생성자 종류 2가지 
	// 1. 정수값을 매개변수로 받아서 저장하는 생성자
	// 2. 문자열을 매개변수로 받아서 저장하는 생성자
		
		//기본자료형 정수값 하나를 포장(래퍼)역할을 하는 Integer클래스의
		//객체 메모리의 변수에 저장하기 위해 
		//Integer클래스의 객체 메모리 생성
		Integer num01 = new Integer(10);//박싱:기본자료형값을 저장
		Integer num02 = new Integer("20");//박싱
		
	//두번째) Integer클래스의 메소드 호출해보자
		// int intValue()메소드 : 
		//-> Integer객체 메모리의 인스턴스변수에 저장된 값을
		//	 정수로 변환해서 반환하는 메소드 
		
		//참고. 포장클래스의 객체 메모리에 저장된 기본자료형값을 추출 작업
		//		-> 언박싱
		int n01 = num01.intValue(); //10  //언박싱
		int n02	= num02.intValue(); //"20"을 정수 20으로 변환후 반환
				  					//언박싱
				  
		//Integer포장(래퍼)객체 메모리에 저장된 10과 20을 뽑아낸후
		//+연산해서 합을 구한뒤 sum변수에 저장
		int sum = n01 + n02;
		System.out.println("두정수의 합은? " + sum);//30
		
		//sum변수에 저장된 값을 2진수로 변환, 8진수로 변환, 16진수 형태로
		//변환해서 반환하는 메소드 
						  //sum 변수값30을 2진수로 변환->11110
		System.out.println("sum 변수값30을 2진수로 변환->" +
						    Integer.toBinaryString(sum));
		
						  //sum 변수값30을 8진수로 변환->36
		System.out.println("sum 변수값30을 8진수로 변환->" + 
						    Integer.toOctalString(sum));
						
						  //sum 변수값30을 16진수로 변환->1e
		System.out.println("sum 변수값30을 16진수로 변환->" + 
		                    Integer.toHexString(sum));

		//박싱 : 기본 자료형 값을 포장클래스의 객체 메모리에 저장
		Integer num03 = new Integer(30);
		
		// double doubleValue()메소드
		//-> Integer객체에 저장된 데이터를 실수로 변환해서 반환하는 메소드
		
		double result = num03.doubleValue();
		System.out.println(result); //30.0
		
		// static int parsInt(Strin s) 메소드
		//-> 숫자형태의 문자열을 매개변수로 전달하면
		//   문자열을 정수로 변환해서 반환하는 메소드 
//		"100" -> 100
		int num = Integer.parseInt("100");
		System.out.println("문자열 100을 숫자100으로 변환후 받자");
		System.out.println(num); //100 <- 숫자 
		
		System.out.println(num + 200); //300 <- 숫자
		
		//참고.
		// parseXXX메소드들
		//-> 문자열형태의 데이터를 해당 포장(래퍼)클래스의 기본자료형값으로
		//	 변경후 반환하는 메소드들
		
		
		
		
	}

}






