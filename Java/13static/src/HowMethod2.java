

/*
 ---문제2---
 
SimpleMath클래스의 메소드들은 단순히 외부 클래스에게 기능을 제공할뿐,

인스턴스(객체)단위로 행하여 지는 일을 하지 않는다.

그러므로.. 최대한 사용가능하기 편리 하도록 정의하라.

AreaMath클래스와, PerimeterMath클래스 내부도 마찬가지로 바꾸어라

그리고 HowMethod.java 파일과 같이 출력결과가 나오게 하라.
*/

/*****************************************************************************************/
//제시된 클래스이름 으로 만들기  -> SimpleMath1클래스, AreaMath1클래스, PerimeterMath1클래스 ,

//				              HowMethod2클래스 는 main메소드가 있음
/*****************************************************************************************/



class SimpleMath1 { // 단순 계산 클래스
	public static final double PI = 3.1415;

	public static double add(double n1, double n2) {
		return n1 + n2;
	}

	public static double min(double n1, double n2) {
		return n1 - n2;
	}

	public static double mul(double n1, double n2) {
		return n1 * n2;
	}
}

class AreaMath1 { // 넓이 계산 클래스

	// 원넓이 계산 메소드
	public static  double calCircleArea(double rad) {
		
		// 반지름 * 반지름 의 결과받아와서 double(8) result변수에저장
		double result = SimpleMath1.mul(rad, rad);

		// 반지름 * 반지름 * 3.14 결과받아 와서 double(8) result변수에저장
		result = SimpleMath1.mul(result, SimpleMath1.PI);
				
		// 호출한곳으로!! 원넓이 리턴
		return result; // 원넓이 리턴
	}
}

class PerimeterMath1 { // 직사각형의 둘레계산클래스
	
	// 직사각형의 둘레 계산 메소드
	public static double calRectanglePeri(double width, double height) {
		
		// {(가로) ＋ (세로)} × 2 <--직사각형 둘레 리턴
		//풀어 쓰면
		//		->  (가로 X 2) + (세로 X 2) 
		
		// 직사각형 둘레 리턴
		return SimpleMath1.add(SimpleMath1.mul(width, 2), 
				               SimpleMath1.mul(height, 2)); 

	}
}

public class HowMethod2 {
	public static void main(String[] args) {
	
	//참고!! 원넓이 구하는 메소드 호출 , 호출시 2.4전달
    System.out.println("원의 넓이 : " +  AreaMath1.calCircleArea(2.4) ); 
     
     
	//참고!! 직사각형 둘레 구하는 메소드 호출, 호출시  2.0, 4.0 전달
    System.out.println("직사각형 둘레 : " +  PerimeterMath1.calRectanglePeri(2.0, 4.0) );

		// 출력 결과
		// 원의 넓이 : 18.09504
		// 직사각형 둘레 : 12.0
	}
}
/*
	결론
	   static으로 메소드를 만들어 놓으면 넓이 계산 클래스나, 둘레계산 클래스내부에
	   객체를 생성하지 않아도 static로 만든 클래스메소드를 클래스명.으로 호출가능
	   
	   장점 : 코드가 짧아지고 간결해 진다.
*/


















