

/*
 	결론.
 		메소드 오버라이딩시~~
 		상속되지 않고 은닉된 부모클래스의 메소드와 같이
 		부모클래스에 정의된 변수와 같은 이름을 가진 변수를 자식클래스에서 선언하는데
 		이러한 경우에도 부모클래스의 변수는 자식 클래스에서 사용할수 없게 된다.
 */

// 예제. 부모클래스의 변수를 상속받아 사용하는예

class Point2D { // 부모클래스
	protected int x = 10;
	protected int y = 20;
}

class Point3D extends Point2D {
	// 부모클래스의 변수 명과 같은 변수 명으로 자식 클래스에 변수를 만들어 놓으면
	// 부모클래스의 변수들은 자식클래스에 상속되지않고 은닉 보호되어 사용할 수 없게 된다.
	protected int x = 40;
	protected int y = 50;
	protected int z = 30;
	
	// 메소드
	public void print() {
		// x, y, z 변수의 값을 불러와 출력
//		System.out.println(x + "," + y + "," + z);
		System.out.println(super.x + "," + super.y + "," + z);
	}
	
}


public class Ex4 {

	public static void main(String[] args) {
		Point3D point3D = new Point3D();
		point3D.print(); // 40,50,30
						 // x, y, z
		// 출력된 이유 :
		// Point3D 자식클래스 내부에 x, y변수를 추가로 작성한 후
		// 똑같은 이름의 x,y 변수가 Point2D 부모 클래스에 있으므로
		// 상속받지 않았으므로 Point3D 자식의 x,y변수값 40,50이 출력된 것이다.
	}

}
