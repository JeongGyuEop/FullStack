// 주제 : 삼항조건연산자식을 이용하여 엘레베이터가 올라가고 내려가는 동작을 구현.
//       이 경우 각 버튼을 누를 때의 동작을 별도의 코드로 나누어 작성 가능.

let currentFloor = 1; // 엘레베이터 상태 정의
let button = ''; // 엘레베이터 버튼 정의 'up' 또는 'down'

// 위쪽 화살표 버튼 눌렸을 때
button = 'up';
let direcrion = button === 'up' ? 'up' : 'down';
currentFloor = direcrion === 'up' ? currentFloor + 1 : currentFloor - 1;
console.log(`엘레베이터가 ${direcrion === 'up' ? '올라갑니다' : '내려갑니다'}.
  현재 층: ${currentFloor}`);

// 아래쪽 화살표 버튼 눌렸을 때
button = 'down';
direcrion = button !== 'up' ? 'down' : 'up';
currentFloor = direcrion === 'down' ? currentFloor - 1 : currentFloor + 1;
console.log(`엘레베이터가 ${direcrion === 'down' ? '내려갑니다' : '올라갑니다'}. 
  현재 층 : ${currentFloor}`);
