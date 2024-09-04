

// (x, y)의 한 점을 표현하는 Point 클래스와 이를 상속받아 점에 색을 추가한
// ColorPoint 클래스를 만들고 활용해보자.

class Point {
	private int x, y; // 한 점을 구성하는 x, y좌표
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void showPoint() { // 점의 좌표 출력
		System.out.println("(" + x + "," + y + ")");
		
	}
}

class ColorPoint extends Point {
	private String  color; // 점의 색 
	public void setColor(String color) {
		this.color = color;
	}
	public void showColorPoint () { // 컬러 점의 좌표 출력
		System.out.print(color);
		showPoint() ;// Point 의 showPoint() 호출
	}
}

public class ColorPointEx {

	public static void main(String[] args) {
		Point p = new Point(); // Point 객체 생성
		p.set(1,2); // Point 클래스의 set() 호출
		p.showPoint();
		
		ColorPoint cp = new ColorPoint();
		cp.set(3, 4);
		cp.setColor("red");
		cp.showColorPoint();
		// Point 클래스의 set() 호출
		// ColorPoint의 setColor() 호출
		// 컬러와 좌표 출력
		

	}

}
