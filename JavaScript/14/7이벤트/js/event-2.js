// <button>Click</button> 요소를 선택해서
// button 상수에 저장

const button = document.querySelector('button');

// 선택해 온 <button>Click</button>요소에
// click 이벤트 등록 후, click 이벤트가 발생하면
// 자동으로 호출될 콜백함수 연결
// 문법
//   선택한 요소.on등록할 이벤트 종류명 = 이벤트 핸들러 함수 호출
button.onclick = function () {
  // body 요소의 배경색을 그린색으로 설정
  document.body.style.backgroundColor = 'green';
};
