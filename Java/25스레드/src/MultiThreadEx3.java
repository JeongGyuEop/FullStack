
import javax.swing.JOptionPane;


//보조작업 스레드 1 클래스 설계
//작업1 : 다이얼로그창에 입력받은 내용을 화면에 출력
//       단! 10초안에 입력하지 않으면 다이얼로그창을 강제로 종료시킵니다.
class InputThread extends Thread {
	
	@Override
	public void run() {
		//다이얼로그창에 입력받은 내용 화면에 출력
		System.out.println("10초 안에 값을 입력해야 창이 안꺼집니다.");

		String input = JOptionPane.showInputDialog("아무것이나 입력하세요");

		System.out.println("입력하신 값은? " + input + " 입니다.");
		
		MultiThreadEx3.inputCheck = true; //10초 안에 입력했다~ 판단할 값으로 true저장
	}
	
}

//보조작업 스레드 2 클래스 설계
//작업2 : 10  9  8 ..... 1 까지 1씩 감소하면서 반복해서 출력
class InputThread2 extends Thread{
	
	@Override
	public void run() {
		//작업2 : 카운트 세는 작업  10 ~ 1까지 1씩 감소시키면서 10 9 8 7 6 5 4 3 2 1 반복해서 출력
		for(int i=10;  i>0;    i--) {
			
			//사용자가 다이얼로그창에 10초안에 입력하면?
			//-> inputCheck변수에 저장된값이 true이면?
			if( MultiThreadEx3.inputCheck ) {
				return; //for반복문을 빠져나감
			}
	
			System.out.print(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//for
		
		System.out.println("10초 동안 값이 입력되지 않아 MultiThreadEx3을 강제종료 합니다.");
		System.exit(0);
	}//run
}

public class MultiThreadEx3 {
	
	//플래그변수? 조건을 판단하기 위해  참인지 거짓인지의 값중 하나를 저장 해 놓을 변수 
	//다이얼로그창에 10초안에 입력했음 -> true 저장
	//다이얼로그창에 10초안에 입력안했으면? -> false 저장
	static boolean inputCheck = false;
	//참고. 다른 보조작업스레드 객체들이 공용으로 사용하기 위한 체크변수임
	
	//메인스레드 
	public static void main(String[] args) {
		//보조작업스레드1 객체, 2객체 생성후 run이 호출되기 위해 start()메소드 호출하자
		new InputThread().start();    new InputThread2().start();

	}

}











