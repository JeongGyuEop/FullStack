


import java.io.*; //입출력관련 클래스와 인터페이스를 사용하기 위해 작성


//예제. 키보드에서 입력받은 내용을 BufferedReader로 읽어들이고 
//     읽어 들인 내용을 특정파일에 BufferedWriter통로를 통해 기록 합니다. 


public class ReaderWriterTest03 {

	public static void main(String[] args) throws Exception {

		//입력받은 파일명을 저장할 변수 
		String fileName = null;
		
		//파일에 저장할 내용을  입력받아 한줄 단위로 읽어서 저장할 변수 
		String buf = null;
		
		//한줄(라인)단위로 읽어들일 입력스트림 통로 만들기
		BufferedReader keyBr =   new BufferedReader( new InputStreamReader(System.in) );      
		
		System.out.print("파일 이름을 입력하세요->");

		//키보드로 부터 입력받은 파일이름을 한줄단위로 읽어와 변수에 저장
		fileName = keyBr.readLine();
		
		System.out.println("파일에 기록할 내용을 입력하세요.");
		
	//BufferedWriter출력스트림통로 역할을 하는 객체 생성
		//2바이트 문자단위로  내보낼(출력할,쓸,기록할) 출력스트림 통로(new FileWriter());객체를 
		//한줄 단위로 내보낼 출력스트림 통로(new BufferedWriter())객체로 변환 해서 만든다.
		BufferedWriter fileBw = new BufferedWriter(new FileWriter(fileName));
		
		//파일에 기록할 내용을 입력받았으면
		//그중에서 한줄단위로 입력받은 데이터를 읽어와 buf변수에 저장합니다.
		//읽은 데이터 가 존재하면 반복해서 계속해서 읽어 들이겠다.
		while(  (buf = keyBr.readLine()) != null ) {
			
			//한번 읽어 들인 데이터를 파일에 기록해야 합니다.
			//BufferedWriter출력스트림 통로를 통해서 한줄 단위로 파일에 내보내자
			fileBw.write(buf);
			
			//파일 내부에 엔터를 누른거와 같이 새로운 빈행을 넣어 기록(내보내자)
			fileBw.newLine();
			
		}
		
		//작업이 모두 완료되면 모든 스트림통로는 메모리를 차지 하므로 제거 
		keyBr.close();
		fileBw.close();
	
	}

}





