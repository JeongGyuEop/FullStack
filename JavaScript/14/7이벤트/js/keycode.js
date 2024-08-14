// 문서의 body 요소 선택
const body = document.body;

// id 속성값이 'result'인 <div>요소 선택
const result = document.querySelector('#result');

// 이벤트 등록 방식 3. addEventListener메소드를 호출하여 등록
// 문법
// 요소선택.addEventListener("이벤트종류1 이벤트종류2 .. ", 이벤트핸들러함수);

// 선택한 body 요소에 키를 누르고 있는 동작(keydown)을 등록하여
// 사용자가 키를 누를 때마다 실행할 이벤트 핸들러 함수 작성
body.addEventListener('keydown', e => {
  // <div id="result"></div>.innerText = 입력한 키 정보;
  result.innerText = `
    code: ${e.code},
    key: ${e.key}
    `;
});

// 이벤트 핸들러 함수 (e)를 보면 e 매개변수는 이벤트 핸들러 함수가 자동으로
// 호출될 때 이벤트 분배 스레드에 의해 자동으로 전달되는 이벤트 객체
// 이 이벤트(Event) 객체는 이벤트가 발생했을 때의 다양한 정보를 지닌 객체이다.

//   1. e.type -> 이벤트의 유형을 제공해줌. 예를 들어 'keydown', 'keyup', 'click' 등
//   2. e.target -> 이벤트가 발생한 DOM 요소를 제공. 예를들어 사용자가 클릭한
//       <button></button>을 선택해서 가지고 온다.
//   3. e.code -> 눌린 키의 물리적 키 코드를 제공해 준다.
//                 키의 위치를 기준으로 하며, 예를 들어 :"KeyA"는 A키를 나타냄.
//   4. e.key -> 눌린 키의 실제 값을 제공해 준다.
//               예를 들어, shift 키를 누른 상태에서 A키를 누르면 e.key는 "A"가 된다.
//   5. e.ctrlKey, e.shiftKey, e.altKey ->
//       각각Ctrl, Shift, Alt 키가 눌렸는지를 나타내는 true 또는 false 반환
//   6. e.preventDefault()메소드 -> 기본 이벤트 <a href> href 속성은 클릭을 유도하는
//                                   기본 이벤트 동작을 차단하는 메소드
//         요약 : 링크 클릭 시 페이지 이동을 막을 수 있다.
//   7. e.stopPropagation()메소드 -> 이벤트가 상위요소로 전파되는 것을 막는 메소드
//                                   이벤트 버블링을 제어할 때 사용
