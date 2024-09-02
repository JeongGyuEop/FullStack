
public class ArrayUtils {

	// 정수들이 저장될 1차원 배열 메모리 주소 하나를 매개변수로 전달 받아서
	// 배열에 저장된 모든 데이터들의 합을 계산한 후 반환하는 기능의 sum 메소드 정의
	public int sum(int[] intArr) {
		int sum = 0;
		for(int num : intArr) {
			sum += num;
		}
		return sum;
	}
	
	// 실수들이 저장될 1차원 배열 메모리 주소 하나를 매개변수로 전달 받아서
	// 배열에 저장된 모든 데이터들의 합을 계산한 후 반환하는 기능의 sum 메소드 정의
	public double sum(double[] doubleArr) {
		double sum = 0.0;
		for(double num : doubleArr) {
			sum += num;
		}
		return sum;
	}
	
	// 정수들이 저장될 1차원 배열 메모리 주소 하나를 매개변수로 전달 받아서
	// 배열에 저장된 정수들의 합을 계산해서 출력하는 기능의 printSum메소드 정의
		// 구현 내용
		// sum메소드를 호출하여 결과 출력!
		// 출력 형식 "정수 배열의 합: 숫자"
	public void printSum(int[] intArr) {
		System.out.println("정수 배열의 합: " + sum(intArr));
	}
	
	// 실수들이 저장될 1차원 배열 메모리 주소 하나를 매개변수로 전달 받아서
	// 배열에 저장된 실수들의 합을 계산해서 출력하는 기능의 printSum메소드 정의
		// 구현 내용
		// sum메소드를 호출하여 결과 출력!
		// 출력 형식 "실수 배열의 합: 숫자"
	public void printSum(double[] doubleArr) {
		System.out.println("실수 배열의 합: " + sum(doubleArr));
	}
	
	
	
	public static void main(String[] args) {

		// ArrayUtils 클래스의 객체 생성
		ArrayUtils arrutil = new ArrayUtils();
		
		// 정수들 1, 2, 3, 4, 5 가 저장된 1 차원 배열 메모리 생성
		// 참조 변수 명은 intArray
		int[] intArray = new int[] {1, 2, 3, 4, 5};
		
		// 실수들 1.5, 2.5, 3.5 가 저장된 1차원 배열 메모리 생성
		// 참조 변수명은 doubleArray
		double[] doubleArray = new double[] {1.5, 2.5, 3.5}; 
		
		// 위 intArray 참조 변수에 저장된 배열 메모리의 합을 계산해서 출력
		// 출력형식 "정수 배열의 합: 15"
		arrutil.printSum(intArray);
		
		// 위 doubleArray 참조 변수에 저장된 배열 메모리의 합을 계산해서 출력
		// 출력형식 "실수 배열의 합: 7.5"
		arrutil.printSum(doubleArray);
	
	}

}
