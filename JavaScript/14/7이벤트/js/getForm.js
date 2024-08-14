// document.querySelector("#major")는 id 속성의 값이
// major인 <select>요소를 선택
// 이 <select> 요소는 사용자가 학과를 선택할 수 있는 드롭다운 메뉴
const selectMenu = document.querySelector('#major');

// 이벤트 처리 함수(이벤트 핸들러 함수)
// 사용자가 선택한 option 항목의 텍스트를 가져와 이를 알림창으로
// 표시하는 기능의 함수
function displaySelect() {
  // selectMenu.options 속성을 호출하면 모든 <option>요소를 포함하는
  // 배열과 유사한 객체를 반환해 준다.

  // selectMenu.selectedIndex 속성을 호출하면
  // 현재 선택된 <option> 요소의 index 위치 번호를 반환
  // selectMenu.options[ selectMenu.selectedIndex].innerText;
  // 선택된 <option>요소의 텍스트 노드값을 가지고 온다.
  // 예) 건축공학과, 기계공학과 등등..
  let selectedText = selectMenu.options[selectMenu.selectedIndex].innerText;

  // alert() 함수를 호출하여 선택된 option 요소를 표시
  alert(`${selectedText}를 선택했습니다.`);
}

// select option들 중에서 하나의 option을 선택하여 변경한 동작을
// <select>요소에 등록
// 사용자가 만약 option 중에서 하나의 option을 선택하면
// 이벤트 핸들러 함수 displaySelect 함수를 호출하여 실행한다.
selectMenu.onchange = displaySelect;
