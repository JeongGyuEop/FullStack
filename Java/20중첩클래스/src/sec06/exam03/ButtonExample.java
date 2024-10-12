package sec06.exam03;

// 버튼 설계도(클래스) 만들기
class Button {
	
	// 	정적 중첩 인터페이스 만들기
	// - 외부 클래스에서 접근이 불가능하도록
	//	  public이면서 Button 객체 생성없이 사용할 수 있는
	//    static 중첩 인터페이스로 만듦
	public static interface ClickListener {
		// 클릭하는 기능의 의미를 지니고 있는 추상메소드
	    void  onClick();
	}
	// 바깥 Button 클래스의 멤버(인스턴스변수) 만들기
	private ClickListener clickListender;
						// new OkListener(); 자식 객체의 주소를 대입할 수 있을 것이다.
						// new CancelListener(); 
	
	// 바깥 button 클래스의 멤버(인스턴스메소드) 만들기
	// 기능 : 위 만들어 놓은 clickListner인스턴스 변수값 변경 
	public void setClickListener(ClickListener clickListender) {
											// new OkListener();
											// new CancelListener();
		this.clickListender  = clickListender;
	}
	
	// Button이 클릭되었을 떄 실행할 메소드로 click 인스턴스 메소드 만들기
	// 실행 내용
	// 위 private ClickListender 인터페이스 자료형 clickListener 인스턴스 변수에 
	// 저장된 자식객의 onClick() 추상메소드를 호출한다.
	public void click( ) {
		this.clickListender.onClick();
	}


}

//--------------------------------------------------------------------------------------

public class ButtonExample {

	public static void main(String[] args) {
		
		// 버튼 역할을 하는 클래스(설계도)의 객체 생성
		// -> Button 클래스를 이용해 객체 생성
		Button btnOk = new Button();
		
		//클릭 이벤트를 처리할 크래스 OkListener 만들기
		// 만드는 방법 :
		// Button 클래스 내부에 중첩 ClickListener 인터페이스 내부의 
		// 추상메소드를 강제로 오버라이딩해서 로컬 중첩클래스 형태로 만든다.
		class OkListener implements Button.ClickListener {
			
			// 버튼을 클릭했을 때 호출되는 메소드로 
			// 클릭이벤트를 했을 때 이벤트 처리코드를 작성하는 
			@Override
			public void onClick() {
				// 재구현
				System.out.println("Ok 버튼을 클릭했으니 선물을 줄게요!");
				
			}
			
		} // ------- OkListener 로컬 중첩클래스 닫기
	
		// 버튼을 클릭할 때마다 이벤트 처리 메소드 onClick을 오버라이딩 해 놓은 OkListener중첩 로컬
		// 클래스의 객체 생성 후 Button 객체의 private ClickListener clickListener 변수에
		// 저장해 놓아야 클릭이벤트가 발생했을 때 이벤트 처리할 수 있다.
		btnOk.setClickListener(new OkListener());
		
		// OK 버튼을 클릭하는 행위(이벤트) 하기
		btnOk.click();
		
		//--------------------------------------------------------------------------
	}

}

