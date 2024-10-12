import javax.swing.JOptionPane;


//사용자 정의 보조작업 스레드 객체를 만들기 위한 스레드 클래스 만들기
class MultiThreadDemo2  extends Thread  {
		
	//보조작업스레드가 할일 을 재구현 
	@Override
	public void run() {

		//작업2 : 카운트 세는 작업  10 9 8 7 6 5 4 3 2 1 이순서대로 1씩 감소 하면서 반복해서 출력
		for(int i=10;  i>0;    i--) {
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);//보조작업 스레드에게 1초 휴식
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//for	
		
	}
	
}


public class MultiThreadEx2 {

	//메인스레드 
	public static void main(String[] args) {

		//보조 작업 스레드 객체 생성하는 일과 보조작업스레드 일시키기
		new MultiThreadDemo2().start();
		
		
		//작업1 : 다이얼로그창을 띄워 입력받은 내용을 화면에 출력하는 작업
		String input = JOptionPane.showInputDialog("아무것이나 입려하세요");
		System.out.println("입력한신 값은? " + input + " 입니다.");
		
		
		
	}

}












