
public class Opr02_03 {

	public static void main(String[] args) {
		/*
		 문제. 다음 중 형변환을 생략할 수 있는 것은 모두 고르시오 ? (모두 고르시오 )
				byte b = 10;
				char ch = 'A';
				int i = 100;
				long l = 1000L;
				
				1. b = (byte)i;
				2. ch = (char)b; 
				3. short s = (short)ch;
				4. float f = (float)1;
				5. i = (int)ch;
				
		 
		 		1.2.3.4.5.풀이를 각각 적으시오
		 
		 */

		byte b = 10;
		char ch = 'A';
		int i = 100;
		long l = 1000L;
		

		b = (byte)i;
		// i의 타입은 int인데, byte 보다 크기 때문에 int 값을 byte로 변환할 때
		// 값의 손실이 발생할 수 있다. 따라서 명시적 형 변환(byte)가 필요하다
		
		ch = (char)b;
		// byte 와 char 는 서로 다른 데이터 타입이며, char 타입은 부호 없는 16비트
		// 데이터 타입이고, byte 타입은 부호 있는 8비트 데이터 타입이다.
		// 따라서 명시적 형 변환이 필요하다.
		
		short s = (short)ch;
		
		float f = l;
		// 정수형 리터럴 1은 float 타입으로 자동 형 변환이 가능하기 때문에 명시적
		// 형 변환을 생략할 수 있다.
		
		i = ch;
		// char는 int로 자동 형 변환이 가능하다. char 16비트이고, int는 32 비트이므로
		// 자동으로 int로 변환 한다. 따라서 명시적 형 변환이 필요 없다.
		
		System.out.println(b);
		System.out.println(ch);
		System.out.println(s);
		System.out.println(f);
		System.out.println(i);
	}

}
