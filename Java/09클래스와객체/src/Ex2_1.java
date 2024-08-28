

// 원 클래스(설계도) 만들기
class Circle {
	
	// 변수
	// 원의 반지름 데이터를 저장할 변수
	double radius;
	
	
	// 메서드
	// 매개변수 newRadius로 전달받은 반지름의 값을 double radius 변수에 저장할 메소드
	public void setRadius(double newRadius) {
		
		radius = newRadius;
		
	}
		
	// 원의 면적을 계산해서 반환(제공)하는 기능의 메소드
	// 원의 면적 : 반지름 X 반지름 X 3.14
	public double calculateArea() {
		return radius * radius * 3.14;
	}
	
}








// 사각형 클래스(설계도) 만들기
class Rectangle {
	// 변수
	double width;
	double height;
	
	// 메소드
	// Rectangle 객체를 생성하여 객체 메모리 내부에 만들어진 객체 변수 width와 height의 값을
	// 새로 변경하여 저장시킬 기능의 setDimensions 메소드 정의
	public void setDimensions(double newWidth, double newHeight) {
		width = newWidth;
		height = newHeight;
	}
	
	// 사각형의 면적을 계산해서 반환(제공)하는 calculateArea메소드 정의
	public double calculateArea() {
		return width * height;
	}
	
	
	
}






public class Ex2_1 {

	public static void main(String[] args) {

		// 객체를 생성해서 사용
		// 1. Circle 클래스 설계도를 이용해서 Circle 객체를 생성
		Circle circle = new Circle();
		
		circle.setRadius(5);		
		
		System.out.println(circle.calculateArea());
		
//					  = 						0x16   
//					    ----------------------------------------------------
//					    |     	// 변수
//						|		// 원의 반지름 데이터를 저장할 변수
//						|		double radius; [0.0] -> [5.0]
//						|
//						|
//						|		// 메서드
//						|		// 매개변수 newRadius로 전달받은 반지름의 값을 double radius 변수에 저장할 메소드
//						|		public void setRadius(double newRadius) {
//						|	
//						|				radius = newRadius;
//						|	
//						|		}
//						|	
//						|		// 원의 면적을 계산해서 반환(제공)하는 기능의 메소드
//						|		// 원의 면적 : 반지름 X 반지름 X 3.14
//						|		public double calculateArea() {
//						|			return radius * radius * 3.14;
//						|		}
//					    ----------------------------------------------------
		
		Circle circle2 = new Circle();
		
		circle2.radius = 3;
		
		// 원 면적을 제공받기 위해 객체메소드 calculateArea() 호출
		double result = circle2.calculateArea();
		
		System.out.println(result);
		
		
//		  = 						0x16   
//	    ----------------------------------------------------
//	    |     	// 변수
//		|		// 원의 반지름 데이터를 저장할 변수
//		|		double radius; [0.0]
//		|
//		|
//		|		// 메서드
//		|		// 매개변수 newRadius로 전달받은 반지름의 값을 double radius 변수에 저장할 메소드
//		|		public void setRadius(double newRadius) {
//		|	
//		|				radius = newRadius;
//		|	
//		|		}
//		|	
//		|		// 원의 면적을 계산해서 반환(제공)하는 기능의 메소드
//		|		// 원의 면적 : 반지름 X 반지름 X 3.14
//		|		public double calculateArea() {
//		|			return radius * radius * 3.14;
//		|		}
//	    ----------------------------------------------------
	
		
// --------------------------------------------------------------------------------------------------------------
		
		// Rectangle 클래스를 이용하여 객체 생성하기
		Rectangle rectangle = new Rectangle();
		
		rectangle.setDimensions(4, 6);
		double result2 = rectangle.calculateArea();
		System.out.println(result2);
		
		/*
						    = 
						    -------------------------------------------------
						    |	// 변수
							|	double width; [0.0] -> [4.0]
							|	double height; [0.0] -> [6.0]
							|
							|	// 메소드
							|	// Rectangle 객체를 생성하여 객체 메모리 내부에 만들어진 객체 변수 width와 height의 값을
							|	// 새로 변경하여 저장시킬 기능의 setDimensions 메소드 정의
							|	public void setDimensions(double newWidth, double newHeight) {
							|		width = newWidth;
							|		height = newHeight;
							|	}
							|
							|	// 사각형의 면적을 계산해서 반환(제공)하는 calculateArea메소드 정의
							|	public double calculateArea() {
							|		return width * height;
							|	}
						    -------------------------------------------------
		*/
		


		
		
		
		
	}

}
