


// 직원 설계도(클래스)
// 클래스명 : Employee

class Employee { // 부모 역할을 한느 클래스
	
	// 문자열 변수 name(이름)
	String name;
	
	// 정수형(4) 변수 salary(급여, 봉급)
	int salary;
	
	// name, salary 문자열을 반환하는 getEmployee()메소드
	public String getEmployee() {
		return name + "," + salary;
	}
	
	// 기본 생성자 -> 아무일도 하지 않음
	public Employee() {
		
	}
	
	// name과 salary를 전달받아 초기화하는 생성자
	public Employee(String name, int salary) {
		super();
		this.name = name;
		this.salary = salary;
	}
	
}

// Employee 클래스를 상속받는 Manager 클래스 만들기
class Manager extends Employee {
	
	// 문자열 변수 depart(부서)
	String depart;
	
	// 완성되는 Manager 객체의
	// name, salary, depart 정보를 하나의 문자열로 반환하는 getManager() 메소드
	// 반환시 !! 부모 클래스의 getEmployee() 메소드 호출
	public String getManager() {
		return super.name + "," + super.salary + "," + this.depart;
	}
	
	// 완성되는 Manager 객체의 name과 salary 그리고 depart를 초기화하는 생성자
	public Manager(String name, int salary, String depart) {
		// Manager객체 메모리 영역에는 name,salary 인스턴스 변수가 없기 때문에
		// Employee 클래스 내부의 생성자를 호출해서 각각의 "이순신"은 name 인스턴스 변수에 저장하고,
		// 4000은 salary 인스턴스 변수에 직접 초기화
		super(name, salary);
		this.depart = depart;
		
	}
}


public class Test {

	public static void main(String[] args) {
		
		Employee emp = new Employee("홍길동", 2000);
		
		// Manager 자식 객체 생성
		Manager man = new Manager("이순신", 4000, "개발");
		/*
		 				자식 Manager 객체 생성하면
		 				전체 Manager 객체 메모리 하나가 생성되면서
		 				부모 객체 메모리도 만들어져서 포함되니
		 				생성된 Manager객체 메모리 주소는 하나다.
		 				
		 									0x11 
		  	[ 0x11 ] = -------------------------------------------------
		  					부모 Employee 객체 메모리 영역
		  						String name; [ "이순신" ]
		  						int salary;  [   4000   ]
		  
		  						pulic String getEmployee() {}
		  			   -------------------------------------------------
		  					자식 Manager객체 메모리 영역
		  						String depart; [ "개발" ]
		  						
		  						public String getManager() {}
		  
		  			   -------------------------------------------------
		 */
		
		System.out.println(emp.getEmployee());
		System.out.println(man.getManager());
	}

}
