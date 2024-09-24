

// 주제 : 파일에 데이터를 내보내어 기록하기 위해 PrintStream 클래스를 사용한 예

import java.io.PrintStream;

public class PrintStreamTest1 {

	public static void main(String[] args) {

		try {
			// ps,txt 파일에 데이터를 기록(내보낸다, 쓰기, 출력)하기 위한
			// 출력스트림 통로 역할의 객체 생성
			PrintStream out = new PrintStream("ps.txt");
			
			out.println(10);
			out.println(3.4);
			out.println('A');
			out.println(true);
			out.println("Hello");
			
			// 사용이 끝난후 PrintStream 통로 객체 메모리 JVM메모리에서 제거
			out.close();
			
			
		} catch (Exception e) {

		}

	}

}
