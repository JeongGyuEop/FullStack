package test2;

public class Car {

	// 필드 선언
	String company = "현대자동차";
	String model;
	String color;
	int maxSpeed;
	
	Car(String model) {
		// 아래의 매개변수가 3개인 생성자 호출
		this(model, "은색", 250);
	}
	
	Car(String model, String color) {
		// 아래의 매개변수가 3개인 생성자 호출
		this(model, color, 250);
	}
	
	Car(String model, String color, int maxSpeed) {
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}

}
