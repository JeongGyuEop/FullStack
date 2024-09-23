
// 주제 : 키보드로부터 한글자를 입력받아 모니터 화면에 출력

/*
 	스트림(Stream)이란?
 		- 키보드 또는 기기를 통해서 입력된 데이터가 바이트 단위로 흘러 가는 통
 */
/*
		 	System.in 
		 		-> System 클래스 내부에 in 상수에 저장된 InputStream
		 		   입력스트림 통로역할을 하는 객체
		 		   
		 	InputStream 클래스의 read() 메소드 
		 		-> 참고. 1. 입력 스트림 통로에서 1바이트를 읽는다.
		 				 2. 읽은 바이트 값을 int타입으로 반환한다.
		 				    반환값은 읽은 바이트의 정수표현이며 0부터 255사이의 값이다.
		 				    만약 입력스트림 통로 끝에 도달하면 -1을 반환한다.
		 				    (더이상 읽어들을 byte단위의 값이 없으면 -1을 반환한다.)
		 
		 	System.out
		 		-> System 클래스 내부에 만들어져 있는 out 상수에 저장된 PrintStream 객체
		 		   (출력스트림 통로 역할을 하는 객체)를 반환해 올 수 있다.
		 		-> PrintStream 객체는 출력장치인 모니터 장치와 연결되어 있는 
		 		   1바이트 단위로 모니터에 내보내어 출력할 출력스트림 통로 역할의 객체 
*/

import java.io.IOException;
import java.io.InputStream;

public class IOTest00 {

	public static void main(String[] args) {

		// 키보드 장치에서 입력받은 전체 데이터 주에서 1바이트씩 읽어 저장할 변수
		int data = 0;
		
		System.out.print("하나의 문자를 입력하세요. 끝내려면 ctrl+z를 누르세요.");
		
		try {
			// 키보드장치로부터 입력한 데이터를 1바이트씩 읽어올
			// 입력 스트림 통로 역할을 하는 InputStream 객체 얻어오기
			InputStream input = System.in;
			
			// 입력한 전체 데이터 중에서 1바이트 크기의 데이터만 읽어 읽어들인
			// 바이트 수를 정수로 반환해서 저장
			data = input.read();
			
			// 키보드로 부터 입력된 전체 데이터 중에서
			// 읽어 들일 데이터가 InputStream 통로에 존재하면
			// 계속 반복해서 1바이트씩, 1바이트씩 읽어서 data 변수에 저장
			// 그리고 1바이트씩 읽어들인 후 바로 모니터(출력기기)에 출력
			while( data != -1 ) {
				// 1바이트씩 읽어들인 데이터 모니터화면에 내보내어 출력
				System.out.print((char)data);
				// 반복문을 통하여 1바이트씩 반복해서 계속 읽어들임
				data = input.read();
			}
			
			
		} catch(IOException e) {
			e.printStackTrace(); // 입출력시 발생한 예외 메시지 출력 
		}
		
	}

}
