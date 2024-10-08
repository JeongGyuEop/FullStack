// 주제 : 삼항 조건연산자식을 이용하여 변수에 저장된 값이
// 홀수인지 짝수인지 판별하는 간단한 예

let num = 1;
let result;

// num 변수에 저장된 값이 짝수이면 result 변수에 "짝수" 문자열을 저장하고,
// num 변수에 저장된 값이 홀수이면 result 변수에 "홀수" 문자열을 저장하자.
num % 2 === 1 ? (result = '홀수') : (result = '짝수');

num = 10; // 1 -> 10 저장시킴

// num 변수에 저장된 값이 짝수가 아니면?에 대한 조건식 작성
num % 2 !== 0 ? console.log('홀수이다.') : console.log('짝수이다.');
