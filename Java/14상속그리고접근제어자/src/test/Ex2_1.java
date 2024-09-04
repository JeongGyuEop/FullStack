

package test;


//주제 : 중복되는 코드를 재활용하는 상속의 장점


//2차원 평면의 좌푯값을 저장하는 Point2D클래스 설계
//Point2D클래스는 변수로  x,y를 갖고,
//이좌표값은 2차원 좌표계에서의 점을 나타냅니다.
class Point2D{
	private int x;
	private int y;
	//x변수-> getX()메소드, setX(int x)메소드
	//y변수-> getY()메소드, setY(int y)메소드
	//get으로 시작하는 메소드들을 getter라고 부르고
	//set으로 시작하는 메소드들을 setter라고 부릅니다
	
	//setter,getter메소드들을 자동으로 만드는 단축키
	//alt + shift + s  r
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}	
}

//3차원 공간상의 좌푯값 한점을 나타내는 x,y,z를 저장하는 Point3D클래스 설계
//-> Point3D클래스를 설계할때 Point2D클래스의 x,y, getX(), setX(int x)상속받아
//   새롭게 설계 하자.
//문법
//class 새롭게만드는자식클래스명 extends 상속받을부모클래스명{     }

class Point3D extends Point2D {
	
	private int z;
	
	//setter, getter메소드들 만들기
	//alt + shift + s  r
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
		
}


public class Ex2_1 {
	public static void main(String[] args) {
		
		//Point3D 자식클래스의 객체 생성
		Point3D p3d = new Point3D();
		
		p3d.setX(10); //상속받은 부모Point2D객체의 인스턴스변수에 저장
		p3d.setY(20); //상속받은 부모Point2D객체메모리내부의 인스턴스변수에 저장
		p3d.setZ(30); //Point3D객체 메모리 내부의 인스턴스변수에 저장
		
		System.out.println(  p3d.getX() + ","     
				           + p3d.getY() + ","
				           + p3d.getZ()
				           );
		

	}

}









