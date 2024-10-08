// 주제 : typeof 연산자와 삼항조건연산자를 함께 사용하는데
// 변수의 자료형에 따라 다른 동작을 수행하는 예제

// varA 이름의 상수 메모리를 만들고, "안녕하세요" 문자열 저장
const varA = '안녕하세요';

// 삼항조건연산자 식 문법
// 조건식 ? 조건식의 결과가 참일 때 실행할 코드 : 조건식의 결과가 거짓일 때 실행할 코드

// varA 상수에 저장된 자료형이 string 문자열이라면?
typeof varA === 'string' ? console.log('문자열') : console.log('문자열 X');
