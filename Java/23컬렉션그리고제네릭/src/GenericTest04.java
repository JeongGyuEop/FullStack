/*
 제네릭 타입이란?
 - 클래스를 설계할떄 변수등이 아직 결정 되지 않는 타입을 말한다.
 - 객체를 생성할떄 제네렉타입이 결정 된다.

 제네릭 타입을 이용한 클래스 또는 인터페이스란?
 - 변수의 타입이 결정되어 있지 않는 클래스와 인터페이스를 말한다.
*/

//제넥릭 클래스 설계
class GenericClass<T>{
	
	private  T  member; //현재 member변수는 아직 결정되지 않는 T 제네렉타입을 지정해서 
						//만든 변수 입니다.
	
	//제네릭 메소드 
	public void setValue(T value) {//매개변수 또한 아직 결정되지 않은 T제네릭타입을 지정해서
								   //매개변수를 만들어 놓을 수 있다.	
		this.member = value;
	}
	
	public T getValue() {
		return this.member;
	}
}
			

class Product<K,M>{ //다양한 종류와 모델제품을 저장하기 위해 제네릭 타입 지정
	
	//변수
	private K kind;   //Tv kind;  또는  Car  kind;
	private M model;  //String model;  또는  String model;
	
	//메소드
	//	 Car
	//    Tv
	public K getKind() {  return this.kind; }	
	//					Car
	//					Tv
	public void setKind(K kind) {  this.kind = kind; }
	//	  String
	//    String
	public M getMode() {  return this.model;  }
	//					String
	//					String
	public void setModel(M model) { this.model = model;  }
	
}

class Tv{}

class Car{}


//Rentable인터페이스를 제네릭 타입으로 선언해보자.
//다양한 대상을 렌트하기 위해 rent()메소드의 리턴타입을 제네릭타입으로 선어합니다.
interface Rentable<P>{
	
	P rent();
	
}

//렌트 대상인 Home과 Car2클래스 작성

class Home{
	public void turnOnLight() {
		System.out.println("전등을 켭니다.");
	}
}

class Car2{
	public void run() {
		System.out.println("자동차가 달립니다.");
	}
}

//HomeAgency와  CarAgency는 집과 자동차를 렌트해주는 대리점 클래스로,
//Rentable의 타입 파라미터를 Home과  Car로 대체해서 구현하는 방법을 보여줍니다.
class HomeAgency implements Rentable<Home> {
	
	//메소드 오버라이딩
	//리턴타입은 반드시~! Home이어야 합니다.
	@Override
	public Home rent() {
		return new Home();
	}	
}

class CarAgency implements Rentable<Car2>{
	
	//메소드 오버라이딩
	//리턴타입은 반드시~! Car2이어야 합니다.
	@Override
	public Car2 rent() {		
		return new Car2();
	}
	
}


public class GenericTest04 {

	public static void main(String[] args) {
		
		HomeAgency homeAgency = new HomeAgency();
		Home  home = homeAgency.rent();
		home.turnOnLight(); //전등을 켭니다.
		
		System.out.println("-------------------------------------");
		
		CarAgency carAgency = new CarAgency();
		Car2 car2 = carAgency.rent();
		car2.run(); //자동차가 달립니다.
		
		
		System.out.println("---------------------------------------");
		
		//K는 Tv로 , M은  String으로 대체 
		Product<Tv, String>  product1 = new Product<Tv, String>();
		
		//setter메소드 호출시 매개변수로 반드시 Tv객체주소와 String문자열객체 전달 하여 저장 해야함
		product1.setKind(new Tv());
		product1.setModel("스마트Tv");
		
		//getter메소드 호출하여 각 변수에 저장된 값들을 반환받아 저장
		Tv tv = product1.getKind();
		String tvModel = product1.getMode();
		//-------------------------------------------------------------------
		
		//K는 Car로 대체 , M은 String으로 대체 
		Product<Car, String> product2 = new Product<>();
		
		product2.setKind(  new Car()   );
		product2.setModel( "SUV자동차"   );
		
		Car car = product2.getKind();
		String carModel = product2.getMode();
		
		
	
		
		
		
		//클래스의 객체를 생성할때 <>오브 기호 안에 제네릭타입을 작성해 놓으면
		//제네릭클래스에 모든 변수나 반환타입이 <>오브 기호 안에 적었던 제넥타입으로 변경된다.
		GenericClass<Double>   obj01 = new GenericClass<Double>(); 
	
		obj01.setValue(3.4); //오토박싱되어 new Double(3.5)객체의 주소가 전달
		
		System.out.println("되돌려 받는 데이터 : " +   obj01.getValue() );
		
		
		
		GenericClass<Integer>  obj02 = new GenericClass<Integer>();
		
		obj02.setValue( new Integer(10) );

		System.out.println("되돌려 받는 데이터 : " +   obj02.getValue());
		
		

		GenericClass<String>  obj03 = new GenericClass<String>();
		
		obj03.setValue("이해할수 있다.");
		
		System.out.println("되도렬 받는 데이터 : " +   obj03.getValue());
		
		
		

	}

}











