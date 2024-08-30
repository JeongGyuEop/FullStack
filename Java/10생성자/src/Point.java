

//주제  : 생성자 만들어 보고 사용해보기 
public class Point {
	//정수값을 각각 하나씩 저장할 x, y 변수 선언
	int x;
	int y;
	
	//기본생성자 선언
	public Point() {
		//x변수값 2초기화
		x = 2;
	
	    //y변수값 2초기화
		y = 2;
		
		//"Point 기본생성자 호출됨" <- 출력
		System.out.println("Point 기본생성자 호출됨");
	}
	
	//x변수의 값만 초기화 시킬 생성자 선언
	public Point(int newX) {
		x = newX;
	}
	
	//x,y변수의 값 모두를 초기화 시킬 생성자 선언
	public Point(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	//Point객체 메모리 내부에 설정된 x변수의 값을 변경할 setX메소드 선언
	void setX(int x_) {
		x = x_;
	}
	
	//Point객체 메모리 내부에 설정된 y변수의 값을 변경할 setY메소드 선언
	void setY(int y_) {
		y = y_;
	}
	
	//Point객체 메모리 내부에 설정된 x변수값을 얻기(반환) 위한 getX메소드 선언
	int getX() {
		return x;
	}

	//Point객체 메모리 내부에 설정된 y변수값을 얻기(반환) 위한 getY메소드 선언
	int getY() {
		return y;
	}
	
	//Point객체 메모리 내부에 설정된 x,y변수값 불러와 출력하는 기능의 prn메소드 선언
	void prn() {
		//출력형식
		//"출력 x,y : x변수값, y변수값"
		System.out.println("x, y : " + x + ", " + y);
	}
	

	public static void main(String[] args) {
		
		//Point클래스 하나를 이용해 Point객체 생성시 기본생성자 호출해서 생성
		Point point = new Point();
		
		//생성된 Point객체의 x변수값, y변수값 불러와서 출력
		//출력형태
		//"객체변수 x = 2"
		//"객체변수 y = 2"
		System.out.println("객체변수 x = " + point.x);
		System.out.println("객체변수 y = " + point.y);
		
		
		//prn메소드 호출하여 아래와 같이 출력
		//출력형태
		//x, y : 2, 2
		point.prn();
		
		//생성된 Point객체의 x변수값을 10으로 변경, y변수값은 20으로 변경
		point.x = 10;
		point.y = 20;
		
		//prn메소드 호출하여 아래와 같이 출력
		//출력형태
		//x, y : 10, 20 
		point.prn();
		
		
		//생성된 Point객체의 x변수값을 100으로 변경, y변수값은 200으로 변경
		//단! set으로 시작하는이름의 메소드들을 호출해서 변경
		point.setX(100);
		point.setY(200);
		
		
		//prn메소드 호출하여 아래와 같이 출력
		//출력형태
		//x, y : 100, 200
		point.prn();
		
		
		//생성된 Point객체 내부에 만들어져 있는 getX(), getY()각각 호출해서
		System.out.println(point.getX());
		System.out.println(point.getY());
		//현재 이자리에 반환값을 각각 출력
		//출력형태
		//100
		//200
		
		System.out.println("===================");
		
		
		//Point객체 생성하는 동시에 x객체변수를 초기화할 생성자만 호출해서
		//x객체변수값만  300 으로 초기화 시킴
		Point point2 = new Point(300);
		
		//생성된 Point객체 내부에 만들어진 prn()메소드를 호출해서 출력
		//출력형태
		//출력 x, y : 300, 0
		point2.prn();
		
		
		//Point객체 생성하는 동시에 x객체변수 그리고 y객체변수를 각각 초기화할
		//생성자를 호출해서 x객체변수값은 300으로 초기화시키고 y객체변수값은 400으로 초기화시킴
		Point point3 = new Point(300, 400);
		
		//생성된 Point객체 내부에 만들어진 prn()메소드를 호출해서 출력
		//출력형태
		//출력 x, y : 300, 400
		point3.prn();
		
		

	}

}















