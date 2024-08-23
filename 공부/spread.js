// 문제 1. 배열 병합
// 배열 arr1과 arr2를 spread 문법을 사용하여 arr3으로 변경
const arr1 = [1, 2, 3];
const arr2 = [4, 5, 6];

const arr3 = [...arr1, ...arr2];
console.log(arr3);

// ------------------------------------------------------------------

// 문제 2. 최대값 구하기
// 배열 numbers에서 스프레드 문법을 사용하여 최댓값 구하기
const numbers = [10, 20, 30, 5, 15];
const maxNum = Math.max(...numbers);

console.log(maxNum);

// ------------------------------------------------------------------

// 문제 3. 객체 병합
// 객체 person과 details를 스프레드 문법을 사용하여 하나의 객체로 병합
const person = { name: 'John', age: 30 };
const details = { job: 'developer', country: 'USA' };

const mergedObject = { ...person, ...details };

console.log(mergedObject);

// -------------------------------------------------------------------

// 문제 4. 함수 호출
// 배열 args를 스프레드 문법을 사용하여 함수 multiply에 인자로 전달하고 결과를 출력
const args = [2, 3, 4];
const multiply = (a, b, c) => a * b * c;

const result = multiply(...args);
console.log(result);
