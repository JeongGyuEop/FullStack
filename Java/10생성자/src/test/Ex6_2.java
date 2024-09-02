package test;

class Student {
	String name;
	int ban;
	int no;
	int kor;
	int eng;
	int math;
	
	public Student(String name, int ban, int no, int kor, int eng, int math) {
		this.name = name;
		this.ban = ban;
		this.no = no;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	String info() {
		return name + ", " +ban + ", " + no + ", " + kor + ", " + 
				eng + ", " + math + ", " + getTotal() + ", " + getAverage();
	}
	
	public int getTotal() {
		return kor + eng + math;
	}
	
    public float getAverage() {
        return Math.round((getTotal() / 3.0) * 10) / 10.0f;
    }
}

public class Ex6_2{
	public static void main(String[] args) {
		Student s = new Student("홍길동", 1, 1, 100, 60, 76);
		
		String str = s.info();
		System.out.println(str);
		
		// 총점과 평균을 출력
        int total = s.getTotal();
        float average = s.getAverage();

        System.out.println("이름: " + s.name);
        System.out.println("총점: " + total);
        System.out.println("평균: " + average);
	}
}
