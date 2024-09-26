
//Swing단원(GUI환경을 구성하고 이벤트를 처리할 단원)
	//제공되는 클래스 중에서 다이얼로그창(메세지 박스)를 화면에 띄워 보여줄
	//JOptionPane클래스를 제공합니다.

import javax.swing.JOptionPane;


public class SingleThreadEx2 {

	//메인스레드
	public static void main(String[] args) throws Exception {
		
		//작업1 : 다이얼로그창을 띄워 입력받은 내용을 화면에 출력하는 작업
		String input = JOptionPane.showInputDialog("아무것이나 입력하세요");
		System.out.println("입력한신 값은? " + input + " 입니다.");
		
		//작업2 : 카운트 세는 작업  10 9 8 7 6 5 4 3 2 1 이순서대로 1씩 감소 하면서 반복해서 출력
		for(int i=10;  i>0;    i--) {
			
			System.out.println(i);
			Thread.sleep(1000);//메인스레드에게 1초 휴식
		}	
		
	}

}








