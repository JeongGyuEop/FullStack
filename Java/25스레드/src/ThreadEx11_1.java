

class MyThread  implements Runnable{

	boolean suspended = false;
	boolean stoped = false;
	
	Thread th;
	
	public MyThread(String name) {
		th = new Thread(this,name);
	}
	
	void start() {    th.start();   }
	void stop()  {    stoped = true; }
	void suspend() {  suspended = true;    th.interrupt(); } //스레드 작업중단(휴식)
	void resume()  {  suspended = false;   th.interrupt(); } //스레드 작업중단(준비)
	
	@Override
	public void run() {	
		while(!stoped) {			
			if(!stoped) {
				//현재 실행중인 스레드 1초 휴식
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() +  " : 출력");
			}else {
				//현재 실행중인 자기자신의 스레드를 중단하여
				//다른스레드에게 일을 양보하게 합니다.
				Thread.yield();
			}		
		}//while
	}//run
}//class



public class ThreadEx11_1 {
	public static void main(String[] args) {
		
	 Thread thread1	 = new Thread( new MyThread("첫번째스레드") );
	 Thread thread2  = new Thread( new MyThread("두번째스레드") );
	 Thread thread3  = new Thread( new MyThread("세번째스레드") );
	 
	 		thread1.start();
	 		thread2.start();
	 		thread3.start();
	 		
	 
	 
	 

	}

}

















