// 방법2. DOM을 사용해 웹 요소를 선택해서 가져온 후 속성(프로퍼티)를
//       이용해 이벤트 처리 함수를 연결하는 방법
const button = document.querySelector('button');
button.onclick = () => {
  document.body.style.backgroundColor = 'green';
};
