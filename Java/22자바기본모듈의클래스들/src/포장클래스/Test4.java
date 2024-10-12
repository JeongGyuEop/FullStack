package 포장클래스;

/* 문자열 String객체를 해당 기본데이터 타입으로 변환  
 * 그리고 문자열로 변환 연습 */
public class Test4 {
	public static void main(String[] args) {
		
		// "123" 문자열객체에 저장된 문자열"123"을 기본자료형 값 123으로 변환하여 반환받아..
		// 정수형(4) 변수 i에 저장
		int i = Integer.parseInt("123"); //-> 123
	
		// "true" 문자열객체에 저장된 문자열"true"를 기본자료형 값 true으로 변환하여 반환받아..
		// boolean형 변수 b에 저장
		boolean b = Boolean.parseBoolean("true"); //-> true
		
		// "3.141592" 문자열객체에 저장된 문자열 "3.141592"를 기본자료형 값 3.141592 변환하여 ..
		// 실수형(4) 변수 f에 저장
		float f = Float.parseFloat("3.141592"); //-> 3.141592
		
		// 정수 123을 "123"으로 변환후 저장된 문자열객체메모리주소를 반환받아 s1변수에 저장
		String s1 = Integer.toString(123); //-> "123"
		
		// s1변수가 사용하고 있는 문자열 객체 내부에 저장된 "123"을 반환받아 출력
		System.out.println(s1.toString());

		// 정수 123을 16진수의 문자열 "7b"로 변환후 저장된 문자열객체메모리주소를 반환받아 s2변수에저장 
		String s2 = Integer.toHexString(123);
		
		// s2변수가 사용하고 있는 문자열 객체 내부에 저장된 "7b"를 반환 받아출력
		System.out.println(s2.toString());
		
		// 실수3.141592f를 문자열 "3.141592"로 변환후 저장된 문자열객체메모리주소를 반환받아 s3변수에 저장
		String s3 = Float.toString(3.141592f); //-> "3.141592"
		
		// s3변수가 사용하고 있는 문자열 객체 내부메모리에 저장된 "3.141592"를 반환 받아 출력
		System.out.println(s3.toString());
		
		// 문자 'a'를 문자열 "a"로 변환후 저장된 ~ 문자열객체 메모리 주소를 반환받아 s4변수에 저장
		String s4 = Character.toString('a'); //-> "a"

		// s4변수가 사용하고 있는 문자열 객체 내부 메모리에 저장된 "a"문자열을 반환 받아 출력
		System.out.println(s4);

		// boolean 값 true를 문자열 "true"로 변환후 저장된~ 문자열객체 메모리 주소를 반환받아 s5변수에 저장
		String s5 = Boolean.toString(true); //-> "true"
				
		// s5변수가 사용하고 있는 문자열 객체 내부 메모리에 저장된 "true"문자열을 반환 받아 출력 
		System.out.println(s5.toString());

	}

}





