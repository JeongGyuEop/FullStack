
public class ThreadEx04 {

	//메인스레드
	public static void main(String[] args) {
		
		//보조작업스레드 객체 생성
		ThreadDemo3 td = new ThreadDemo3();
					td.start(); 
					//새로운 보조작업스레드가 생성되어 보조작업스레드의 일을 실행시키고
					//다시~~~~ 메인스레드(main메소드)로 돌아온다.
		/*			
			java.lang.Exception
			at ThreadDemo3.throwException(ThreadDemo3.java:17)
			at ThreadDemo3.run(ThreadDemo3.java:11)
		*/
								
					td.run();//새로운 보조작업스레드 객체에 만들어져있는 run이 실행되는 것이 아니라
							 //main스레드 내부에서 run()메소드를 호출하는 스레드 작업을 하는것임
		/*
		 java.lang.Exception
		 at ThreadDemo3.throwException(ThreadDemo3.java:17)
		 at ThreadDemo3.run(ThreadDemo3.java:11)
		 at ThreadEx04.main(ThreadEx04.java:18)  <----- main스레드로와서 run을 호출한 것임 		 			
		 */

		/*			
			결론1. start()메소드 호출시 보조작업스레드가 생성되어 실행되고,
			      run메소드를 새로운 스택공간에 올려 놓아 호출하게 됨
			      
			결론2. run()메소드를 직접 호출하면 보조작업스레드가 생성되어 실행되는 것이 아니라
			      main스레드에서 생성된 객체의 run메소드만 ? 호출하는 것이다.    
			    			
		*/			
					
	}

}








