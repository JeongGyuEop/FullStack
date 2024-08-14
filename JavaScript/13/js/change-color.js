// 위에 작성해 놓은 id가 heading인 <h1>한 쌍을 선택하여
// heading 변수 메모리에 저장
var heading = document.querySelector('#heading');

// <h1> 한 쌍에 사용자가 클릭하는 행동 등록
heading.onclick = function () {
  // 동작하는 코드
  // <h1> 한쌍의 내부에 적힌 글자 색상을 빨간색으로 설정
  heading.style.color = 'red';
};
