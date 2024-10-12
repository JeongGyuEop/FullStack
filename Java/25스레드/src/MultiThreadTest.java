

//작업1을 위한 보조작업스레드 클래스 만들기
class Task1 extends Thread{
	//run메소드 오버라이딩
	@Override
	public void run() {
		//작업1
		for(int i=0;  i<10;  i++) {
			System.out.print(i);
		}
	}
}

//작업2를 위한 보조작업스레드 클래스 만들기
class Task2 extends Thread{
	//스레드가 처리할 일은 무조건 run메소드 내부에 작성
	@Override
	public void run() {
		//작업2
		for(char i='A';  i<='Z'; i++){
			System.out.print(i);
		}
	}	
}

//작업3을 위한 보조작업스레드 클래스 만들기
class Task3 extends Thread{
	@Override
	public void run() {
		//작업3
		for(char i='a';  i<='z'; i++){
			System.out.print(i);
		}
	}
}

//작업4를 위한 보조작업스레드 클래스 만들기
class Task4 implements Runnable{
	@Override
	public void run() {
		//작업4
		char arr[] = {'ㄱ', 'ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅈ'};
		for(char i : arr){
			System.out.print(i);
		}
	}
}


//다음 싱글 스레드에서 처리하는   많은 작업들을  각각의 작업스레드들을 만드는 방식으로 바꾸시오.
//힌트! :  각각 작업스레드 클래스 따로 만들기 
public class MultiThreadTest {
	
	//싱글(메인)스레드
	public static void main(String[] args) {
		
		Task1 task1 =  new Task1();
		Task2 task2 =  new Task2();
		Task3 task3 =  new Task3(); //run메소드 실행됨
			
		Thread task4 = new Thread(new Task4());
			
		task1.start();
		task2.start();
		task3.start();
		task4.start();
		
	}

}
//실행결과 
//0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzㄱㄴㄷㄹㅁㅂㅅㅈ

//과제 내용~~
/*
실행결과를 보면.. 메인(싱글)스레드에서 4가지의 작업을 하고 있다.
각각의 작업스레드 클래스를 만들어서   멀티스레딩  방식으로 바꾸세요~~~  
*/










