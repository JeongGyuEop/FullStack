


class Tree {
	
	// 멤버변수 - 데이터(사과나무, 빨간색 사과, 100살) 저장용도
	String name;
	String color;
	int age;
	
	// 메소드 - 동작, 기능(흔들리다, 사과가 떨어지다) 표현 용도
	void move() {
		System.out.println("흔들리다.");
	}
	
	void dowon() {
		System.out.println("떨어지다.");
	}
	
}





public class Ex2_ex {
	
	public static void main(String[] args) {

		Tree tree1 = new Tree();
		
		tree1.name = "사과나무";
		tree1.color  = "빨간색 사과";
		tree1.age = 100;
		tree1.move();
		tree1.dowon();
		
		Tree tree2 = new Tree();
		
		tree2.name = "배나무";
		tree1.color  = "파란색 배";
		tree1.age = 99;
		tree1.move();
		tree1.dowon();
		

	}

}
