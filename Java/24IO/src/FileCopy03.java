import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
  PrintWriter클래스
  - 자바의 입출력 스트림 클래스중 하나로,
    다양한 데이터를 텍스트 형식으로 출력하는 기능을 제공하는 클래스입니다.
    
  - PrintWriter클래스는  Writer클래스를 확장해서 만들어 놓은 클래스로
    편리한 출력 기능과 다양한 출력형식을 지원합니다.
    
  - println(),  printf(),  format(), print() 메소드들을 통해 텍스트를 출력합니다.
  
  - 메소드
  	println(), print() : 다양한 형태의 텍스트를 출력하는 메소드
  	printf(), format() : 포맷문자열을 사용하여 형식화된 텍스트를 출력하는 메소드
  	write() : 문자열을 출력하는 메소드
  	flush() : 출력 버퍼 메모리를 강제로 비워 출력할때 사용하는 메소드
  	close() : 출력스트림 통로를 제거 (닫기)
  
  - 예외처리를 자동으로 처리합니다.   
  
  - 생성자
    public PintWriter(Writer writer)
    public PrintWriter(OutputStream outputStream)
    public PrintWriter(String fileName)
    ....
 */

//주제 : PrintWriter를 사용하여 텍스트를 파일에 출력하는 예제.

public class FileCopy03 {

	public static void main(String[] args) {
		
		PrintWriter printWriter = null;
		
		try { //텍스트를 출력할 출력스트림 통로 만들기
  			  printWriter = new PrintWriter("output.txt");

  			  printWriter.println("Hello, World!");
  			  printWriter.printf("Value: %d%s\n", 42,"안녕");
  			  printWriter.write("Some text");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//PrintWriter출력스트림 통로 메모리 자원해제
			printWriter.close();
		}
	
		
	}

}









