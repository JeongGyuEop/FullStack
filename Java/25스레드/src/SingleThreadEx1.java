
public class SingleThreadEx1 {

	//메인스레드
	public static void main(String[] args) {

		//System.currentTimeMillis() 메소드
		//: 1970년 1월 1일 0시 0분 0초 부터 현재까지 경과된 시간을
		//  long값(1/10000초 단위값)으로 리턴
		
		//메인스레드가 작업을 할때 걸린 시간을 측정하기 위해 일단 저장합니다.
		long startTime = System.currentTimeMillis();
		
		//메인스레드 작업1
		//10번 반복하여 1초간격으로  *을 하나씩 출력하는 작업
		for(int i=0;  i<10;  i++) {
			
			try {
				Thread.sleep(1000);//메인 스레드 1초 휴식
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
			System.out.print("*");
		}//for
		
		//메인스레드 작업1을 하는데 소요된 시간 구하기 : 
		//(* 을 10번 출력한 후의 현재시간 -  * 10번 출력하기전 작업1시간) / 1000
		System.out.println("메인스레드 작업1 소요된 시간 : " 
							+ (System.currentTimeMillis()- startTime) / 1000 + "초");
		
		//메인스레드 작업2
		//10번 반복하여 1초 간격으로 # 을 하나씩 출력하는 작업
		for(int i=0;   i<10;  i++) {
			
			try {
				Thread.sleep(1000);//메인 스레드 1초 휴식
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
			System.out.print("#");			
		}
		
		//메인스레드 작업2을 하는데 소요된 시간 구하기 : 
		//(# 을 10번 출력한 후의 현재시간 -  # 10번 출력하기전 작업1시간) / 1000
		System.out.println("메인스레드 작업2 소요된 시간 : " 
							+ (System.currentTimeMillis()- startTime) / 1000 + "초");		
		
		
	}//main
	
	/*
	결론 : 메인스레드(main메소드)는 하나이기때문에 싱글스레드라고 부른다.
	      싱글스레드하나로 2개의 작업을 하면  
	      하나의 작업을 모두 마친 후~ 그다음 작업을 해야하기 떄문에
	      그다음 작업을 하기 위해서는 그전의 작업을 모두 마쳐야 
	      그다음 작업을 할수 있는 단점이 있다.
	*/

}












