

// 주제 : 부모클래스 A와 자식클래스 B가 다른 패키지에 있는 경우
//		  다양한 접근 제어자를 지정한 변수에 접근하는 실습

package PA;

public class A { // PA 패키지 안에 있는 A부모 클래스

	          int i; // default 접근제어자 사용
	protected int pro;
	private   int pri;
	public    int pub;
	
	public void print() {
		System.out.print("i=" + this.i + ", ");
		System.out.print("pro=" + this.pro +", ");
		System.out.print("pri=" + this.pri +", ");
		System.out.println("pro=" + this.pub +"입니다.");
	}
	
}

// 다른 PB 패키지에 만들어 놓은 B 자식 클래스에서 접근 해보자
