import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy02 { // <- 파일 복사 프로그램
	
	// CMD창에서 입력할 것
	// java FileCopy02 원본파일명 복사할파일명

	public static void main(String[] args) {

		// 파일에 저장된 데이터를 2바이트 크기를 읽어 저장할 변수
		int data = 0;
		
		// 재료
		FileReader reader = null;
		FileWriter writer = null;
		
		// 복사할 파일명을 입력하지 않으면?
		if(args.length < 2) {
			System.out.println("java FileCopy02 원본파일명 복사될파일명");
			System.exit(0);
		}
		
		try {
			
			// 원본파일에 저장된 데이터를 영문 한 문자(2바이트) 단위로 읽어올 
			// FileReader 입력문자스트림 통로 객체 생성
			reader = new FileReader(args[0]); // 원본파일명
			
			// 복사할 파일에 원본파일에서 읽어들인 데이터를 영문 한 문자 (2바이트) 단위로
			// 기록할 FileWriter 출력문자스트림 통로 객체 생성
			writer = new FileWriter(args[1]); // 복사한파일명
			
			// 원본파일에서 2바이트 단위로 읽어들인 데이터가 존재하면 반복
			while((data = reader.read()) != -1) {
				// 한번 읽어들인 data 변수의 값을 복사될 파일명에 기록
				writer.write(data);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // try-catch와 상관없이 무조건 한번 실행되는 코드 작성
			
			// 위 만들어서 사용한 문자 입출력 스트림 통로를 사용하지 않으면 
			// 메모리 낭비이니 JVM 메모리에서 제거 하자
			try {
				if(reader != null) {
					reader.close();
				}
				if(writer != null) {
					writer.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} // finally

	} // main

} // class
