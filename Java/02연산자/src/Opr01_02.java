
/*
 *   예제 : 문자열 데이터에 + 연산하기
 *   
 *   산술연산자 + 에 대해
 *   	+ 산술연산자는 합을 구할 수도 있지만, 문자열끼리 하나로 합쳐서 계산할 수 있다.
 *   	+ 산술연산자를 문자 결합 연산자라고도 부른다.
 */

public class Opr01_02 {

	public static void main(String[] args) {
		
		// 자료형 + 변수명;
		String a;
		a = "Apple";
		
		String b = "Banana";
		
		// 문자열끼리 + 연산을 할 경우 두 문자열은 하나의 문자열로 합쳐져 반환된다.
		// String c = "Apple" + "Banana";
		String c = a + b;
		System.out.println(c);
		
		String str = "결과값 : ";
		int n = 10;
		
		// 정수 데이터를 문자열 데이터와 + 연산을 하게 되면
		// 정수 데이터가 + 연산을 하기 전에 문자열로 자동 형변환된 이후
		// 앞에 작성한 문자열과 + 하여 결합된다.
		System.out.println(str + n); // "결과값 : 10"
		System.out.println(n + str); // "10결과값 : "
		

	}

}
