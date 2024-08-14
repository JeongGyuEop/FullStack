
// 세 개의 숫자를 각각 상수에 저장
const num1 = 10;
const num2 = 20;
const num3 = 15;

// 삼항조건연산자를 사용하여 가장 큰 수 찾기
// const max = 조건식 ? 조건식 참_실행코드 : 조건식 거짓_실행코드;
const max = num1 > num2 ? (num1 > num3 ? num1 : num3) : num2 > num3 ? num2 : num3;
// 순서 1
// num1이 num2보다 큰지를 비교하면 num1은 10, num2는 20으로 거짓_실행코드인
// num2 > num3 ? num2 : num3 삼항연산자 식 부분으로 넘어간다.

// 순서 2
// 거짓_실행코드의 삼항연산자 식에서의 조건문인 num2 > num3을 비교한다.

// 순서 3
// num2가 num3 보다 큰지 비교하면 num2는 20, num3은 15이기 때문에 참_실행코드인 
// num2가 최종 선택되어 실행된다.

console.log('가장 큰 수:', max);
